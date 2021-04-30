package kr.dataportal.datahubcore.datahub;

import kr.dataportal.datahubcore.domain.datahub.DatahubList;
import kr.dataportal.datahubcore.implement.service.datahub.DatahubListService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = ("" +
        "spring.config.location=" +
        "classpath:/application.yml," +
        "optional:C:/repository/_secrets/datahub-core.yml," +
        "optional:/home/datahub/_secrets/datahub-core.yml," +
        "optional:/Users/sun/repository/_secrets/datahub-core.yml"
))
@Transactional(readOnly = true)
public class DatahubListServiceTest {
    @Autowired
    DatahubListService datahubListService;

    @Test
    @Transactional(readOnly = false)
    @Rollback(value = true)
    void DatahubList_등록_테스트() {
        datahubListService.save(new DatahubList(
                "TEST2"
        ));

        Optional<DatahubList> test = datahubListService.findByName("TEST2");
        test.ifPresent(datahubList -> {
            assertThat(datahubList).isNotNull();
            assertThat(datahubList.getName()).isEqualTo("TEST2");
        });
    }

}
