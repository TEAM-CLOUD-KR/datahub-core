package kr.dataportal.datahubcore.implement.repository.datahub;

import kr.dataportal.datahubcore.domain.datahub.DatahubList;
import kr.dataportal.datahubcore.interfaces.datahub.DatahubListInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DatahubListRepository implements DatahubListInterface {
    private final EntityManager em;

    @Override
    @Transactional(readOnly = false)
    public void save(DatahubList datahubList) {
        em.persist(datahubList);
    }

    @Override
    public Optional<DatahubList> fineBySeq(int seq) {
        List<DatahubList> resultList = em.createQuery("" +
                " SELECT datahublist FROM DatahubList datahublist" +
                " WHERE datahublist.seq =: seq", DatahubList.class)
                .setParameter("seq", seq)
                .getResultList();

        if (resultList.size() > 0)
            return Optional.ofNullable(resultList.get(0));
        else
            return Optional.empty();
    }

    @Override
    public Optional<DatahubList> findByName(String name) {
        List<DatahubList> resultList = em.createQuery("" +
                " SELECT datahublist FROM DatahubList datahublist" +
                " WHERE datahublist.name =: name", DatahubList.class)
                .setParameter("name", name)
                .getResultList();

        if (resultList.size() > 0)
            return Optional.ofNullable(resultList.get(0));
        else
            return Optional.empty();
    }

}
