package kr.dataportal.datahubcore.dataset.gwanbo;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.Category;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.Organization;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.Publish;
import kr.dataportal.datahubcore.dto.dataset.DataSetColumnDesc;
import kr.dataportal.datahubcore.dto.dataset.DataSetSearchDTO;
import kr.dataportal.datahubcore.implement.service.api.ApiListService;
import kr.dataportal.datahubcore.implement.service.dataset.gwanbo.DataSetGwanboService;
import kr.dataportal.datahubcore.util.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = ("" +
        "spring.config.location=" +
        "classpath:/application.yml," +
        "optional:C:/repository/_secrets/datahub-core.yml," +
        "optional:/home/datahub/_secrets/datahub-core.yml," +
        "optional:/Users/sun/repository/_secrets/datahub-core.yml"
))
public class DataSetGwanboServiceTest {
    @Autowired
    private ApiListService apiListService;
    @Autowired
    private DataSetGwanboService dataSetGwanboService;

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

        assertThat(one.getPublish().getSeq()).isEqualTo("publish_seq");
    }

    @Test
    void DataSetGwanboService_랜덤조회_테스트() {
        DataSetGwanbo randomize = dataSetGwanboService.findRandomize();
        assertThat(randomize.getSeq()).isNotNull();
    }

    @Test
    void DataSetGwanbo_컬럼_전체조회_By_Class() {
        List<DataSetColumnDesc> columns = CommonUtil.parseClassProperty(DataSetGwanbo.class);
        assertThat(columns).isNotNull();
        assertThat(columns.size()).isGreaterThan(0);
    }

    @Test
    void DataSetCCTV_컬럼_전체조회_By_Class() {
        List<DataSetColumnDesc> columns = CommonUtil.parseClassProperty(DataSetCCTV.class);
        assertThat(columns).isNotNull();
        assertThat(columns.size()).isGreaterThan(0);
    }

    @Test
    void DataSetGwanbo_컬럼_전체조회_By_String() {
        List<DataSetColumnDesc> columns = CommonUtil.parseClassProperty("DataSetGwanbo");
        assertThat(columns).isNotNull();
        assertThat(columns.size()).isGreaterThan(0);
    }

    @Test
    void DataSetGwanbo_Search() {
        ApiList apiList = apiListService.findBySeq(56).get();
        List<String> targetColumns = new ArrayList<>(Arrays.asList(apiList.getTargetColumn().split(",")));
        dataSetGwanboService.search(targetColumns, 1, 10);
    }
}
