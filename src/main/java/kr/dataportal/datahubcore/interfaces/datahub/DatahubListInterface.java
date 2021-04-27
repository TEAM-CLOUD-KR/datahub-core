package kr.dataportal.datahubcore.interfaces.datahub;

import kr.dataportal.datahubcore.domain.datahub.DatahubList;

import java.util.List;
import java.util.Optional;

public interface DatahubListInterface {
    void save(DatahubList datahubList);

    List<DatahubList> findAll();

    Optional<DatahubList> fineBySeq(int seq);

    Optional<DatahubList> findByName(String name);

    List<String> getDatahubOrganization();
}
