package kr.dataportal.datahubcore.dataset.cctv;

import kr.dataportal.datahubcore.dto.dataset.DataSetColumnDesc;
import kr.dataportal.datahubcore.util.CommonUtil;
import org.junit.jupiter.api.Test;
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
public class DataSetCCTVServiceTest {
    @Test
    void DataSetCCTV_컬럼_전체조회_By_String() {
        List<DataSetColumnDesc> columns = CommonUtil.parseClassProperty("DataSetCCTV");
        assertThat(columns).isNotNull();
        assertThat(columns.size()).isGreaterThan(0);
    }
}
