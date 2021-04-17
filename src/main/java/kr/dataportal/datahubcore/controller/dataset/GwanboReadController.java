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
public class GwanboReadController {
    private final DataSetGwanboService dataSetGwanboService;

    @GetMapping("/gwanbo")
    public JSONResponse ReadDataSetGwanbo(@RequestParam(name = "page", required = false, defaultValue = "1") int page, @RequestParam(name = "item_per_page", required = false, defaultValue = "10") int itemPerPage) {
        return new JSONResponse(HttpStatus.OK, dataSetGwanboService.findByPage(page, itemPerPage));
    }
}
