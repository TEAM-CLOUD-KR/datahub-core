/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.controller.util;

import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.dto.dataset.DataSetColumnDesc;
import kr.dataportal.datahubcore.implement.service.dataset.DataSetListService;
import kr.dataportal.datahubcore.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common/util")
@RequiredArgsConstructor
public class UtilController {
    private final DataSetListService dataSetListService;

    @GetMapping("/scheme/{target}")
    public JSONResponse getClassProperty(@PathVariable String target) {
        List<DataSetColumnDesc> dataSetColumnDescs = CommonUtil.parseClassProperty(target);
        if (dataSetColumnDescs.size() > 0) {
            return new JSONResponse(
                    HttpStatus.OK,
                    dataSetColumnDescs
            );
        }
        DataSetList one = dataSetListService.findOne(target);
        return new JSONResponse(
                HttpStatus.MOVED_PERMANENTLY, one
        );
    }
}
