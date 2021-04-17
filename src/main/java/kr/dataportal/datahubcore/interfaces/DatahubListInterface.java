package kr.dataportal.datahubcore.interfaces;

import kr.dataportal.datahubcore.domain.datahub.DatahubList;

import java.util.Optional;

public interface DatahubListInterface {
    void save(DatahubList datahubList);

    Optional<DatahubList> fineBySeq(int seq);
}
