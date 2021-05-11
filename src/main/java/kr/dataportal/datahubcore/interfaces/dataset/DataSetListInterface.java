package kr.dataportal.datahubcore.interfaces.dataset;

import kr.dataportal.datahubcore.domain.dataset.DataSetList;

import java.util.List;

public interface DataSetListInterface {
    List<String> findAll(String name);
    DataSetList findOne(String dataSetName);
}
