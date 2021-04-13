/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.controller.user;

import kr.dataportal.datahubcore.domain.datacore.JSONResponse;
import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.dto.user.UserSignInDto;
import kr.dataportal.datahubcore.dto.user.UserSignupDto;
import kr.dataportal.datahubcore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signin")
    public JSONResponse SignInAction(@RequestBody UserSignInDto user) {
        return switch (userService.signIn(user)) {
            case SUCCESS -> new JSONResponse(HttpStatus.OK, "로그인 성공");
            case WRONG_EMAIL -> new JSONResponse(HttpStatus.UNAUTHORIZED, "존재하지 않는 이메일입니다.");
            case WRONG_PASSWORD -> new JSONResponse(HttpStatus.UNAUTHORIZED, "암호가 일치하지 않습니다.");
            case FAIL -> new JSONResponse(HttpStatus.INTERNAL_SERVER_ERROR, "로그인 과정에서 알 수 없는 오류가 발생하였습니다.");
        };
    }

    @PostMapping("/signup")
    public JSONResponse SignUpAction(@RequestBody UserSignupDto user) {
        Optional<User> u = User.create(user.getEmail(), user.getFirstPassword(), user.getSecondPassword(), user.getNickname());
        return u.map(
                createUser -> switch (userService.signUp(createUser)) {
                    case SUCCESS -> new JSONResponse(HttpStatus.OK, "회원가입 성공");
                    case CONFLICT_EMAIL -> new JSONResponse(HttpStatus.CONFLICT, "이미 등록된 이메일입니다.");
                    case CONFLICT_NICKNAME -> new JSONResponse(HttpStatus.CONFLICT, "이미 등록된 닉네임입니다.");
                    case FAIL -> new JSONResponse(HttpStatus.INTERNAL_SERVER_ERROR, "회원가입 과정에서 알 수 없는 오류가 발생하였습니다.");
                }
        ).orElseGet(
                () -> new JSONResponse(HttpStatus.FORBIDDEN, "두 암호가 일치하지 않습니다.")
        );
    }
}
