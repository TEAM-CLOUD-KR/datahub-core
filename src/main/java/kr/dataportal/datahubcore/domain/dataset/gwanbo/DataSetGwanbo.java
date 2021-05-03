/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html

    Contributors:
        Aaron(JIN, Taeyang) -
*/

package kr.dataportal.datahubcore.domain.dataset.gwanbo;

import lombok.*;
import org.springframework.context.annotation.Description;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dataset_gwanbo")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class DataSetGwanbo {
    @Column(name = "seq", length = 45, nullable = false,
            columnDefinition = "seq_desc"
    )
    @Id
    private final String seq;

    @Column(name = "ebook_no", length = 10, nullable = false,
            columnDefinition = "ebook_no Desc"
    )
    private final String ebookNo;

    @Embedded
    private final Publish publish;

    @Embedded
    private final Organization organization;

    @Embedded
    private final Category category;

    @Column(name = "law_name", length = 255, nullable = true,
            columnDefinition = "law_name Desc"
    )
    private final String lawName;

    @Column(name = "binary_file", length = 255, nullable = false,
            columnDefinition = "관보 PDF 파일의 저장 위치"
    )
    private final String binaryFile;

    public DataSetGwanbo() {
        this.seq = null;
        this.ebookNo = null;
        this.publish = new Publish();
        this.organization = new Organization();
        this.category = new Category();
        this.lawName = null;
        this.binaryFile = null;
    }
}
