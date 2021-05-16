package kr.dataportal.datahubcore.interfaces.dataset.cctv;

import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;

import java.util.List;

public interface DataSetCCTVInterface {
    DataSetCCTV findBySeq(String seq);

    List<DataSetCCTV> findAll();

    List<DataSetCCTV> findByPage(int page, int itemPerPage);

    List<DataSetCCTV> search(List<String> targetColumns, int page, int itemPerPage);

    DataSetCCTV findRandomize();

    void save(DataSetCCTV cctv);
}
