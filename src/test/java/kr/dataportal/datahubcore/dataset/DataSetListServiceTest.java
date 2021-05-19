package kr.dataportal.datahubcore.dataset;

import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.implement.service.dataset.DataSetListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        DataSetList dataSetList = dataSetListService.findOne("datasetcctv");
        assertThat(dataSetList.getDataset()).isEqualTo("DataSetCCTV");
    }

    @Test
    void DataSetList_findAll() {
        List<DataSetList> all = dataSetListService.findAll("");
        System.out.println("all.get(0).getDataset() = " + all.get(0).getDataset());
    }
}
