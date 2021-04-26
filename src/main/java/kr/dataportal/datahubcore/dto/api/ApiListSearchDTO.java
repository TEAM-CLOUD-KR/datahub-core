package kr.dataportal.datahubcore.dto.api;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ApiListSearchDTO {
    private final Integer page;
    private final Integer itemPerPage;
    private final PermissionGroup permissionGroup;
    private final List<String> category;
    private final List<String> organization;
}
