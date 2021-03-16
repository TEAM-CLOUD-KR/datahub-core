package kr.dataportal.datahubcore;

import kr.dataportal.datahubcore.domain.dataset.Category;
import kr.dataportal.datahubcore.domain.dataset.DataSetGwanbo;
import kr.dataportal.datahubcore.domain.dataset.Publish;
import kr.dataportal.datahubcore.service.DataSetGwanboService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @Rollback(value = true)
    void DataSetGwanboService_저장_테스트() {
        DataSetGwanbo gwanbo = new DataSetGwanbo(
                "test_id",
                new Publish("publish_id", "publish_title",
                        "20210316", "publish_sequence", "publish_author"),
                new Category("category_name", "category_id"),
                "binary"
        );
        dataSetGwanboService.save(gwanbo);
        DataSetGwanbo one = dataSetGwanboService.findById("test_id");

        Assertions.assertThat(one.getPublish().getAuthor()).isEqualTo("publish_author");
    }

}
