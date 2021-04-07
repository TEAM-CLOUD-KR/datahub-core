package kr.dataportal.datahubcore;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.Category;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.Organization;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.Publish;
import kr.dataportal.datahubcore.dto.ApiListPagingDTO;
import kr.dataportal.datahubcore.service.api.ApiListService;
import kr.dataportal.datahubcore.service.dataset.DataSetListService;
import kr.dataportal.datahubcore.service.dataset.cctv.DataSetCCTVService;
import kr.dataportal.datahubcore.service.dataset.gwanbo.DataSetGwanboService;
import kr.dataportal.datahubcore.util.CommonUtil;
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
    @Autowired
    private DataSetCCTVService dataSetCCTVService;

    @Autowired
    private DataSetListService dataSetListService;

    @Autowired
    private ApiListService apiListService;


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
    void DataSetGwanboService_랜덤조회_테스트() {
        DataSetGwanbo randomize = dataSetGwanboService.findRandomize();
        Assertions.assertThat(randomize.getSeq()).isNotNull();
    }

    @Test
    void DataSetGwanbo_컬럼_전체조회_By_Class() {
        List<String> columns = CommonUtil.parseClassProperty(DataSetGwanbo.class);
        Assertions.assertThat(columns).isNotNull();
        Assertions.assertThat(columns.size()).isGreaterThan(0);
    }

    @Test
    void DataSetCCTV_컬럼_전체조회_By_Class() {
        List<String> columns = CommonUtil.parseClassProperty(DataSetCCTV.class);
        Assertions.assertThat(columns).isNotNull();
        Assertions.assertThat(columns.size()).isGreaterThan(0);
    }

    @Test
    void DataSetGwanbo_컬럼_전체조회_By_String() {
        List<String> columns = CommonUtil.parseClassProperty("DataSetGwanbo");
        Assertions.assertThat(columns).isNotNull();
        Assertions.assertThat(columns.size()).isGreaterThan(0);
    }

    @Test
    void DataSetCCTV_컬럼_전체조회_By_String() {
        List<String> columns = CommonUtil.parseClassProperty("DataSetCCTV");
        Assertions.assertThat(columns).isNotNull();
        Assertions.assertThat(columns.size()).isGreaterThan(0);
    }

    @Test
    void ApiList_단건조회() {
        ApiList apiList = apiListService.findBySeq(4);
        Assertions.assertThat(apiList.getSeq()).isEqualTo(4);
    }

    @Test
    void DataSetList_findOne() {
        DataSetList dataSetList = dataSetListService.findOne("dataset_cctv");
        Assertions.assertThat(dataSetList.getDataSet()).isEqualTo("dataset_cctv");
    }

    @Test
    @Transactional(readOnly = false)
    @Rollback(value = true)
    ApiList ApiList_저장() {
        apiListService.save(new ApiList(
                "API 이름 테스트123",
                dataSetListService.findOne("dataset_cctv"),
                "@All",
                PermissionGroup.PERMISSION_PUBLIC,
                "API DESC",
                1,
                "컴퓨터정보과",
                4));
        ApiList apiList = apiListService.findByName("API 이름 테스트123");
        Assertions.assertThat(apiList).isNotNull();
        return apiList;
    }

    @Test
    @Transactional(readOnly = false)
    @Rollback(value = true)
    void ApiList_수정() {
        ApiList apiList = ApiList_저장();
        ApiList update = apiList.update(
                "API 이름 수정",
                dataSetListService.findOne("dataset_gwanbo"),
                "['A','B','C']",
                PermissionGroup.PERMISSION_DATAHUB,
                "API DESC 수정",
                2,
                "기관 수정"
        );
        Assertions.assertThat(update.getName()).isEqualTo("API 이름 수정");
    }

    @Test
    void ApiList_Paing() {
        List<ApiList> byPage = apiListService.findByPage(new ApiListPagingDTO(1, 10));

        Assertions.assertThat(byPage).isNotNull();
        Assertions.assertThat(byPage.size()).isGreaterThan(0);

    }
}
