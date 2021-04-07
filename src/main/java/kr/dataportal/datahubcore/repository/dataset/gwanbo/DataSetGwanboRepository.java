/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.repository.dataset.gwanbo;

import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;

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

    public List<DataSetGwanbo> findByPage(int page, int itemPerPage) {
        return em.createQuery("" +
                " SELECT datasetgwanbo FROM DataSetGwanbo datasetgwanbo order by datasetgwanbo.seq desc", DataSetGwanbo.class)
                .setFirstResult((page - 1) * itemPerPage)
                .setMaxResults(itemPerPage)
                .getResultList();
    }

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

    public void save(DataSetGwanbo gwanbo) {
        em.persist(gwanbo);
    }
}
