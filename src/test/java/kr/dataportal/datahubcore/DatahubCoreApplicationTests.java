package kr.dataportal.datahubcore;

import kr.dataportal.datahubcore.domain.dataset.gwanbo.Category;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.Organization;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.Publish;
import kr.dataportal.datahubcore.service.DataSetGwanboService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(properties = ("" +
        "spring.config.location=" +
        "classpath:/application.yml," +
        "optional:C:/repository/_secrets/datahub-core.yml," +
        "optional:/home/datahub/_secrets/datahub-core.yml," +
        "optional:/Users/sun/repository/_secrets/datahub-core.yml"
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
                "test_seq",
                "ebook_no",
                new Publish("publish_seq", "publish_subject", "20210325"),
                new Organization("org_name", "org_code"),
                new Category("category_name", "category_seq"),
                "LawName",
                "binary"
        );
        dataSetGwanboService.save(gwanbo);
        DataSetGwanbo one = dataSetGwanboService.findBySeq("test_seq");

        Assertions.assertThat(one.getPublish().getSeq()).isEqualTo("publish_seq");
    }

    @Test
    void DataSetGwanboService_전체조회_테스트() {
        List<DataSetGwanbo> all = dataSetGwanboService.findAll();
        Assertions.assertThat(all.size()).isGreaterThan(0);
    }
}
