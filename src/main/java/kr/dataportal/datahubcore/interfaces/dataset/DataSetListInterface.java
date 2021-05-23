package kr.dataportal.datahubcore.interfaces.dataset;

import kr.dataportal.datahubcore.domain.dataset.DataSetList;

import java.util.List;

public interface DataSetListInterface {
    int save(DataSetList dataSetList);

    List<DataSetList> findAll(String name);
    List<DataSetList> findAll(int seq);

    DataSetList findOne(String dataSetName);
    DataSetList findOne(int seq);
}
