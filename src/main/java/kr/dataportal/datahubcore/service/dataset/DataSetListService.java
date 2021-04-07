package kr.dataportal.datahubcore.service.dataset;

import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.repository.dataset.DataSetListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataSetListService {
    private final DataSetListRepository dataSetListRepository;

    public DataSetList findOne(String dataSetName) {
        return dataSetListRepository.findOne(dataSetName);
    }
}
