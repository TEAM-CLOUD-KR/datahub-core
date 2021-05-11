package kr.dataportal.datahubcore.domain.map;

import io.swagger.annotations.ApiModelProperty;
import kr.dataportal.datahubcore.domain.datahub.DatahubList;
import kr.dataportal.datahubcore.domain.user.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "map__user_datahub")
@Getter
public class MapUserDatahub {
    @Id
    @Column(name = "seq")
    private int seq;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "datahub")
    private DatahubList datahubList;
}
