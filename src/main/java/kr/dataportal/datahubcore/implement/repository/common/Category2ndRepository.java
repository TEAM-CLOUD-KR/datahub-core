package kr.dataportal.datahubcore.implement.repository.common;

import kr.dataportal.datahubcore.domain.common.Category2nd;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Category2ndRepository {
    private final EntityManager em;

    public Category2nd findOne(String text) {
        List<Category2nd> category2stList = em.createQuery("" +
                " SELECT category2nd FROM Category2nd category2nd" +
                " WHERE category2nd.text =: text", Category2nd.class)
                .setParameter("text", text)
                .getResultList();

        if (category2stList.size() > 0) {
            return category2stList.get(0);
        } else {
            return null;
        }
    }
}
