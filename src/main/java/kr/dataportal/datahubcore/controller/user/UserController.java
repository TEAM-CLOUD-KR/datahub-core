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

import io.swagger.annotations.Api;
import kr.dataportal.datahubcore.domain.user.User;
import kr.dataportal.datahubcore.dto.user.UserSignupDto;
import kr.dataportal.datahubcore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    @ApiIgnore
    public String SignUpAction(@RequestBody UserSignupDto user) {
        User u = User.create(user.getEmail(), user.getFirstPassword(), user.getSecondPassword(), user.getNickname());
        if (u == null) {
            return "ERROR";
        } else {
            userService.signup(u);
            return "";
        }
    }
}
