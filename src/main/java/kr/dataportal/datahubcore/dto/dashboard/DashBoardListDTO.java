package kr.dataportal.datahubcore.dto.dashboard;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DashBoardListDTO {
    private final int apiSeq;

    private final int page;

    private final int itemPerPage;

    private final String labels;

    private final String datas;

    private final String type;
}
