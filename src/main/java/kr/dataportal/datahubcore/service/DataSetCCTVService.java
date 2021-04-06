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
import kr.dataportal.datahubcore.repository.DataSetCCTVRepository;
import kr.dataportal.datahubcore.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataSetCCTVService {
    private final DataSetCCTVRepository dataSetCCTVRepository;

    public DataSetCCTV findBySeq(String seq) {
        return dataSetCCTVRepository.findBySeq(seq);
    }

    public List<DataSetCCTV> findAll() {
        return dataSetCCTVRepository.findAll();
    }

    public DataSetCCTV findRandomize() {
        return dataSetCCTVRepository.findRandomize();
    }

    @Transactional(readOnly = false)
    public void save(DataSetCCTV cctv) {
        dataSetCCTVRepository.save(cctv);
    }
}
