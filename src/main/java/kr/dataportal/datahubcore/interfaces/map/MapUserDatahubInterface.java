package kr.dataportal.datahubcore.interfaces.map;

import kr.dataportal.datahubcore.domain.map.MapUserDatahub;

import java.util.List;

public interface MapUserDatahubInterface {
    List<MapUserDatahub> findAll(int userSeq);
    List<String> findAllDataHubName(int userSeq);

    MapUserDatahub findOne(String dataSetName);
}
