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
import kr.dataportal.datahubcore.implement.service.dataset.DataSetListService;
import kr.dataportal.datahubcore.implement.service.dataset.gwanbo.DataSetGwanboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataset")
@RequiredArgsConstructor
public class DataSetListController {
    private final DataSetListService dataSetListService;

    @GetMapping("/search")
    public JSONResponse DataSetSearchAction(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        return new JSONResponse(HttpStatus.OK, dataSetListService.findAll(name));
    }
}
