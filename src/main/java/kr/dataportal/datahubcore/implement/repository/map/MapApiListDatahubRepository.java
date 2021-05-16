package kr.dataportal.datahubcore.implement.repository.map;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.dataportal.datahubcore.domain.map.MapApiListDatahub;
import kr.dataportal.datahubcore.domain.map.MapUserDatahub;
import kr.dataportal.datahubcore.domain.map.QMapUserDatahub;
import kr.dataportal.datahubcore.interfaces.map.MapApiListDatahubInterface;
import kr.dataportal.datahubcore.interfaces.map.MapUserDatahubInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MapApiListDatahubRepository implements MapApiListDatahubInterface {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MapApiListDatahub> findAll(int userSeq) {
        return em.createQuery("" +
                " SELECT item FROM MapUserDatahub item" +
                " WHERE item.user.seq =: seq order by item.seq desc", MapApiListDatahub.class)
                .setParameter("seq", userSeq)
                .getResultList();
    }

    @Override
    public List<String> findAllDataHubNameByUserSeq(int userSeq) {
        return queryFactory
                .select(QMapUserDatahub.mapUserDatahub.datahubList.name)
                .from(QMapUserDatahub.mapUserDatahub)
                .where(
                        QMapUserDatahub.mapUserDatahub.user.seq.eq(userSeq)
                )
                .fetch();
    }

    @Override
    public MapApiListDatahub findOne(String dataSetName) {
        return null;
    }
}
