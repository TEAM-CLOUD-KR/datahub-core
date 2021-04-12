/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.service.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.dto.api.ApiListPagingDTO;
import kr.dataportal.datahubcore.repository.api.ApiListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApiListService {
    private final ApiListRepository apiListRepository;

    public ApiList findBySeq(int seq) {
        return apiListRepository.findBySeq(seq);
    }

    public ApiList findByName(String name) {
        return apiListRepository.findByName(name);
    }

    public List<ApiList> findByPage(ApiListPagingDTO pagingDTO) {
        return apiListRepository.findByPage(pagingDTO);
    }

    @Transactional(readOnly = false)
    public void save(ApiList apiList) {
        apiListRepository.save(apiList);
    }

    public Long getCount() {
        return apiListRepository.getCount();
    }
}
