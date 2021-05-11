package kr.dataportal.datahubcore.interfaces.map;

import kr.dataportal.datahubcore.domain.map.MapApiListDatahub;
import kr.dataportal.datahubcore.domain.map.MapUserDatahub;

import java.util.List;

public interface MapApiListDatahubInterface {
    List<MapApiListDatahub> findAll(int userSeq);
    List<String> findAllDataHubNameByUserSeq(int userSeq);

    MapApiListDatahub findOne(String dataSetName);
}
