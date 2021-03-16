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

    @Embedded
    private Publish publish;

    @Embedded
    private Category category;

    @Column(name = "binary", length = 255, nullable = false)
    private String binary;
}
