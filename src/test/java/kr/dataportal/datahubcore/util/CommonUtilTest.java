/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.util;

import kr.dataportal.datahubcore.dto.dataset.DataSetColumnDesc;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonUtilTest {

    @Test
    void Parse_Class_Property_with_null() {
        List<DataSetColumnDesc> dataSetColumnDescs = CommonUtil.parseClassProperty(
                ""
        );

        assertThat(dataSetColumnDescs.size()).isEqualTo(0);
    }

    @Test
    void Parse_Class_Property_with_not_registered_value() {
        List<DataSetColumnDesc> dataSetColumnDescs = CommonUtil.parseClassProperty(
                "abc"
        );

        assertThat(dataSetColumnDescs.size()).isEqualTo(0);
    }
}
