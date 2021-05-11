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
public class Publish {
    @Column(name = "publish_seq", length = 45, nullable = false,
            columnDefinition = "관보 고유번호"
    )
    private final String seq;

    @Column(name = "publish_subject", length = 500, nullable = false,
            columnDefinition = "관보 제목"
    )
    private final String subject;

    @Column(name = "publish_regdate", length = 8, nullable = false,
            columnDefinition = "관보 발행일자"
    )
    private final String regdate;

    public Publish() {
        this.seq = null;
        this.subject = null;
        this.regdate = null;
    }
}
