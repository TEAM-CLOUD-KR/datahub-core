package kr.dataportal.datahubcore.implement.service.map;

import kr.dataportal.datahubcore.domain.map.MapUserDatahub;
import kr.dataportal.datahubcore.implement.repository.map.MapUserDatahubRepository;
import kr.dataportal.datahubcore.interfaces.map.MapUserDatahubInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapUserDatahubService implements MapUserDatahubInterface {
    private final MapUserDatahubRepository mapUserDatahubRepository;

    @Override
    public List<MapUserDatahub> findAll(int userSeq) {
        return mapUserDatahubRepository.findAll(userSeq);
    }

    @Override
    public List<String> findAllDataHubNameByUserSeq(int userSeq) {
        return mapUserDatahubRepository.findAllDataHubNameByUserSeq(userSeq);
    }

    @Override
    public MapUserDatahub findOne(String dataSetName) {
        return null;
    }
}
