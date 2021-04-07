package kr.dataportal.datahubcore.controller.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.dto.ApiListPagingDTO;
import kr.dataportal.datahubcore.service.api.ApiListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiListController {
    private final ApiListService apiListService;

    @PostMapping("/list")
    public List<ApiList> ApiListPaging(@RequestBody ApiListPagingDTO apiListPagingDTO) {
        return apiListService.findByPage(apiListPagingDTO);
    }
}
