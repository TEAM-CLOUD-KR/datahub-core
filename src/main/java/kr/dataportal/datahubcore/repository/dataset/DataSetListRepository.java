package kr.dataportal.datahubcore.repository.dataset;

import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DataSetListRepository {
    private final EntityManager em;

    public DataSetList findOne(String dataSetName) {
        List<DataSetList> dataset = em.createQuery("" +
                " SELECT dl FROM DataSetList dl" +
                " WHERE dl.dataSet =: dataset", DataSetList.class)
                .setParameter("dataset", dataSetName)
                .getResultList();

        if (dataset.size() > 0) {
            return dataset.get(0);
        } else {
            return null;
        }

    }
}
