package kr.dataportal.datahubcore.controller.datacore;

import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.service.dataset.cctv.DataSetCCTVService;
import kr.dataportal.datahubcore.service.dataset.gwanbo.DataSetGwanboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccessOneController {
    private final DataSetCCTVService dataSetCCTVService;
    private final DataSetGwanboService dataSetGwanboService;

    @GetMapping("/")
    public JSONResponse AccessOne() {
        return new JSONResponse(HttpStatus.OK, dataSetCCTVService.findRandomize());
    }
}
