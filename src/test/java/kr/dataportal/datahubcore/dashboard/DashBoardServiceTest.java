package kr.dataportal.datahubcore.dashboard;

import kr.dataportal.datahubcore.domain.dashboard.DashBoardList;
import kr.dataportal.datahubcore.implement.service.dashboard.DashBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest(properties = ("" +
        "spring.config.location=" +
        "classpath:/application.yml," +
        "optional:C:/repository/_secrets/datahub-core.yml," +
        "optional:/home/datahub/_secrets/datahub-core.yml," +
        "optional:/Users/sun/repository/_secrets/datahub-core.yml"
))
public class DashBoardServiceTest {
    @Autowired
    private DashBoardService dashBoardService;

    @Autowired
    private EntityManager em;

    @Test
    @Transactional(readOnly = false)
    @Rollback(value = false)
    void 영속성테스트() {
        DashBoardList one = dashBoardService.findOne(22);
        boolean contains = em.contains(one);
        System.out.println("contains = " + contains);
    }
}
