/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.api;

import kr.dataportal.datahubcore.domain.api.ApiUsingList;
import kr.dataportal.datahubcore.implement.service.api.ApiListService;
import kr.dataportal.datahubcore.implement.service.api.ApiUsingAcceptService;
import kr.dataportal.datahubcore.implement.service.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = ("" +
        "spring.config.location=" +
        "classpath:/application.yml," +
        "optional:C:/repository/_secrets/datahub-core.yml," +
        "optional:/home/datahub/_secrets/datahub-core.yml," +
        "optional:/Users/sun/repository/_secrets/datahub-core.yml"
))
public class ApiUsingAcceptServiceTest {
    @Autowired
    private ApiUsingAcceptService apiUsingAcceptService;

    @Autowired
    private ApiListService apiListService;

    @Autowired
    private UserService userService;

    @Test
    void ApiUsingAcceptService_findByPublisher() {
        List<ApiUsingList> byPublisher = apiUsingAcceptService.findByPublisher(36);
        assertThat(byPublisher.size()).isGreaterThan(0);
    }

    @Test
    @Transactional(readOnly = false)
    @Rollback(value = true)
    void ApiUsingAcceptService_save() {
        ApiUsingList apiUsingList = new ApiUsingList(
                apiListService.findBySeq(59).get(),
                userService.findBySeq(36).get(),
                ""
        );
        int save = apiUsingAcceptService.save(apiUsingList);
        assertThat(save).isNotEqualTo(0);
    }
}