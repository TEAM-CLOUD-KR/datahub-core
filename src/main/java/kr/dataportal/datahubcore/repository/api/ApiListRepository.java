package kr.dataportal.datahubcore.repository.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.dto.api.ApiListPagingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApiListRepository {
    private final EntityManager em;

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

    public List<ApiList> findByPage(ApiListPagingDTO pagingDTO) {
        return em.createQuery("" +
                " SELECT apilist FROM ApiList apilist" +
                " order by apilist.seq desc", ApiList.class)
                .setFirstResult((pagingDTO.getPage() - 1) * pagingDTO.getItemPerPage())
                .setMaxResults(pagingDTO.getItemPerPage())
                .getResultList();
    }

    public void save(ApiList apiList) {
        em.persist(apiList);
    }
}
