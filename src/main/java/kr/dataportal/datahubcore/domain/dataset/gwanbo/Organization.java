/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License v2.1
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.domain.dataset.gwanbo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Organization {
    @Column(name = "organization_name", length = 45, nullable = false,
            columnDefinition = "organization_name Desc"
    )
    private final String name;

    @Column(name = "organization_code", length = 45, nullable = false,
            columnDefinition = "organization_code Desc"
    )
    private final String code;

    public Organization() {
        this.name = null;
        this.code = null;
    }
}