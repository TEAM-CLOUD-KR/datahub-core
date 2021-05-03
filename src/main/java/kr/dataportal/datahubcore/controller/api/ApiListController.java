package kr.dataportal.datahubcore.controller.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.dto.api.ApiListDetailAndDataSetColumn;
import kr.dataportal.datahubcore.dto.api.ApiListSearchDTO;
import kr.dataportal.datahubcore.dto.dataset.DataSetColumnDesc;
import kr.dataportal.datahubcore.implement.service.api.ApiListService;
import kr.dataportal.datahubcore.implement.service.common.Category1stService;
import kr.dataportal.datahubcore.implement.service.datahub.DatahubListService;
import kr.dataportal.datahubcore.implement.service.dataset.DataSetListService;
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
    private final DataSetListService dataSetListService;

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

    // 사용자 정의 API Class 매퍼
    private final Map<DataSetList, ClassLoader> apiMapper = new HashMap<>();

    // 사용자 정의 API 조회 기능
    @GetMapping("/{user}/{apiPath}")
    public JSONResponse UserCustomizeApi(@PathVariable String user, @PathVariable String apiPath) {
        Optional<ApiList> byPath = apiListService.findByUserAndPath(user, apiPath);
        return byPath.map(
                apiList -> new JSONResponse(HttpStatus.OK, apiList)
        ).orElseGet(
                () -> new JSONResponse(HttpStatus.NOT_FOUND, "CAN NOT FOUND THE PATH")
        );
    }
}
