package kr.dataportal.datahubcore.domain.dataset;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "dataset_list")
@Getter
public class DataSetList {
    @Id
    @Column(name = "dataset", length = 255)
    private String dataset;
}
