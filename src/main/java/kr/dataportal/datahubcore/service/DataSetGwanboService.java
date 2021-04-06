/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.service;

import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.repository.DataSetGwanboRepository;
import kr.dataportal.datahubcore.util.ParseProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataSetGwanboService {
    private final DataSetGwanboRepository dataSetGwanboRepository;

    public DataSetGwanbo findBySeq(String seq) {
        return dataSetGwanboRepository.findBySeq(seq);
    }

    public List<DataSetGwanbo> findAll() {
        return dataSetGwanboRepository.findAll();
    }

    public DataSetGwanbo findRandomize() {
        return dataSetGwanboRepository.findRandomize();
    }

    @Transactional(readOnly = false)
    public void save(DataSetGwanbo gwanbo) {
        dataSetGwanboRepository.save(gwanbo);
    }

    public List<String> selectAllColumn() {
        return ParseProperty.getAll(DataSetGwanbo.class);
    }
}
