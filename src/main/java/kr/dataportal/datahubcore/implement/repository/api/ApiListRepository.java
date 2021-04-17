package kr.dataportal.datahubcore.implement.repository.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.dto.api.ApiListPagingDTO;
import kr.dataportal.datahubcore.interfaces.api.ApiListInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApiListRepository implements ApiListInterface {
    private final EntityManager em;

    @Override
    public ApiList findBySeq(int seq) {
        List<ApiList> apiList = em.createQuery("" +
                " SELECT apilist FROM ApiList apilist" +
                " WHERE apilist.seq=:seq", ApiList.class)
                .setParameter("seq", seq)
                .getResultList();

        if (apiList.size() > 0) {
            return apiList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public ApiList findByName(String name) {
        List<ApiList> apiList = em.createQuery("" +
                " SELECT apilist FROM ApiList apilist" +
                " WHERE apilist.name=:name", ApiList.class)
                .setParameter("name", name)
                .getResultList();

        if (apiList.size() > 0) {
            return apiList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<ApiList> findByPage(ApiListPagingDTO pagingDTO) {
        return em.createQuery("" +
                " SELECT apilist FROM ApiList apilist" +
                " order by apilist.seq desc", ApiList.class)
                .setFirstResult((pagingDTO.getPage() - 1) * pagingDTO.getItemPerPage())
                .setMaxResults(pagingDTO.getItemPerPage())
                .getResultList();
    }

    @Override
    public Long getCount() {
        return em.createQuery("" +
                " SELECT COUNT(apilist) FROM ApiList apilist", Long.class)
                .getSingleResult();
    }

    @Override
    public void save(ApiList apiList) {
        em.persist(apiList);
    }
}
