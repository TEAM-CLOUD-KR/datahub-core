package kr.dataportal.datahubcore.dto.user;


import kr.dataportal.datahubcore.domain.user.SignInStatus;
import kr.dataportal.datahubcore.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SignInResponse {
    private final User user;
    private final SignInStatus status;
}
