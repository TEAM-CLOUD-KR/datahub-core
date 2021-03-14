/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html

    Contributors:
        Aaron(JIN, Taeyang) -
*/

package kr.dataportal.datahubcore.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dataset_gwanbo")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSetGwanbo {
    @Column(name = "id", length = 45, nullable = false)
    @Id
    private String id;

    @Column(name = "publish_id", length = 45, nullable = false)
    private String publishId;

    @Column(name = "publish_title", length = 500, nullable = false)
    private String publishTitle;

    @Column(name = "publish_created_at", length = 8, nullable = false)
    private String createdAt;

    @Column(name = "publish_sequence", length = 45, nullable = true)
    private String publishSequence;

    @Column(name = "publish_author", length = 500, nullable = true)
    private String publishAuthor;

    @Column(name = "category_name", length = 45, nullable = false)
    private String categoryName;

    @Column(name = "category_id", length = 45, nullable = false)
    private String categoryId;

    @Column(name = "binary", length = 255, nullable = false)
    private String binary;
}
