package kr.dataportal.datahubcore.user;

import kr.dataportal.datahubcore.domain.user.SignInStatus;
import kr.dataportal.datahubcore.domain.user.SignUpStatus;
import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.dto.user.UserSignInDto;
import kr.dataportal.datahubcore.implement.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = ("" +
        "spring.config.location=" +
        "classpath:/application.yml," +
        "optional:C:/repository/_secrets/datahub-core.yml," +
        "optional:/home/datahub/_secrets/datahub-core.yml," +
        "optional:/Users/sun/repository/_secrets/datahub-core.yml"
))
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @Transactional(readOnly = false)
    @Rollback(value = true)
    void 회원가입테스트() {
        Optional<User> user_1 = User.create("test1@example.com", "pass1", "pass1", "abc1");

        user_1.ifPresent(user -> {
            assertThat(user).isNotNull();
            assertThat(user.getEmail()).isEqualTo("test1@example.com");

            SignUpStatus signup_1 = userService.signUp(user);
            assertThat(signup_1).isEqualTo(SignUpStatus.SUCCESS);

            Optional<User> byEmail = userService.findByEmail(user.getEmail());
            byEmail.ifPresent(findUser ->
                    assertThat(findUser.getNickname()).isEqualTo(user.getNickname())
            );
        });

        Optional<User> user_2 = User.create("test@example.com", "pass1", "pass2", "abc");
        assertThat(user_2.isEmpty()).isTrue();
    }

    @Test
    @Rollback(value = true)
    @Transactional(readOnly = false)
    void 로그인테스트() {
        Optional<User> user_1 = User.create("test@example.com", "pass1", "pass1", "abc");

        user_1.ifPresent(user -> {
            SignUpStatus signup_1 = userService.signUp(user);
            SignInStatus pass1 = userService.signIn(new UserSignInDto("test@example.com", "pass1"));
            assertThat(pass1).isEqualTo(SignInStatus.SUCCESS);
        });
    }

    @Test
    void 회원조회_BySeq() {
        User bySeq = userService.findBySeq(17).get();
        assertThat(bySeq.getSeq()).isEqualTo(17);
    }

    @Test
    void 대시보드아이템수정_테스트() {
        User user = userService.findByEmail("sun@codefor.kr").get();
        user.updateDashboardContent("TEST");

        assertThat(user.getDashboardContent()).isEqualTo("TEST");
    }
}
