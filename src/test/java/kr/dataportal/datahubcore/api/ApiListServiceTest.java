package kr.dataportal.datahubcore.api;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.api.ApiUsingList;
import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.domain.common.Category2nd;
import kr.dataportal.datahubcore.domain.datahub.DatahubList;
import kr.dataportal.datahubcore.dto.api.ApiListSearchDTO;
import kr.dataportal.datahubcore.implement.service.api.ApiListService;
import kr.dataportal.datahubcore.implement.service.common.Category1stService;
import kr.dataportal.datahubcore.implement.service.common.Category2ndService;
import kr.dataportal.datahubcore.implement.service.datahub.DatahubListService;
import kr.dataportal.datahubcore.implement.service.dataset.DataSetListService;
import kr.dataportal.datahubcore.implement.service.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class ApiListServiceTest {
    @Autowired
    private ApiListService apiListService;

    @Autowired
    private DataSetListService dataSetListService;

    @Autowired
    private Category1stService category1stService;

    @Autowired
    private Category2ndService category2ndService;

    @Autowired
    private DatahubListService datahubListService;

    @Autowired
    private UserService userService;

    @Test
    @Transactional(readOnly = false)
    @Rollback(value = true)
    void Create_New_Api() {
        Category2nd category2nd = category2ndService.findOne("테스트");
        apiListService.save(new ApiList(
                "API 이름 테스트123",
                dataSetListService.findOne("datasetcctv"),
                "@All",
                PermissionGroup.PERMISSION_PUBLIC,
                "API DESC",
                category2nd.getParent(),
                category2nd,
                "컴퓨터정보과",
                userService.findBySeq(71).get())
        );
        ApiList apiList = apiListService.findByName("API 이름 테스트123");
        assertThat(apiList).isNotNull();
    }

    @Test
    @Transactional(readOnly = false)
    @Rollback(value = true)
    void Create_New_Api_with_null() {
        int seq = apiListService.save(new ApiList(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null)
        );
        Optional<ApiList> bySeq = apiListService.findBySeq(seq);
        assertThat(bySeq.isPresent()).isTrue();
    }

//    @Test
//    @Transactional(readOnly = false)
//    @Rollback(value = true)
//    void ApiList_수정() {
//        ApiList apiList = apiListService.findBySeq(56).get();
//        ApiList update = apiList.update(
//                "API 이름 수정",
//                dataSetListService.findOne("dataset_gwanbo"),
//                "['A','B','C']",
//                PermissionGroup.PERMISSION_DATAHUB,
//                "API DESC 수정",
//                apiList.getCategory1st(),
//                apiList.getCategory2nd(),
//                "기관 수정",
//                "TEST"
//        );
//        assertThat(update.getName()).isEqualTo("API 이름 수정");
//    }

//    @Test
//    void ApiList_단건조회() {
//        ApiList apiList = apiListService.findBySeq(56).get();
//        assertThat(apiList.getSeq()).isEqualTo(56);
//    }

//    @Test
//    void ApiList_Paging() {
//        List<ApiList> byPage = apiListService.search(new ApiListSearchDTO(1, 10, PermissionGroup.PERMISSION_PUBLIC, ));
//        assertThat(byPage).isNotNull();
//        assertThat(byPage.size()).isGreaterThan(0);
//    }

//    @Test
//    void ApiList_Search() {
//        List<String> filteredDatahubList = new ArrayList<>();
//        List<String> filteredCategory1st = new ArrayList<>();
//        List<String> filteredOrganization = new ArrayList<>();
//
//        List<ApiList> search1 = apiListService.search(new ApiListSearchDTO(
//                1, 10, filteredDatahubList, filteredCategory1st, filteredOrganization, ""
//        ));
//
//        Assertions.assertThat(search1.size()).isGreaterThan(0);
//
//        filteredDatahubList.add(datahubListService.fineBySeq(1).get().getName());
//
//        filteredCategory1st.add(category1stService.findOne("과학기술").getText());
//        filteredCategory1st.add(category1stService.findOne("교육").getText());
//
//        List<ApiList> search2 = apiListService.search(new ApiListSearchDTO(
//                1, 10, filteredDatahubList, filteredCategory1st, filteredOrganization, "가나다라마바사"
//        ));
//
//        Assertions.assertThat(search2.size()).isEqualTo(1);
//    }

//    @Test
//    void ApiList_GetCount() {
//        Long count = apiListService.getCount(new ApiListSearchDTO(
//                1, 10, null, null, null, null
//        ));
//        assertThat(count).isNotNull();
//        assertThat(count).isGreaterThan(0);
//
//        Long count1 = apiListService.getCount(new ApiListSearchDTO(
//                1, 10, null, null, null, "가나다라마바사"
//        ));
//        assertThat(count1).isNotNull();
//        assertThat(count1).isEqualTo(1);
//    }

    @Test
    void ApiList_findByPublisher() {
        List<ApiUsingList> byPublisher = apiListService.findByPublisher(36);
        assertThat(byPublisher.size()).isGreaterThan(0);
    }

//    @Test
//    void ApiList_findByUserAndPath() {
//        Optional<ApiList> byUserAndPath = apiListService.findByUserAndPath("a", "b");
//        byUserAndPath.ifPresent(
//                item -> assertThat(item.getSeq()).isEqualTo(14)
//        );
//    }
}
