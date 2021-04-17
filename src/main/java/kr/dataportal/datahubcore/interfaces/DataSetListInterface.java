package kr.dataportal.datahubcore.interfaces;

import kr.dataportal.datahubcore.domain.dataset.DataSetList;

public interface DataSetListInterface {
    DataSetList findOne(String dataSetName);
}
