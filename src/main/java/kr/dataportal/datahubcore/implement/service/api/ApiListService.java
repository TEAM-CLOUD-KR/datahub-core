/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.implement.service.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.dto.api.ApiListSearchDTO;
import kr.dataportal.datahubcore.interfaces.api.ApiListInterface;
import kr.dataportal.datahubcore.implement.repository.api.ApiListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApiListService implements ApiListInterface {
    private final ApiListRepository apiListRepository;

    @Override
    public Optional<ApiList> findBySeq(int seq) {
        return apiListRepository.findBySeq(seq);
    }

    @Override
    public ApiList findByName(String name) {
        return apiListRepository.findByName(name);
    }

    @Override
    public Optional<ApiList> findByUserAndPath(String user, String path) {
        return apiListRepository.findByUserAndPath(user, path);
    }

    @Override
    public List<ApiList> search(ApiListSearchDTO searchDTO) {
        return apiListRepository.search(searchDTO);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(ApiList apiList) {
        apiListRepository.save(apiList);
    }

    @Override
    public List<String> getOrganizations() {
        return apiListRepository.getOrganizations();
    }

    @Override
    public Long getCount(ApiListSearchDTO searchDTO) {
        return apiListRepository.getCount(searchDTO);
    }
}
