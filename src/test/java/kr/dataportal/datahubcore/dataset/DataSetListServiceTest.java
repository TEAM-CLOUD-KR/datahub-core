package kr.dataportal.datahubcore.dataset;

import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.implement.service.dataset.DataSetListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = ("" +
        "spring.config.location=" +
        "classpath:/application.yml," +
        "optional:C:/repository/_secrets/datahub-core.yml," +
        "optional:/home/datahub/_secrets/datahub-core.yml," +
        "optional:/Users/sun/repository/_secrets/datahub-core.yml"
))
public class DataSetListServiceTest {
    @Autowired
    private DataSetListService dataSetListService;

    @Test
    void DataSetList_findOne() {
        DataSetList dataSetList = dataSetListService.findOne("dataset_cctv");
        assertThat(dataSetList.getDataSet()).isEqualTo("dataset_cctv");
    }
}
