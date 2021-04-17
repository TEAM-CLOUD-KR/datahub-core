package kr.dataportal.datahubcore.domain.datahub;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "datahub_list")
@Getter
@RequiredArgsConstructor
public class DatahubList {

    @Id
    @Column(name = "seq")
    private int seq;

    @Column(name = "name")
    private final String name;
}
