package kr.dataportal.datahubcore.implement.service.dataset;

import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.interfaces.DataSetListInterface;
import kr.dataportal.datahubcore.implement.repository.dataset.DataSetListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataSetListService implements DataSetListInterface {
    private final DataSetListRepository dataSetListRepository;

    @Override
    public DataSetList findOne(String dataSetName) {
        return dataSetListRepository.findOne(dataSetName);
    }
}
