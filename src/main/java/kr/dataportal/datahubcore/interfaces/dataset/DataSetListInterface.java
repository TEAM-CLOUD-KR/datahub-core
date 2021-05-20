package kr.dataportal.datahubcore.interfaces.dataset;

import kr.dataportal.datahubcore.domain.dataset.DataSetList;

import java.util.List;

public interface DataSetListInterface {
    void save(DataSetList dataSetList);

    List<DataSetList> findAll(String name);

    DataSetList findOne(String dataSetName);
}
