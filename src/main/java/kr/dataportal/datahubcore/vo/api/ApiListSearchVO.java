package kr.dataportal.datahubcore.vo.api;

import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.domain.datahub.DatahubList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ApiListSearchVO {
    public final List<DatahubList> ownDatahub;
    public final List<Category1st> category;
    public final List<String> organization;
    public final Long itemCount;
    public final List<ApiList> items;
}
