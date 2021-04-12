package kr.dataportal.datahubcore;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.common.Category2nd;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.Category;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.Organization;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.Publish;
import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.dto.api.ApiListPagingDTO;
import kr.dataportal.datahubcore.service.api.ApiListService;
import kr.dataportal.datahubcore.service.common.Category1stService;
import kr.dataportal.datahubcore.service.common.Category2ndService;
import kr.dataportal.datahubcore.service.dataset.DataSetListService;
import kr.dataportal.datahubcore.service.dataset.cctv.DataSetCCTVService;
import kr.dataportal.datahubcore.service.dataset.gwanbo.DataSetGwanboService;
import kr.dataportal.datahubcore.service.user.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private Category1stService category1stService;

    @Autowired
    private Category2ndService category2ndService;


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
    void 회원가입테스트() {
        User user_1 = User.create("test@example.com", "pass1", "pass1", "abc");
        Assertions.assertThat(user_1).isNotNull();
        Assertions.assertThat(user_1.getEmail()).isEqualTo("test@example.com");

        int signup_1 = userService.signup(user_1);
        Assertions.assertThat(signup_1).isNotNull();

        Assertions.assertThat(userService.findByEmail(user_1.getEmail()).getNickname()).isEqualTo(user_1.getNickname());

        User user_2 = User.create("test@example.com", "pass1", "pass2", "abc");
        Assertions.assertThat(user_2).isNull();
    }

    @Test
    void 회원조회_BySeq() {
        User bySeq = userService.findBySeq(4);
        Assertions.assertThat(bySeq.getSeq()).isEqualTo(4);
    }

    @Test
    @Transactional(readOnly = false)
    @Rollback(value = true)
    void ApiList_저장() {
        Category2nd category2nd = category2ndService.findOne("테스트");
        apiListService.save(new ApiList(
                "API 이름 테스트123",
                dataSetListService.findOne("dataset_cctv"),
                "@All",
                PermissionGroup.PERMISSION_PUBLIC,
                "API DESC",
                category2nd.getParent(),
                category2nd,
                "컴퓨터정보과",
                userService.findBySeq(4)));
        ApiList apiList = apiListService.findByName("API 이름 테스트123");
        Assertions.assertThat(apiList).isNotNull();
    }

    @Test
    @Transactional(readOnly = false)
    @Rollback(value = true)
    void ApiList_수정() {
        ApiList apiList = apiListService.findBySeq(14);
        ApiList update = apiList.update(
                "API 이름 수정",
                dataSetListService.findOne("dataset_gwanbo"),
                "['A','B','C']",
                PermissionGroup.PERMISSION_DATAHUB,
                "API DESC 수정",
                apiList.getCategory1st(),
                apiList.getCategory2nd(),
                "기관 수정"
        );
        Assertions.assertThat(update.getName()).isEqualTo("API 이름 수정");
    }

    @Test
    void ApiList_Paging() {
        List<ApiList> byPage = apiListService.findByPage(new ApiListPagingDTO(1, 10));
        Assertions.assertThat(byPage).isNotNull();
        Assertions.assertThat(byPage.size()).isGreaterThan(0);
    }
}
