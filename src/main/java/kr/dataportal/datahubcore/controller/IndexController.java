/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - Create core/IndexController
*/

package kr.dataportal.datahubcore.controller;

import kr.dataportal.datahubcore.domain.DataSetGwanbo;
import kr.dataportal.datahubcore.service.DataSetGwanboService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IndexController {
    private final DataSetGwanboService dataSetGwanboService;

    @GetMapping("/")
    public DataSetGwanbo Index() {
        return dataSetGwanboService.findOne("1317816664268000");
    }
}
