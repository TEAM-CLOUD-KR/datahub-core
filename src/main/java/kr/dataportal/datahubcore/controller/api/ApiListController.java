package kr.dataportal.datahubcore.controller.api;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.api.ApiUsingList;
import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.dto.api.*;
import kr.dataportal.datahubcore.dto.dataset.DataSetColumnDesc;
import kr.dataportal.datahubcore.implement.service.api.ApiListService;
import kr.dataportal.datahubcore.implement.service.api.ApiUsingAcceptService;
import kr.dataportal.datahubcore.implement.service.common.Category1stService;
import kr.dataportal.datahubcore.implement.service.common.Category2ndService;
import kr.dataportal.datahubcore.implement.service.datahub.DatahubListService;
import kr.dataportal.datahubcore.implement.service.dataset.DataSetListService;
import kr.dataportal.datahubcore.implement.service.dataset.cctv.DataSetCCTVService;
import kr.dataportal.datahubcore.implement.service.dataset.gwanbo.DataSetGwanboService;
import kr.dataportal.datahubcore.implement.service.user.UserService;
import kr.dataportal.datahubcore.util.CommonUtil;
import kr.dataportal.datahubcore.vo.api.ApiListSearchVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiListController {
    private final ApiListService apiListService;
    private final DatahubListService datahubListService;
    private final Category1stService category1stService;
    private final Category2ndService category2ndService;
    private final DataSetListService dataSetListService;
    private final DataSetGwanboService dataSetGwanboService;
    private final DataSetCCTVService dataSetCCTVService;
    private final UserService userService;
    private final ApiUsingAcceptService apiUsingAcceptService;

    private final Gson gson;

    // API 목록 조회 기능
    @PostMapping("/list")
    public JSONResponse ApiListSearch(@RequestBody ApiListSearchDTO searchDTO) {
        return new JSONResponse(HttpStatus.OK, new ApiListSearchVO(
                datahubListService.findAll(), category1stService.findAll(), apiListService.getOrganizations(),
                apiListService.getCount(searchDTO), apiListService.search(searchDTO))
        );
    }

    // API 목록 조회 기능 with User
    @GetMapping("/search")
    public JSONResponse ApiListSearchWithUser(@RequestParam int userSeq) {
        List<ApiUsingList> byPublisher = apiUsingAcceptService.findByPublisher(userSeq);
        return new JSONResponse(HttpStatus.OK, byPublisher);
    }

    // API 상세 조회 기능
    @GetMapping("/{seq}")
    public JSONResponse ApiDetail(@PathVariable String seq) {
        try {
            Optional<ApiList> bySeq = apiListService.findBySeq(Integer.parseInt(seq));
            if (bySeq.isPresent()) {
                ApiList apiList = bySeq.get();
                List<Object> retColumnDesc = new ArrayList<>();
                List<?> dataSetColumnDesc;

                List<String> columns = new ArrayList<String>(Arrays.asList(apiList.getTargetColumn().split(",")));

                if (apiList.getTargetDataset().getDatasetColumn() == null) {
                    dataSetColumnDesc = CommonUtil.parseClassProperty(apiList.getTargetDataset().getDataset());

                    for (Object setColumnDesc : dataSetColumnDesc) {
                        if (columns.contains(((DataSetColumnDesc) setColumnDesc).getColumnEn())) {
                            retColumnDesc.add(setColumnDesc);
                        }
                    }
                } else {
                    String s = apiList.getTargetDataset().getDatasetColumn();
                    List<String> tmp = new ArrayList<String>(Arrays.asList(s.substring(1, s.length() - 1).split(",")));

                    for (String setColumnDesc : tmp) {
                        if (columns.contains(setColumnDesc.substring(1, setColumnDesc.length() - 1))) {
                            retColumnDesc.add(setColumnDesc.substring(1, setColumnDesc.length() - 1));
                        }
                    }
                }
                return new JSONResponse(HttpStatus.OK, new ApiListDetailAndDataSetColumn(apiList, retColumnDesc));
            } else {
                return new JSONResponse(HttpStatus.BAD_REQUEST, "CAN NOT FOUND Item By Seq");
            }
        } catch (NumberFormatException e) {
            return new JSONResponse(HttpStatus.BAD_REQUEST, "seq is not Integer");
        }
    }

    // API 생성 기능
    @PostMapping("/new")
    public JSONResponse ApiCreate(@RequestBody ApiListCreateDTO apiListCreateDTO) {
        try {
            ApiList apiList = new ApiList(
                    apiListCreateDTO.getName(),
                    dataSetListService.findOne(apiListCreateDTO.getTargetDataset()),
                    apiListCreateDTO.getTargetColumn(),
                    apiListCreateDTO.getPermissionGroup(),
                    apiListCreateDTO.getApiDesc(),
                    category1stService.findOne(apiListCreateDTO.getCategory1st()),
                    category2ndService.findOne(apiListCreateDTO.getCategory2nd()),
                    apiListCreateDTO.getOrganization(),
                    userService.findBySeq(apiListCreateDTO.getPublisher()).get()
            );
            return new JSONResponse(HttpStatus.OK, apiListService.save(apiList));
        } catch (Exception e) {
            return new JSONResponse(HttpStatus.BAD_REQUEST, "");
        }
    }

    // 사용자 정의 API 조회 기능
    @GetMapping("/v2/{apiSeq}")
    public JSONResponse UserCustomizeApi(
            @PathVariable int apiSeq,
            @RequestParam(required = true, defaultValue = "") String serviceKey,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int itemPerPage
    ) {
        ApiUsingList byApi = apiUsingAcceptService.findByApiAndServiceKey(apiSeq, serviceKey);
        if (byApi == null) {
            return new JSONResponse(HttpStatus.UNAUTHORIZED, "SERVICE KEY IS NOT MATCHED");
        }

        Optional<ApiList> byPath = apiListService.findBySeq(apiSeq);
        if (byPath.isPresent()) {
            ApiList apiList = byPath.get();
            Optional<Class<?>> classByClassName = CommonUtil.getClassByClassName(apiList.getTargetDataset().getDataset());


            if (classByClassName.isPresent()) {
                Class<?> extractClass = classByClassName.get();
                List<String> targetColumns = new ArrayList<String>(Arrays.asList(apiList.getTargetColumn().split(",")));
                if (DataSetGwanbo.class.equals(extractClass)) {
                    return new JSONResponse(HttpStatus.OK, dataSetGwanboService.search(targetColumns, page, itemPerPage));
                } else if (DataSetCCTV.class.equals(extractClass)) {
                    return new JSONResponse(HttpStatus.OK, dataSetCCTVService.search(targetColumns, page, itemPerPage));
                }
            } else {
                return new JSONResponse(HttpStatus.OK,
                        gson.fromJson(apiList.getTargetDataset().getDatasetRaw(),
                                new TypeToken<ArrayList<Object>>() {
                                }.getType())
                );
            }
        }
        return new JSONResponse(HttpStatus.BAD_REQUEST, "CAN NOT FOUND THE PATH");
    }

    // 사용자가 권한을 가진 Api 목록 조회 기능
    @GetMapping("/user")
    public JSONResponse UserOwnApiList(@RequestParam int userSeq) {
        List<ApiUsingList> byPublisher = apiListService.findByPublisher(userSeq);
        return new JSONResponse(HttpStatus.OK, byPublisher);
    }

    @GetMapping("/dev")
    public JSONResponse ApiUsingAcceptView(@RequestParam int userSeq) {
        return new JSONResponse(HttpStatus.OK, apiUsingAcceptService.findByRequestUser(userSeq));
    }

    @GetMapping("/dev/search")
    public JSONResponse ApiUsingAcceptSearch(@RequestParam int apiSeq, @RequestParam int userSeq) {
        return new JSONResponse(HttpStatus.OK, apiUsingAcceptService.findByApiAndRequestUser(apiSeq, userSeq));
    }

    @PostMapping("/dev")
    public JSONResponse ApiUsingRequest(@RequestBody ApiUsingAcceptCreateDTO apiUsingAcceptCreateDTO) {
        Optional<ApiList> apiList = apiListService.findBySeq(apiUsingAcceptCreateDTO.getApiSeq());
        if (apiList.isEmpty()) {
            return new JSONResponse(HttpStatus.BAD_REQUEST, ApiDevRequestResponseEnum.FAIL);
        }
        Optional<User> user = userService.findBySeq(apiUsingAcceptCreateDTO.getUserSeq());
        if (user.isEmpty()) {
            return new JSONResponse(HttpStatus.BAD_REQUEST, ApiDevRequestResponseEnum.FAIL);
        }

        ApiUsingList apiUsingList = new ApiUsingList(
                apiList.get(), user.get(), apiUsingAcceptCreateDTO.getPurpose()
        );

        int save = apiUsingAcceptService.save(apiUsingList);
        if (save == 0) {
            return new JSONResponse(HttpStatus.BAD_REQUEST, ApiDevRequestResponseEnum.FAIL);
        }

        return new JSONResponse(HttpStatus.OK, ApiDevRequestResponseEnum.SUCCESS);
    }
}
