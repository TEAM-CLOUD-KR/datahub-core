package kr.dataportal.datahubcore.controller.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.api.ApiUsingList;
import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.dto.api.ApiDevRequestResponseEnum;
import kr.dataportal.datahubcore.dto.api.ApiUsingAcceptCreateDTO;
import kr.dataportal.datahubcore.implement.service.api.ApiListService;
import kr.dataportal.datahubcore.implement.service.api.ApiUsingAcceptService;
import kr.dataportal.datahubcore.implement.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dev")
public class ApiUsingAcceptController {
    private final UserService userService;
    private final ApiListService apiListService;
    private final ApiUsingAcceptService apiUsingAcceptService;


    @GetMapping("")
    public JSONResponse ApiUsingAcceptView(@RequestParam int userSeq) {
        return new JSONResponse(HttpStatus.OK, apiUsingAcceptService.findByPublisher(userSeq));
    }

    @PostMapping("")
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
