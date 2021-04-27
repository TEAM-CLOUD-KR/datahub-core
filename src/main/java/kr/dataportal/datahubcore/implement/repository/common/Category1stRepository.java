package kr.dataportal.datahubcore.implement.repository.common;

import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.interfaces.common.Category1stInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Category1stRepository implements Category1stInterface {
    private final EntityManager em;

    @Override
    public Category1st findOne(String text) {
        List<Category1st> category1stList = em.createQuery("" +
                " SELECT category1st FROM Category1st category1st" +
                " WHERE category1st.text =: text", Category1st.class)
                .setParameter("text", text)
                .getResultList();

        if (category1stList.size() > 0) {
            return category1stList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Category1st> findAll() {
        return em.createQuery("" +
                " SELECT category1st FROM Category1st category1st", Category1st.class)
                .getResultList();
    }
}
