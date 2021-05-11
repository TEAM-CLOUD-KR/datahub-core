package kr.dataportal.datahubcore.interfaces.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.dto.api.ApiListSearchDTO;

import java.util.List;
import java.util.Optional;

public interface ApiListInterface {
    Optional<ApiList> findBySeq(int seq);

    ApiList findByName(String name);

    List<ApiList> search(ApiListSearchDTO searchDTO);

    Long getCount(ApiListSearchDTO searchDTO);

    void save(ApiList apiList);
    
    List<String> getOrganizations();
}
