package kr.dataportal.datahubcore.domain.map;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.datahub.DatahubList;
import kr.dataportal.datahubcore.domain.user.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "map__api_datahub")
@Getter
public class MapApiListDatahub {
    @Id
    @Column(name = "seq")
    private int seq;

    @ManyToOne
    @JoinColumn(name = "api_list")
    private ApiList apiList;

    @ManyToOne
    @JoinColumn(name = "datahub")
    private DatahubList datahubList;
}
