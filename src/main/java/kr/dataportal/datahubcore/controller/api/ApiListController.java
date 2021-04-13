package kr.dataportal.datahubcore.controller.api;

import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.dto.api.ApiListPagingDTO;
import kr.dataportal.datahubcore.service.api.ApiListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiListController {
    private final ApiListService apiListService;

    // API 목록 조회 기능
    @PostMapping("/list")
    public JSONResponse ApiListPaging(@RequestBody ApiListPagingDTO apiListPagingDTO) {
        return new JSONResponse(HttpStatus.OK, apiListService.findByPage(apiListPagingDTO));
    }

    // API 목록 개수 조회 기능
    @GetMapping("/count")
    public JSONResponse ApiListGetCount() {
        return new JSONResponse(HttpStatus.OK, apiListService.getCount());
    }
}
