package kr.dataportal.datahubcore.service.user;

import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.repository.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class UserService {
    private final UserRepository userRepository;

    public int signup(User user) {
        return userRepository.signup(user);
    }

    public User findBySeq(int seq) {
        return userRepository.findBySeq(seq);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
