package kr.dataportal.datahubcore;

import kr.dataportal.datahubcore.service.DataSetGwanboService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = ("" +
        "spring.config.location=" +
        "classpath:/application.yml," +
        "optional:C:/repository/_secrets/datahub-core.yml," +
        "optional:/home/datahub/_secrets/datahub-core.yml"
))
class DatahubCoreApplicationTests {
    @Autowired
    private DataSetGwanboService dataSetGwanboService;

    @Test
    void loadContext() {

    }

    @Test
    void repositoryTest() {
        Assertions.assertThat(dataSetGwanboService.findAll().size()).isGreaterThan(0);
    }

}
