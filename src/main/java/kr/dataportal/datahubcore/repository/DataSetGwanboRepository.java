/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.repository;

import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DataSetGwanboRepository {

    private final EntityManager em;

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

    public List<DataSetGwanbo> findAll() {
        return em.createQuery("" +
                " SELECT datasetgwanbo FROM DataSetGwanbo datasetgwanbo", DataSetGwanbo.class)
                .getResultList();
    }

    public void save(DataSetGwanbo gwanbo) {
        em.persist(gwanbo);
    }
}