/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.domain.dataset;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DataSetGeneral {
    private final LocalDateTime date;
    private final Object data;

    public DataSetGeneral(Object data) {
        this.date = LocalDateTime.now();
        this.data = data;
    }
}
