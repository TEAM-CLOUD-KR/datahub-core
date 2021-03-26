/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html

    Contributors:
        Aaron(JIN, Taeyang) -
*/

package kr.dataportal.datahubcore.domain.dataset.cctv;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dataset_cctv")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class DataSetCCTV {
    @Column(name = "seq", nullable = false)
    @Id
    private final int seq;

    @Embedded
    private final Organization organization;

    @Embedded
    private final Address address;

    @Column(name = "purpose", length = 100)
    private final String purpose;

    @Embedded
    private final Camera camera;

    @Column(name= "storage_peroid", length = 10, nullable = true)
    private final String storagePeriod;

    @Column(name ="regdate", length = 10, nullable = true)
    private final String regdate;

    @Column(name ="lat", length = 50, nullable = true)
    private final String lat;

    @Column(name ="lng", length = 50, nullable = true)
    private final String lng;

    @Column(name ="publish_date", length = 12, nullable = true)
    private final String publishDate;

    public DataSetCCTV() {
        this.seq = 0;
        this.organization = new Organization();
        this.address = new Address();
        this.purpose = null;
        this.camera = new Camera();
        this.storagePeriod = null;
        this.regdate = null;
        this.lat = null;
        this.lng = null;
        this.publishDate = null;
    }
}
