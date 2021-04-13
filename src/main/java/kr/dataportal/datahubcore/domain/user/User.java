package kr.dataportal.datahubcore.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "user")
@Getter
public class User {
    @Id
    @Column(name = "seq")
    @ApiModelProperty(hidden = true)
    private int seq;

    @Column(name = "email", length = 255, unique = true)
    private final String email;

    @JsonIgnore
    @Setter
    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "nickname", length = 255, unique = true)
    private final String nickname;

    @Column(name = "reg_date")
    private final LocalDateTime regDate;

    @Column(name = "erase_date")
    private final LocalDateTime eraseDate;

    public User() {
        this.email = null;
        this.password = null;
        this.nickname = null;
        regDate = LocalDateTime.now();
        eraseDate = null;
    }

    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.regDate = LocalDateTime.now();
        this.eraseDate = null;
    }

    public static Optional<User> create(String email, String password_1, String password_2, String nickname) {
        if (password_1.equals(password_2)) {
            return Optional.of(new User(email, password_1, nickname));
        } else {
            return Optional.empty();
        }
    }
}