package kr.dataportal.datahubcore.implement.service.dataset;

import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.interfaces.dataset.DataSetListInterface;
import kr.dataportal.datahubcore.implement.repository.dataset.DataSetListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataSetListService implements DataSetListInterface {
    private final DataSetListRepository dataSetListRepository;

    @Override
    public int save(DataSetList dataSetList) {
        return dataSetListRepository.save(dataSetList);
    }

    @Override
    public List<DataSetList> findAll(String name) {
        return dataSetListRepository.findAll(name);
    }

    @Override
    public List<DataSetList> findAll(int seq) {
        return dataSetListRepository.findAll(seq);
    }

    @Override
    public DataSetList findOne(String dataSetName) {
        return dataSetListRepository.findOne(dataSetName);
    }

    @Override
    public DataSetList findOne(int seq) {
        return dataSetListRepository.findOne(seq);
    }
}
