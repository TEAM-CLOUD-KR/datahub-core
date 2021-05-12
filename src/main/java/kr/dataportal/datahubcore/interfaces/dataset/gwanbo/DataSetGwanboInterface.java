package kr.dataportal.datahubcore.interfaces.dataset.gwanbo;

import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.dto.dataset.DataSetSearchDTO;

import java.util.List;

public interface DataSetGwanboInterface {
    DataSetGwanbo findBySeq(String seq);

    List<DataSetGwanbo> findAll();

    List<DataSetGwanbo> search(List<String> targetColumns, int page, int itemPerPage);

    DataSetGwanbo findRandomize();

    void save(DataSetGwanbo gwanbo);
}
