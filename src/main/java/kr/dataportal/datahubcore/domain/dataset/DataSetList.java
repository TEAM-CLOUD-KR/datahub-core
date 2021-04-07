package kr.dataportal.datahubcore.domain.dataset;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dataset_list")
@Getter
public class DataSetList {
    @Id
    @Column(name = "dataset", length = 255)
    private String dataSet;
}
