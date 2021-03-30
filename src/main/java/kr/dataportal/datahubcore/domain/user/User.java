package kr.dataportal.datahubcore.domain.user;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class User {
    @Id
    @Column(name = "seq")
    private final int seq;

    @Column(name = "email", length = 255, unique = true)
    private final String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "nickname", length = 255, unique = true)
    private String nickname;

}