/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.implement.repository.dataset.cctv;

import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.cctv.QDataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.QDataSetGwanbo;
import kr.dataportal.datahubcore.interfaces.dataset.cctv.DataSetCCTVInterface;
import kr.dataportal.datahubcore.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;

@Repository
@RequiredArgsConstructor
public class DataSetCCTVRepository implements DataSetCCTVInterface {

    private final EntityManager em;

    @Override
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

    @Override
    public List<DataSetCCTV> findAll() {
        return em.createQuery("" +
                " SELECT datasetcctv FROM DataSetCCTV datasetcctv", DataSetCCTV.class)
                .getResultList();
    }

    @Override
    public List<DataSetCCTV> findByPage(int page, int itemPerPage) {
        return em.createQuery("" +
                " SELECT datasetcctv FROM DataSetCCTV datasetcctv ORDER BY datasetcctv.seq desc", DataSetCCTV.class)
                .setFirstResult((page - 1) * itemPerPage)
                .setMaxResults(itemPerPage)
                .getResultList();
    }

    @Override
    public List<DataSetCCTV> search(List<String> targetColumns, int page, int itemPerPage) {
        String qur = CommonUtil.createSearchQuery(QDataSetCCTV.dataSetCCTV.getClass(), targetColumns, DataSetCCTV.class);

        return em.createQuery(qur)
                .setFirstResult((page - 1) * itemPerPage)
                .setMaxResults(itemPerPage)
                .getResultList();
    }

    @Override
    public DataSetCCTV findRandomize() {
        Random random = new Random();
        List<DataSetCCTV> cctv = em.createQuery("" +
                " SELECT datasetcctv FROM DataSetCCTV datasetcctv", DataSetCCTV.class)
                .setFirstResult(random.nextInt(10000))
                .setMaxResults(100)
                .getResultList();

        if (cctv.size() > 0) {
            return cctv.get(random.nextInt(cctv.size() - 1));
        } else {
            return null;
        }
    }

    @Override
    public void save(DataSetCCTV cctv) {
        em.persist(cctv);
    }
}
