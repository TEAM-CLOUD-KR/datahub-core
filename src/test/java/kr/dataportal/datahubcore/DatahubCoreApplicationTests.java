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
import kr.dataportal.datahubcore.domain.user.SignInStatus;
import kr.dataportal.datahubcore.domain.user.SignUpStatus;
import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.dto.api.ApiListPagingDTO;
import kr.dataportal.datahubcore.dto.user.UserSignInDto;
import kr.dataportal.datahubcore.implement.service.api.ApiListService;
import kr.dataportal.datahubcore.implement.service.common.Category1stService;
import kr.dataportal.datahubcore.implement.service.common.Category2ndService;
import kr.dataportal.datahubcore.implement.service.datahub.DatahubListService;
import kr.dataportal.datahubcore.implement.service.dataset.DataSetListService;
import kr.dataportal.datahubcore.implement.service.dataset.cctv.DataSetCCTVService;
import kr.dataportal.datahubcore.implement.service.dataset.gwanbo.DataSetGwanboService;
import kr.dataportal.datahubcore.implement.service.user.UserService;
import kr.dataportal.datahubcore.util.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Autowired
    private DatahubListService datahubListService;


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
        List<String> columns = CommonUtil.parseClassProperty(DataSetGwanbo.class);
        assertThat(columns).isNotNull();
        assertThat(columns.size()).isGreaterThan(0);
    }

    @Test
    void DataSetCCTV_컬럼_전체조회_By_Class() {
        List<String> columns = CommonUtil.parseClassProperty(DataSetCCTV.class);
        assertThat(columns).isNotNull();
        assertThat(columns.size()).isGreaterThan(0);
    }

    @Test
    void DataSetGwanbo_컬럼_전체조회_By_String() {
        List<String> columns = CommonUtil.parseClassProperty("DataSetGwanbo");
        assertThat(columns).isNotNull();
        assertThat(columns.size()).isGreaterThan(0);
    }

    @Test
    void DataSetCCTV_컬럼_전체조회_By_String() {
        List<String> columns = CommonUtil.parseClassProperty("DataSetCCTV");
        assertThat(columns).isNotNull();
        assertThat(columns.size()).isGreaterThan(0);
    }

    @Test
    void ApiList_단건조회() {
        ApiList apiList = apiListService.findBySeq(14);
        assertThat(apiList.getSeq()).isEqualTo(14);
    }

    @Test
    void DataSetList_findOne() {
        DataSetList dataSetList = dataSetListService.findOne("dataset_cctv");
        assertThat(dataSetList.getDataSet()).isEqualTo("dataset_cctv");
    }

    @Test
    @Transactional(readOnly = false)
    @Rollback(value = true)
    void 회원가입테스트() {
        Optional<User> user_1 = User.create("test1@example.com", "pass1", "pass1", "abc1");

        user_1.ifPresent(user -> {
            assertThat(user).isNotNull();
            assertThat(user.getEmail()).isEqualTo("test1@example.com");

            SignUpStatus signup_1 = userService.signUp(user);
            assertThat(signup_1).isEqualTo(SignUpStatus.SUCCESS);

            Optional<User> byEmail = userService.findByEmail(user.getEmail());
            byEmail.ifPresent(findUser ->
                    assertThat(findUser.getNickname()).isEqualTo(user.getNickname())
            );
        });

        Optional<User> user_2 = User.create("test@example.com", "pass1", "pass2", "abc");
        assertThat(user_2.isEmpty()).isTrue();
    }

    @Test
    @Rollback(value = true)
    @Transactional(readOnly = false)
    void 로그인테스트() {
        Optional<User> user_1 = User.create("test@example.com", "pass1", "pass1", "abc");

        user_1.ifPresent(user -> {
            SignUpStatus signup_1 = userService.signUp(user);
            SignInStatus pass1 = userService.signIn(new UserSignInDto("test@example.com", "pass1"));
            assertThat(pass1).isEqualTo(SignInStatus.SUCCESS);
        });
    }

    @Test
    void 회원조회_BySeq() {
        User bySeq = userService.findBySeq(17).get();
        assertThat(bySeq.getSeq()).isEqualTo(17);
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
                datahubListService.fineBySeq(1).get(),
                userService.findBySeq(17).get()));
        ApiList apiList = apiListService.findByName("API 이름 테스트123");
        assertThat(apiList).isNotNull();
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
        assertThat(update.getName()).isEqualTo("API 이름 수정");
    }

    @Test
    void ApiList_Paging() {
        List<ApiList> byPage = apiListService.findByPage(new ApiListPagingDTO(1, 10));
        assertThat(byPage).isNotNull();
        assertThat(byPage.size()).isGreaterThan(0);
    }

    @Test
    void ApiList_GetCount() {
        Long count = apiListService.getCount();
        assertThat(count).isNotNull();
        assertThat(count).isGreaterThan(0);
    }
}
