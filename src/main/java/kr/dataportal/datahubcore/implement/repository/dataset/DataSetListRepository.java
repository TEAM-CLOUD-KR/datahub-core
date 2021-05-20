package kr.dataportal.datahubcore.implement.repository.dataset;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.domain.dataset.QDataSetList;
import kr.dataportal.datahubcore.interfaces.dataset.DataSetListInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataSetListRepository implements DataSetListInterface {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    @Transactional(readOnly = false)
    public void save(DataSetList dataSetList) {
        em.persist(dataSetList);
        em.flush();
    }

    @Override
    public List<DataSetList> findAll(String name) {
        return queryFactory
                .select(QDataSetList.dataSetList)
                .from(QDataSetList.dataSetList)
                .where(
                        QDataSetList.dataSetList.dataset.contains(name)
                )
                .fetch();
    }

    @Override
    public DataSetList findOne(String dataSetName) {
        List<DataSetList> dataset = em.createQuery("" +
                " SELECT dl FROM DataSetList dl" +
                " WHERE dl.dataset =: dataset", DataSetList.class)
                .setParameter("dataset", dataSetName)
                .getResultList();

        if (dataset.size() > 0) {
            return dataset.get(0);
        } else {
            return null;
        }
    }
}
