package kr.dataportal.datahubcore.domain.dataset;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dataset_list")
@Getter
@RequiredArgsConstructor
public class DataSetList {
    @Id
    @Column(name = "dataset", length = 255)
    private final String dataset;

    @Column(name = "dataset_raw")
    private final String datasetRaw;

    @Column(name = "dataset_column")
    private final String datasetColumn;

    @Column(name = "desc")
    private final String desc;

    public DataSetList() {
        this.dataset = null;
        this.datasetRaw = null;
        this.datasetColumn = null;
        this.desc = null;
    }
}
