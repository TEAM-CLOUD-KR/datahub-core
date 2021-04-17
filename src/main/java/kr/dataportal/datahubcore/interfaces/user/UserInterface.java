package kr.dataportal.datahubcore.interfaces.user;

import kr.dataportal.datahubcore.domain.user.SignUpStatus;
import kr.dataportal.datahubcore.domain.user.User;

import java.util.Optional;

public interface UserInterface {
    Optional<User> findBySeq(int seq);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);
}
