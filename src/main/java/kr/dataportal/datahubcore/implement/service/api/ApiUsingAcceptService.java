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

import kr.dataportal.datahubcore.domain.api.ApiUsingList;
import kr.dataportal.datahubcore.implement.repository.api.ApiUsingAcceptRepository;
import kr.dataportal.datahubcore.interfaces.api.ApiUsingAcceptInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApiUsingAcceptService implements ApiUsingAcceptInterface {
    private final ApiUsingAcceptRepository apiUsingAcceptRepository;

    @Override
    public int save(ApiUsingList apiUsingList) {
        return apiUsingAcceptRepository.save(apiUsingList);
    }

    @Override
    public List<ApiUsingList> findByPublisher(int userSeq) {
        return apiUsingAcceptRepository.findByPublisher(userSeq);
    }

    @Override
    public List<ApiUsingList> findByRequestUser(int userSeq) {
        return apiUsingAcceptRepository.findByRequestUser(userSeq);
    }
}
