package kr.dataportal.datahubcore.interfaces.dashboard;

import kr.dataportal.datahubcore.domain.dashboard.DashBoardList;

import java.util.List;

public interface DashBoardListInterface {
    int save(DashBoardList dashBoardList);

    DashBoardList findOne(int seq);

    List<DashBoardList> findAll();
}
