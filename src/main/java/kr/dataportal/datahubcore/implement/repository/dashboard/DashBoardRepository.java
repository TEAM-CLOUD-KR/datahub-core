package kr.dataportal.datahubcore.implement.repository.dashboard;

import com.google.gson.Gson;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.dataportal.datahubcore.domain.dashboard.DashBoardList;
import kr.dataportal.datahubcore.domain.dashboard.QDashBoardList;
import kr.dataportal.datahubcore.interfaces.dashboard.DashBoardListInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashBoardRepository implements DashBoardListInterface {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    @Transactional(readOnly = false)
    public int save(DashBoardList dashBoardList) {
        em.persist(dashBoardList);
        em.flush();
        return dashBoardList.getSeq();
    }

    @Override
    public DashBoardList findOne(int seq) {
        return queryFactory
                .selectFrom(QDashBoardList.dashBoardList)
                .where(QDashBoardList.dashBoardList.seq.eq(seq))
                .fetchOne();
    }

    @Override
    public List<DashBoardList> findAll() {
        return queryFactory
                .selectFrom(QDashBoardList.dashBoardList)
                .fetch();
    }
}
