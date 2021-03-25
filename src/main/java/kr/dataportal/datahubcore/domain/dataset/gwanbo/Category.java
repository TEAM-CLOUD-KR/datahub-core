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
public class Category {
    @Column(name = "category_name", length = 45, nullable = false)
    private final String name;

    @Column(name = "category_seq", length = 45, nullable = false)
    private final String seq;

    public Category() {
        this.name = null;
        this.seq = null;
    }
}