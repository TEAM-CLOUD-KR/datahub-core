package kr.dataportal.datahubcore.service.user;

import kr.dataportal.datahubcore.domain.user.SignInStatus;
import kr.dataportal.datahubcore.domain.user.SignUpStatus;
import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.dto.user.UserSignInDto;
import kr.dataportal.datahubcore.repository.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInStatus signIn(UserSignInDto user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), byEmail.get().getPassword())) {
                return SignInStatus.SUCCESS;
            } else {
                return SignInStatus.WRONG_PASSWORD;
            }
        } else {
            return SignInStatus.WRONG_EMAIL;
        }
    }

    @Transactional
    public SignUpStatus signUp(User user) {
        if (validateDuplicateEmail(user.getEmail())) {
            if (validateDuplicateNickName(user.getNickname())) {
                // 암호 인코딩 => Setter 사용. => 최적화 가능하면 최적화 필요
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.signUp(user);
                if (findByEmail(user.getEmail()).isPresent()) {
                    return SignUpStatus.SUCCESS;
                } else {
                    return SignUpStatus.FAIL;
                }
            } else {
                return SignUpStatus.CONFLICT_NICKNAME;
            }
        } else {
            return SignUpStatus.CONFLICT_EMAIL;
        }
    }

    public Optional<User> findBySeq(int seq) {
        return userRepository.findBySeq(seq);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    // 이메일이 중복되지 않으면 True 반환
    // 이메일이 중복되면 False 반환
    private boolean validateDuplicateEmail(String email) {
        Optional<User> optionalUser = this.findByEmail(email);
        return optionalUser.isEmpty();
    }

    // 닉네임이 중복되지 않으면 True 반환
    // 닉네임이 중복되면 False 반환
    private boolean validateDuplicateNickName(String nickname) {
        Optional<User> optionalUser = this.findByNickname(nickname);
        return optionalUser.isEmpty();
    }
}
