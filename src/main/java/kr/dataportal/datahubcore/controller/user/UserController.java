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
import kr.dataportal.datahubcore.domain.user.SignInStatus;
import kr.dataportal.datahubcore.domain.user.SignUpStatus;
import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.dto.user.SignInResponse;
import kr.dataportal.datahubcore.dto.user.UserSignInDto;
import kr.dataportal.datahubcore.dto.user.UserSignupDto;
import kr.dataportal.datahubcore.implement.service.map.MapUserDatahubService;
import kr.dataportal.datahubcore.implement.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MapUserDatahubService mapUserDatahubService;

    @PostMapping("/signin")
    public JSONResponse SignInAction(@RequestBody UserSignInDto user) {
        return switch (userService.signIn(user)) {
            case SUCCESS -> new JSONResponse(HttpStatus.OK, new SignInResponse(userService.findByEmail(user.getEmail()).get(), SignInStatus.SUCCESS));
            case WRONG_EMAIL -> new JSONResponse(HttpStatus.UNAUTHORIZED, new SignInResponse(null, SignInStatus.WRONG_EMAIL));
            case WRONG_PASSWORD -> new JSONResponse(HttpStatus.UNAUTHORIZED, new SignInResponse(null, SignInStatus.WRONG_PASSWORD));
            case FAIL -> new JSONResponse(HttpStatus.INTERNAL_SERVER_ERROR, new SignInResponse(null, SignInStatus.FAIL));
        };
    }

    @PostMapping("/signup")
    public JSONResponse SignUpAction(@RequestBody UserSignupDto user) {
        Optional<User> u = User.create(user.getEmail(), user.getFirstPassword(), user.getSecondPassword(), user.getNickname());
        return u.map(
                createUser -> switch (userService.signUp(createUser)) {
                    case SUCCESS -> new JSONResponse(HttpStatus.OK, SignUpStatus.SUCCESS);
                    case CONFLICT_EMAIL -> new JSONResponse(HttpStatus.CONFLICT, SignUpStatus.CONFLICT_EMAIL);
                    case CONFLICT_NICKNAME -> new JSONResponse(HttpStatus.CONFLICT, SignUpStatus.CONFLICT_NICKNAME);
                    case MISMATCH_PASSWORD -> null;
                    case FAIL -> new JSONResponse(HttpStatus.INTERNAL_SERVER_ERROR, SignUpStatus.FAIL);
                }
        ).orElseGet(
                () -> new JSONResponse(HttpStatus.FORBIDDEN, SignUpStatus.MISMATCH_PASSWORD)
        );
    }

    @GetMapping("/datahub")
    public JSONResponse UserDataHubNameAction(@RequestParam int userSeq) {
        return new JSONResponse(HttpStatus.OK, mapUserDatahubService.findAllDataHubName(userSeq));
    }
}
