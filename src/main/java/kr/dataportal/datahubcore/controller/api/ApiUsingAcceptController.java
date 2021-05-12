package kr.dataportal.datahubcore.controller.api;

import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.implement.service.api.ApiUsingAcceptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/request")
public class ApiUsingAcceptController {
    private final ApiUsingAcceptService apiUsingAcceptService;

    @GetMapping("")
    public JSONResponse ApiUsingAcceptView(@RequestParam int userSeq) {
        return new JSONResponse(HttpStatus.OK, apiUsingAcceptService.findByPublisher(userSeq));
    }
}
