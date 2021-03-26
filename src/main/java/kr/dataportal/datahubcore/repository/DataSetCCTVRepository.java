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

import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DataSetCCTVRepository {

    private final EntityManager em;

    public DataSetCCTV findBySeq(String seq) {
        List<DataSetCCTV> cctv = em.createQuery("" +
                " SELECT datasetcctv FROM DataSetCCTV datasetcctv" +
                " WHERE datasetcctv.seq =: seq", DataSetCCTV.class)
                .setParameter("seq", seq)
                .getResultList();

        if (cctv.size() > 0) {
            return cctv.get(0);
        } else {
            return null;
        }
    }

    public List<DataSetCCTV> findAll() {
        return em.createQuery("" +
                " SELECT datasetcctv FROM DataSetCCTV datasetcctv", DataSetCCTV.class)
                .getResultList();
    }

    public void save(DataSetCCTV cctv) {
        em.persist(cctv);
    }
}
