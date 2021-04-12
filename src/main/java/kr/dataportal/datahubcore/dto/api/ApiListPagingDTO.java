package kr.dataportal.datahubcore.dto.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiListPagingDTO {
    private final int page;
    private final int itemPerPage;
}
