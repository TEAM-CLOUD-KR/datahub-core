package kr.dataportal.datahubcore.dto.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.dto.dataset.DataSetColumnDesc;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ApiListDetailAndDataSetColumn {
    private final ApiList detail;
    private final List<Object> dataSetColumnDesc;

}
