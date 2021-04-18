package kr.dataportal.datahubcore.api;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.common.Category2nd;
import kr.dataportal.datahubcore.dto.api.ApiListPagingDTO;
import kr.dataportal.datahubcore.implement.service.api.ApiListService;
import kr.dataportal.datahubcore.implement.service.common.Category1stService;
import kr.dataportal.datahubcore.implement.service.common.Category2ndService;
import kr.dataportal.datahubcore.implement.service.datahub.DatahubListService;
import kr.dataportal.datahubcore.implement.service.dataset.DataSetListService;
import kr.dataportal.datahubcore.implement.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    void ApiList_단건조회() {
        ApiList apiList = apiListService.findBySeq(14);
        assertThat(apiList.getSeq()).isEqualTo(14);
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
