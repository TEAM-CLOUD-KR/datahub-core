package kr.dataportal.datahubcore.domain.dashboard;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.api.ApiUsingList;
import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.domain.common.Category2nd;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dashboard_list")
@RequiredArgsConstructor
@Getter
public class DashBoardList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private int seq;

    @ManyToOne(targetEntity = ApiUsingList.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "target_api")
    private final ApiUsingList targetApi;

    @Column(name = "page")
    private final int page;

    @Column(name = "item_per_page")
    private final int itemPerPage;

    @Column(name = "labels")
    private final String labels;

    @Column(name = "datas")
    private final String datas;

    @Column(name = "type")
    private final String type;

    public DashBoardList() {
        this.targetApi = null;
        this.page = 1;
        this.itemPerPage = 10;
        this.labels = null;
        this.datas = null;
        this.type = null;
    }
}
