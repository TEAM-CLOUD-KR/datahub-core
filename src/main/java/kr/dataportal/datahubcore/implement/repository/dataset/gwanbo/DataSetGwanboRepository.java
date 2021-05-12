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

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sun.el.lang.ExpressionBuilder;
import kr.dataportal.datahubcore.domain.dataset.QDataSetList;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.QDataSetGwanbo;
import kr.dataportal.datahubcore.interfaces.dataset.gwanbo.DataSetGwanboInterface;
import kr.dataportal.datahubcore.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    private Expression<?>[] getDeclaredColumns(Class<?> target, List<String> filter, boolean recursion) {
        List<Expression<?>> ret = new ArrayList<>();
        for (Field field : target.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                if (field.getType().equals(StringPath.class)) {
                    String fieldName = CommonUtil.camelToSnake(field.getName());
                    if (recursion) {
                        String s = target.getSimpleName().toLowerCase().substring(1) + "_" + fieldName;
                        if (filter.contains(s)) {
                            ret.add(Expressions.asSimple("datasetGwanbo." + field.getName()));
                        }
                    } else {
                        if (filter.contains(fieldName)) {
                            ret.add(Expressions.asSimple("datasetGwanbo." + field.getName()));
                        }
                    }
                } else {
                    ret.addAll(Arrays.asList(getDeclaredColumns(field.getType(), filter, true)));
                }
            }
        }
        return ret.toArray(new Expression<?>[0]);
    }

    private Expression<?>[] filteredColumn(Class<?> target, List<String> filter) {
        return getDeclaredColumns(target, filter, false);
    }

    @Override
    public List<DataSetGwanbo> search(List<String> targetColumns, int page, int itemPerPage) {
//        Expression<?>[] expressions = filteredColumn(QDataSetGwanbo.dataSetGwanbo.getClass(), targetColumns);
//
//        try {
//            Field fd = QDataSetGwanbo.dataSetGwanbo.getClass().getDeclaredField("seq");
//            System.out.println("fd = " + fd);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        StringPath sp = QDataSetGwanbo.dataSetGwanbo.seq;
//        System.out.println("sp = " + sp);

        queryFactory
                .selectFrom(QDataSetGwanbo.dataSetGwanbo)
                .offset((long) (page - 1) * itemPerPage)
                .limit((long) itemPerPage)
                .fetch();

        return new ArrayList<>();
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
