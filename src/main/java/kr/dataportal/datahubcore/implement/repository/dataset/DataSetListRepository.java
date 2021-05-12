package kr.dataportal.datahubcore.implement.repository.dataset;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.dataportal.datahubcore.domain.api.QApiList;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.domain.dataset.QDataSetList;
import kr.dataportal.datahubcore.interfaces.dataset.DataSetListInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DataSetListRepository implements DataSetListInterface {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> findAll(String name) {
        return queryFactory
                .select(QDataSetList.dataSetList.dataSet)
                .from(QDataSetList.dataSetList)
                .where(
                        QDataSetList.dataSetList.dataSet.contains(name)
                )
                .fetch();
    }

    @Override
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
