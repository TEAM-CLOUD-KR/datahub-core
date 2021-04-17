package kr.dataportal.datahubcore.interfaces;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.dto.api.ApiListPagingDTO;

import java.util.List;

public interface ApiListInterface {
    ApiList findBySeq(int seq);

    ApiList findByName(String name);

    List<ApiList> findByPage(ApiListPagingDTO pagingDTO);

    Long getCount();

    void save(ApiList apiList);
}
