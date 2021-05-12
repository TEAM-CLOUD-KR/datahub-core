package kr.dataportal.datahubcore.domain.api;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.domain.common.Category2nd;
import kr.dataportal.datahubcore.domain.datahub.DatahubList;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_using_list")
@Getter
@RequiredArgsConstructor
public class ApiUsingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private int seq;

    @ManyToOne(targetEntity = ApiList.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "api_seq")
    private final ApiList api;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "request_user")
    private final User requestUser;

    @Column(name = "accept")
    @Enumerated(EnumType.STRING)
    private ApiUsingAcceptEnum accept;

    public ApiUsingList() {
        this.api = new ApiList();
        this.requestUser = new User();
    }
}
