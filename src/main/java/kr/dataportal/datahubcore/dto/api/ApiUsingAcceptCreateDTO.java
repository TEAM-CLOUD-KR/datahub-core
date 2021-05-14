package kr.dataportal.datahubcore.dto.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiUsingAcceptCreateDTO {
    private final int apiSeq;

    private final int userSeq;

    private final String purpose;
}
