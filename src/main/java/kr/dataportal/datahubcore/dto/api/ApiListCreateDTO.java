package kr.dataportal.datahubcore.dto.api;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.domain.common.Category2nd;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiListCreateDTO {
    private final String name;
    private final String targetDataset;
    private final String targetColumn;
    private final PermissionGroup permissionGroup;
    private final String apiDesc;
    private final String category1st;
    private final String category2nd;
    private final String organization;
    private final int publisher;
}
