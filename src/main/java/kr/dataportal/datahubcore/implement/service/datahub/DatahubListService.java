package kr.dataportal.datahubcore.implement.service.datahub;

import kr.dataportal.datahubcore.domain.datahub.DatahubList;
import kr.dataportal.datahubcore.interfaces.datahub.DatahubListInterface;
import kr.dataportal.datahubcore.implement.repository.datahub.DatahubListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DatahubListService implements DatahubListInterface {
    private final DatahubListRepository datahubListRepository;

    @Override
    public void save(DatahubList datahubList) {
        datahubListRepository.save(datahubList);
    }

    @Override
    public List<DatahubList> findAll() {
        return datahubListRepository.findAll();
    }

    @Override
    public Optional<DatahubList> fineBySeq(int seq) {
        return datahubListRepository.fineBySeq(seq);
    }

    @Override
    public Optional<DatahubList> findByName(String name) {
        return datahubListRepository.findByName(name);
    }
}
