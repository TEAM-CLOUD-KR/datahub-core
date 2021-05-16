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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Repository
public class ApiUsingAcceptRepository implements ApiUsingAcceptInterface {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    @Transactional(readOnly = false)
    public int save(ApiUsingList apiUsingList) {
        em.persist(apiUsingList);
        em.flush();
        return apiUsingList.getSeq();
    }

    @Override
    public ApiUsingList findByApiAndServiceKey(int apiSeq, String serviceKey) {
        return queryFactory
                .selectFrom(QApiUsingList.apiUsingList)
                .where(
                        QApiUsingList.apiUsingList.api.seq.eq(apiSeq)
                ).fetchOne();
    }

    @Override
    public List<ApiUsingList> findByPublisher(int userSeq) {
        return queryFactory
                .selectFrom(QApiUsingList.apiUsingList)
                .where(
                        QApiUsingList.apiUsingList.api.publisher.seq.eq(userSeq)
                )
                .fetch();
    }

    @Override
    public List<ApiUsingList> findByRequestUser(int userSeq) {
        return queryFactory
                .selectFrom(QApiUsingList.apiUsingList)
                .where(
                        QApiUsingList.apiUsingList.requestUser.seq.eq(userSeq)
                )
                .fetch();
    }

    @Override
    public ApiUsingList findByApiAndRequestUser(int apiSeq, int userSeq) {
        return queryFactory
                .selectFrom(QApiUsingList.apiUsingList)
                .where(
                        QApiUsingList.apiUsingList.api.seq.eq(apiSeq),
                        QApiUsingList.apiUsingList.requestUser.seq.eq(userSeq)
                )
                .fetchOne();
    }
}
