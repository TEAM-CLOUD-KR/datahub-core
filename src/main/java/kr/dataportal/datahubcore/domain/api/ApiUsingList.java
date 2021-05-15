package kr.dataportal.datahubcore.domain.api;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.domain.common.Category2nd;
import kr.dataportal.datahubcore.domain.datahub.DatahubList;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.util.CommonUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "api_using_list")
@Getter
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
    private final ApiUsingAcceptEnum accept = ApiUsingAcceptEnum.N;

    @Column(name = "service_key")
    private final String serviceKey;

    @Column(name = "purpose")
    private final String purpose;

    public ApiUsingList(ApiList api, User requestUser, String purpose) {
        this.api = api;
        this.requestUser = requestUser;
        this.serviceKey = CommonUtil.generateRandomAlphaNumber(64);
        this.purpose = purpose.isBlank() ? "" : purpose;
    }

    public ApiUsingList() {
        this.api = new ApiList();
        this.requestUser = new User();
        this.serviceKey = null;
        this.purpose = null;
    }
}
