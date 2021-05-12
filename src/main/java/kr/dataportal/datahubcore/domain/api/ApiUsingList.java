package kr.dataportal.datahubcore.domain.api;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.domain.common.Category2nd;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.domain.user.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_using_list")
@Getter
public class ApiUsingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private int seq;

}
