/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.implement.service.dataset.gwanbo;

import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.interfaces.DataSetGwanboInterface;
import kr.dataportal.datahubcore.implement.repository.dataset.gwanbo.DataSetGwanboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataSetGwanboService implements DataSetGwanboInterface {
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

    public List<DataSetGwanbo> findByPage(int page, int itemPerPage) {
        return dataSetGwanboRepository.findByPage(page, itemPerPage);
    }

    @Transactional(readOnly = false)
    public void save(DataSetGwanbo gwanbo) {
        dataSetGwanboRepository.save(gwanbo);
    }
}
