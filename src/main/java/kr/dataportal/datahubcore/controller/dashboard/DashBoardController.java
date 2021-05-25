package kr.dataportal.datahubcore.controller.dashboard;

import kr.dataportal.datahubcore.domain.dashboard.DashBoardList;
import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.implement.service.dashboard.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashBoardController {
    private final DashBoardService dashBoardService;

    @PostMapping("")
    public JSONResponse DashBoardCreate(DashBoardList dashBoardList) {
        return new JSONResponse(HttpStatus.OK, dashBoardService.save(dashBoardList));
    }

    @GetMapping("/{seq}")
    public JSONResponse DashBoardSelect(@PathVariable int seq) {
        return new JSONResponse(HttpStatus.OK, dashBoardService.findOne(seq));
    }

}
