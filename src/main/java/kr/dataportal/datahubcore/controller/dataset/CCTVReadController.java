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
import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.service.DataSetCCTVService;
import kr.dataportal.datahubcore.service.DataSetGwanboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/dataset")
@RequiredArgsConstructor
public class CCTVReadController {
    private final DataSetCCTVService dataSetCCTVService;

    @GetMapping("/cctv")
    public JSONResponse ReadDataSetGwanbo() {
        List<DataSetCCTV> all = dataSetCCTVService.findAll();
        Random random = new Random();
        return new JSONResponse(HttpStatus.OK, all.get(random.nextInt(all.size() - 1)));
    }
}
