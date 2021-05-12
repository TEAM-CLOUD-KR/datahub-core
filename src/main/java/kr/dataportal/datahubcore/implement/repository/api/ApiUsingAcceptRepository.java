/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.implement.repository.api;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.dataportal.datahubcore.domain.api.ApiUsingList;
import kr.dataportal.datahubcore.domain.api.QApiUsingList;
import kr.dataportal.datahubcore.interfaces.api.ApiUsingAcceptInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ApiUsingAcceptRepository implements ApiUsingAcceptInterface {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ApiUsingList> findByPublisher(int userSeq) {
        return queryFactory
                .selectFrom(QApiUsingList.apiUsingList)
                .where(
                        QApiUsingList.apiUsingList.api.publisher.seq.eq(userSeq)
                )
                .fetch();
    }
}
