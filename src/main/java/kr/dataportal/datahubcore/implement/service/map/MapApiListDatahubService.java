package kr.dataportal.datahubcore.implement.service.map;

import kr.dataportal.datahubcore.domain.map.MapApiListDatahub;
import kr.dataportal.datahubcore.domain.map.MapUserDatahub;
import kr.dataportal.datahubcore.implement.repository.map.MapApiListDatahubRepository;
import kr.dataportal.datahubcore.implement.repository.map.MapUserDatahubRepository;
import kr.dataportal.datahubcore.interfaces.map.MapApiListDatahubInterface;
import kr.dataportal.datahubcore.interfaces.map.MapUserDatahubInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapApiListDatahubService implements MapApiListDatahubInterface {
    private final MapApiListDatahubRepository mapApiListDatahubRepository;

    @Override
    public List<MapApiListDatahub> findAll(int userSeq) {
        return mapApiListDatahubRepository.findAll(userSeq);
    }

    @Override
    public List<String> findAllDataHubNameByUserSeq(int userSeq) {
        return mapApiListDatahubRepository.findAllDataHubNameByUserSeq(userSeq);
    }

    @Override
    public MapApiListDatahub findOne(String dataSetName) {
        return null;
    }
}
