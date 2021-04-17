/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.implement.service.dataset.cctv;

import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.interfaces.DataSetCCTVInterface;
import kr.dataportal.datahubcore.implement.repository.dataset.cctv.DataSetCCTVRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataSetCCTVService implements DataSetCCTVInterface {
    private final DataSetCCTVRepository dataSetCCTVRepository;

    @Override
    public DataSetCCTV findBySeq(String seq) {
        return dataSetCCTVRepository.findBySeq(seq);
    }

    @Override
    public List<DataSetCCTV> findAll() {
        return dataSetCCTVRepository.findAll();
    }

    @Override
    public List<DataSetCCTV> findByPage(int page, int itemPerPage) {
        return dataSetCCTVRepository.findByPage(page, itemPerPage);
    }

    @Override
    public DataSetCCTV findRandomize() {
        return dataSetCCTVRepository.findRandomize();
    }

    @Override
    @Transactional(readOnly = false)
    public void save(DataSetCCTV cctv) {
        dataSetCCTVRepository.save(cctv);
    }
}
