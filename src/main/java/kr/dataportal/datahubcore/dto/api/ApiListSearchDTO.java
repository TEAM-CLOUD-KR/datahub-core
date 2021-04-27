package kr.dataportal.datahubcore.dto.api;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.domain.datahub.DatahubList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ApiListSearchDTO {
    private final Integer page;
    private final Integer itemPerPage;
    private final List<DatahubList> ownDatahub;
    private final List<Category1st> category;
    private final List<String> organization;
    private final String name;
}
