/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - Create core/IndexController
*/

package kr.dataportal.datahubcore.controller.dataset;

import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.dto.dataset.DataSetCreateDTO;
import kr.dataportal.datahubcore.implement.service.dataset.DataSetListService;
import kr.dataportal.datahubcore.implement.service.dataset.gwanbo.DataSetGwanboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dataset")
@RequiredArgsConstructor
public class DataSetListController {
    private final DataSetListService dataSetListService;

    @PostMapping("")
    public JSONResponse DataSetCreateAction(@RequestBody DataSetCreateDTO dataSetCreateDTO) {
        DataSetList dataSetList = new DataSetList(
                dataSetCreateDTO.getDataset(),
                dataSetCreateDTO.getDatasetRaw(),
                dataSetCreateDTO.getDatasetColumn(),
                dataSetCreateDTO.getDescription()
        );
        dataSetListService.save(dataSetList);
        return new JSONResponse(HttpStatus.OK, "SUCCESS");
    }

    @GetMapping("/search")
    public JSONResponse DataSetSearchAction(
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "seq", required = false, defaultValue = "") Integer seq
    ) {
        if (seq != null) {
            return new JSONResponse(HttpStatus.OK, dataSetListService.findAll(seq));
        } else {
            return new JSONResponse(HttpStatus.OK, dataSetListService.findAll(name));
        }
    }
}
