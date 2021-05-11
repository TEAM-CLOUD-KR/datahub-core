package kr.dataportal.datahubcore.implement.repository.map;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.dataportal.datahubcore.domain.api.QApiList;
import kr.dataportal.datahubcore.domain.map.MapUserDatahub;
import kr.dataportal.datahubcore.domain.map.QMapUserDatahub;
import kr.dataportal.datahubcore.interfaces.map.MapUserDatahubInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MapUserDatahubRepository implements MapUserDatahubInterface {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MapUserDatahub> findAll(int userSeq) {
        return em.createQuery("" +
                " SELECT item FROM MapUserDatahub item" +
                " WHERE item.user.seq =: seq", MapUserDatahub.class)
                .setParameter("seq", userSeq)
                .getResultList();
    }

    @Override
    public List<String> findAllDataHubName(int userSeq) {
        return queryFactory
                .select(QMapUserDatahub.mapUserDatahub.datahubList.name)
                .from(QMapUserDatahub.mapUserDatahub)
                .where(
                        QMapUserDatahub.mapUserDatahub.user.seq.eq(userSeq)
                )
                .fetch();
    }

    @Override
    public MapUserDatahub findOne(String dataSetName) {
        return null;
    }
}
