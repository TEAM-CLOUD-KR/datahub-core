package kr.dataportal.datahubcore.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiListPagingDTO {
    private final int page;
    private final int itemPerPage;
}
