package kr.dataportal.datahubcore.dto.dataset;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DataSetCreateDTO {
    private final String dataset;
    private final String datasetRaw;
    private final String datasetColumn;
}
