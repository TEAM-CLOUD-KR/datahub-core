package kr.dataportal.datahubcore.controller.dashboard;

import kr.dataportal.datahubcore.domain.api.ApiUsingList;
import kr.dataportal.datahubcore.domain.dashboard.DashBoardList;
import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.dto.dashboard.DashBoardListDTO;
import kr.dataportal.datahubcore.implement.service.api.ApiUsingAcceptService;
import kr.dataportal.datahubcore.implement.service.dashboard.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashBoardController {
    private final DashBoardService dashBoardService;
    private final ApiUsingAcceptService apiUsingAcceptService;

    @PostMapping("")
    public JSONResponse DashBoardCreate(@RequestBody DashBoardListDTO dashBoardListDTO) {
        ApiUsingList byApiSeq = apiUsingAcceptService.findByApiSeq(dashBoardListDTO.getApiSeq());
        DashBoardList dashBoardList = new DashBoardList(
                byApiSeq,
                dashBoardListDTO.getPage(),
                dashBoardListDTO.getItemPerPage(),
                dashBoardListDTO.getLabels(),
                dashBoardListDTO.getDatas(),
                dashBoardListDTO.getType()
        );
        return new JSONResponse(HttpStatus.OK, dashBoardService.save(dashBoardList));
    }

    @GetMapping("/{seq}")
    public JSONResponse DashBoardSelect(@PathVariable int seq) {
        return new JSONResponse(HttpStatus.OK, dashBoardService.findOne(seq));
    }

}
