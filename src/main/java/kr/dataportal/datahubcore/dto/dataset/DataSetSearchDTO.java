package kr.dataportal.datahubcore.dto.dataset;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class DataSetSearchDTO {
    private final List<String> targetColumns;
    private final Integer page;
    private final Integer itemPerPage;
}
