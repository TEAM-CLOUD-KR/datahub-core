package kr.dataportal.datahubcore.controller.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.dto.api.ApiListCreateDTO;
import kr.dataportal.datahubcore.dto.api.ApiListDetailAndDataSetColumn;
import kr.dataportal.datahubcore.dto.api.ApiListSearchDTO;
import kr.dataportal.datahubcore.dto.dataset.DataSetColumnDesc;
import kr.dataportal.datahubcore.implement.service.api.ApiListService;
import kr.dataportal.datahubcore.implement.service.common.Category1stService;
import kr.dataportal.datahubcore.implement.service.common.Category2ndService;
import kr.dataportal.datahubcore.implement.service.datahub.DatahubListService;
import kr.dataportal.datahubcore.implement.service.dataset.DataSetListService;
import kr.dataportal.datahubcore.implement.service.dataset.cctv.DataSetCCTVService;
import kr.dataportal.datahubcore.implement.service.dataset.gwanbo.DataSetGwanboService;
import kr.dataportal.datahubcore.implement.service.user.UserService;
import kr.dataportal.datahubcore.util.CommonUtil;
import kr.dataportal.datahubcore.vo.api.ApiListSearchVO;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    // API 목록 조회 기능
    @PostMapping("/list")
    public JSONResponse ApiListSearch(@RequestBody ApiListSearchDTO searchDTO) {
        return new JSONResponse(HttpStatus.OK, new ApiListSearchVO(
                datahubListService.findAll(), category1stService.findAll(), apiListService.getOrganizations(),
                apiListService.getCount(searchDTO), apiListService.search(searchDTO))
        );
    }

    // API 상세 조회 기능
    @GetMapping("/{seq}")
    public JSONResponse ApiDetail(@PathVariable String seq) {
        try {
            Optional<ApiList> bySeq = apiListService.findBySeq(Integer.parseInt(seq));
            if (bySeq.isPresent()) {
                ApiList apiList = bySeq.get();
                List<DataSetColumnDesc> dataSetColumnDesc =
                        CommonUtil.parseClassProperty(apiList.getTargetDataset().getDataSet());
                return new JSONResponse(HttpStatus.OK, new ApiListDetailAndDataSetColumn(apiList, dataSetColumnDesc));
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

    // 사용자 정의 API Class 매퍼
    private final Map<DataSetList, ClassLoader> apiMapper = new HashMap<>();

    // 사용자 정의 API 조회 기능
    @GetMapping("/v2/{apiSeq}")
    public JSONResponse UserCustomizeApi(@PathVariable int apiSeq,
                                         @RequestParam(required = false, defaultValue = "1") int page,
                                         @RequestParam(required = false, defaultValue = "10") int itemPerPage
    ) {
        Optional<ApiList> byPath = apiListService.findBySeq(apiSeq);
        if (byPath.isPresent()) {
            Optional<Class<?>> classByClassName = CommonUtil.getClassByClassName(byPath.get().getTargetDataset().getDataSet());
            if (classByClassName.isPresent()) {
                Class<?> extractClass = classByClassName.get();
                if (DataSetGwanbo.class.equals(extractClass)) {
                    return new JSONResponse(HttpStatus.OK, dataSetGwanboService.findByPage(page, itemPerPage));
                } else if (DataSetCCTV.class.equals(extractClass)) {
                    return new JSONResponse(HttpStatus.OK, dataSetCCTVService.findByPage(page, itemPerPage));
                }
            } else {
                return new JSONResponse(HttpStatus.BAD_REQUEST, "CAN NOT FOUND THE DATASET");
            }
        }
        return new JSONResponse(HttpStatus.BAD_REQUEST, "CAN NOT FOUND THE PATH");
    }
}
