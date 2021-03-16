/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - Create core/IndexController
*/

package kr.dataportal.datahubcore.controller.datacore;

import kr.dataportal.datahubcore.domain.datacore.GeneralResultVO;
import kr.dataportal.datahubcore.domain.dataset.DataSetGeneral;
import kr.dataportal.datahubcore.domain.dataset.DataSetGwanbo;
import kr.dataportal.datahubcore.service.DataSetGwanboService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class GwanboCoreController {
    private final DataSetGwanboService dataSetGwanboService;

    @PostMapping("/gwanbo")
    public GeneralResultVO WriteDataSetGwanbo(@RequestBody DataSetGwanbo gwanbo) {
        dataSetGwanboService.save(gwanbo);
        return new GeneralResultVO(200, dataSetGwanboService.findById(gwanbo.getId()));
    }
}
