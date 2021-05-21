package kr.dataportal.datahubcore.domain.dataset;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dataset_list")
@Getter
public class DataSetList {
    @Id
    @Column(name = "dataset", length = 255)
    private final String dataset;

    @Column(name = "dataset_raw")
    private final String datasetRaw;

    @Column(name = "dataset_column")
    private final String datasetColumn;

    @Column(name = "description")
    private final String description;


    public DataSetList(String dataset, String datasetRaw, String datasetColumn) {
        this.dataset = dataset;
        this.datasetRaw = datasetRaw;
        this.datasetColumn = datasetColumn;
        this.description = null;
    }

    public DataSetList(String dataset, String datasetRaw, String datasetColumn, String description) {
        this.dataset = dataset;
        this.datasetRaw = datasetRaw;
        this.datasetColumn = datasetColumn;
        this.description = description;
    }

    public DataSetList() {
        this.dataset = null;
        this.datasetRaw = null;
        this.datasetColumn = null;
        this.description = null;
    }
}
