/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.implement.repository.dataset.gwanbo;

import com.google.common.reflect.TypeToken;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.QDataSetGwanbo;
import kr.dataportal.datahubcore.interfaces.dataset.gwanbo.DataSetGwanboInterface;
import kr.dataportal.datahubcore.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DataSetGwanboRepository implements DataSetGwanboInterface {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public DataSetGwanbo findBySeq(String seq) {
        List<DataSetGwanbo> gwanbos = em.createQuery("" +
                " SELECT datasetgwanbo FROM DataSetGwanbo datasetgwanbo" +
                " WHERE datasetgwanbo.seq =: seq", DataSetGwanbo.class)
                .setParameter("seq", seq)
                .getResultList();

        if (gwanbos.size() > 0) {
            return gwanbos.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<DataSetGwanbo> findAll() {
        return em.createQuery("" +
                " SELECT datasetgwanbo FROM DataSetGwanbo datasetgwanbo", DataSetGwanbo.class)
                .getResultList();
    }

    @Override
    public List<DataSetGwanbo> search(List<String> targetColumns, int page, int itemPerPage) {
        String qur = CommonUtil.createSearchQuery(QDataSetGwanbo.dataSetGwanbo.getClass(), targetColumns, DataSetGwanbo.class);

        return em.createQuery(qur)
                .setFirstResult((page - 1) * itemPerPage)
                .setMaxResults(itemPerPage)
                .getResultList();
//        return queryFactory
//                .selectFrom(QDataSetGwanbo.dataSetGwanbo)
//                .offset((long) (page - 1) * itemPerPage)
//                .limit(itemPerPage)
//                .fetch();
    }

    @Override
    public DataSetGwanbo findRandomize() {
        Random random = new Random();
        List<DataSetGwanbo> gwanbo = em.createQuery("" +
                " SELECT datasetgwanbo FROM DataSetGwanbo datasetgwanbo", DataSetGwanbo.class)
                .setFirstResult(random.nextInt(10000))
                .setMaxResults(100)
                .getResultList();

        if (gwanbo.size() > 0) {
            return gwanbo.get(random.nextInt(gwanbo.size() - 1));
        } else {
            return null;
        }
    }

    @Override
    public void save(DataSetGwanbo gwanbo) {
        em.persist(gwanbo);
    }
}
