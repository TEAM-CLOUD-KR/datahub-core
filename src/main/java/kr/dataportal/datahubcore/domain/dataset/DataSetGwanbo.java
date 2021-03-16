/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html

    Contributors:
        Aaron(JIN, Taeyang) -
*/

package kr.dataportal.datahubcore.domain.dataset;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dataset_gwanbo")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class DataSetGwanbo {
    @Column(name = "id", length = 45, nullable = false)
    @Id
    private final String id;

    @Embedded
    private final Publish publish;

    @Embedded
    private final Category category;

    @Column(name = "binary_file", length = 255, nullable = false)
    private final String binaryFile;

    public DataSetGwanbo() {
        this.id = null;
        this.publish = null;
        this.category = null;
        this.binaryFile = null;
    }
}
