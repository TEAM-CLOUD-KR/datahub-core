package kr.dataportal.datahubcore.repository.user;

import kr.dataportal.datahubcore.domain.user.SignInStatus;
import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.dto.user.UserSignInDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class UserRepository {
    private final EntityManager em;

    public int signUp(User user) {
        em.persist(user);
        return user.getSeq();
    }

    public Optional<User> findBySeq(int seq) {
        List<User> userList = em.createQuery("" +
                " SELECT user FROM User user" +
                " WHERE user.seq =: seq", User.class)
                .setParameter("seq", seq)
                .getResultList();

        if (userList.size() > 0)
            return Optional.ofNullable(userList.get(0));
        else
            return Optional.empty();
    }

    public Optional<User> findByEmail(String email) {
        List<User> userList = em.createQuery("" +
                " SELECT user FROM User user" +
                " WHERE user.email =: email", User.class)
                .setParameter("email", email)
                .getResultList();

        if (userList.size() > 0)
            return Optional.ofNullable(userList.get(0));
        else
            return Optional.empty();
    }

    public Optional<User> findByNickname(String nickname) {
        List<User> userList = em.createQuery("" +
                " SELECT user FROM User user" +
                " WHERE user.nickname =: nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();

        if (userList.size() > 0)
            return Optional.ofNullable(userList.get(0));
        else
            return Optional.empty();
    }
}
