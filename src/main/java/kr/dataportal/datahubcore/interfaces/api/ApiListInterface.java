package kr.dataportal.datahubcore.interfaces.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.dto.api.ApiListSearchDTO;

import java.util.List;

public interface ApiListInterface {
    ApiList findBySeq(int seq);

    ApiList findByName(String name);

    List<ApiList> findByPage(ApiListSearchDTO searchDTO);

    Long getCount();

    void save(ApiList apiList);
}
