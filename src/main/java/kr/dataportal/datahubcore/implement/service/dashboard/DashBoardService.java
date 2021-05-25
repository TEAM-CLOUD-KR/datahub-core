package kr.dataportal.datahubcore.implement.service.dashboard;

import kr.dataportal.datahubcore.domain.dashboard.DashBoardList;
import kr.dataportal.datahubcore.implement.repository.dashboard.DashBoardRepository;
import kr.dataportal.datahubcore.interfaces.dashboard.DashBoardListInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBoardService implements DashBoardListInterface {
    private final DashBoardRepository dashBoardRepository;
    @Override
    public int save(DashBoardList dashBoardList) {
        return dashBoardRepository.save(dashBoardList);
    }

    @Override
    public DashBoardList findOne(int seq) {
        return dashBoardRepository.findOne(seq);
    }

    @Override
    public List<DashBoardList> findAll() {
        return dashBoardRepository.findAll();
    }
}
