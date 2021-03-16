/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License v2.1
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.domain.dataset;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Publish {
    @Column(name = "publish_id", length = 45, nullable = false)
    private final String id;

    @Column(name = "publish_title", length = 500, nullable = false)
    private final String title;

    @Column(name = "publish_created_at", length = 8, nullable = false)
    private final String createdAt;

    @Column(name = "publish_sequence", length = 45, nullable = true)
    private final String sequence;

    @Column(name = "publish_author", length = 500, nullable = true)
    private final String author;

    public Publish() {
        this.id = null;
        this.title = null;
        this.createdAt = null;
        this.sequence = null;
        this.author = null;
    }
}
