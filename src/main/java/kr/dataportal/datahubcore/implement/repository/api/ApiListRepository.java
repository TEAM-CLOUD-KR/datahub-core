package kr.dataportal.datahubcore.implement.repository.api;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.api.ApiList;
import kr.dataportal.datahubcore.domain.api.QApiList;
import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.domain.datahub.DatahubList;
import kr.dataportal.datahubcore.domain.map.QMapApiListDatahub;
import kr.dataportal.datahubcore.dto.api.ApiListSearchDTO;
import kr.dataportal.datahubcore.interfaces.api.ApiListInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ApiListRepository implements ApiListInterface {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ApiList> findBySeq(int seq) {
        List<ApiList> apiList = em.createQuery("" +
                " SELECT apilist FROM ApiList apilist" +
                " WHERE apilist.seq=:seq", ApiList.class)
                .setParameter("seq", seq)
                .getResultList();

        if (apiList.size() > 0) {
            return Optional.ofNullable(apiList.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public ApiList findByName(String name) {
        List<ApiList> apiList = em.createQuery("" +
                " SELECT apilist FROM ApiList apilist" +
                " WHERE apilist.name=:name", ApiList.class)
                .setParameter("name", name)
                .getResultList();

        if (apiList.size() > 0) {
            return apiList.get(0);
        } else {
            return null;
        }
    }

    private BooleanExpression eqName(String name) {
        if (name.isEmpty()) {
            return null;
        }
        return QApiList.apiList.name.eq(name);
    }

    private BooleanExpression isFilteredOwnDatahub(String datahubList) {
        return QMapApiListDatahub.mapApiListDatahub.datahubList.name.eq(datahubList);
    }

    private BooleanExpression isFilteredOwnDatahubs(List<String> datahubList) {
        return datahubList != null
                ? Expressions.anyOf(datahubList.stream().map(
                this::isFilteredOwnDatahub).toArray(BooleanExpression[]::new)
        )
                : null;
    }

    private BooleanExpression isFilteredCategory(String category) {
        return QApiList.apiList.category1st.text.eq(category);
    }

    private BooleanExpression isFilteredCategories(List<String> categories) {
        return categories != null
                ? Expressions.anyOf(categories.stream().map(
                this::isFilteredCategory).toArray(BooleanExpression[]::new)
        )
                : null;
    }

    private BooleanExpression isFilteredOrganization(String organization) {
        return QApiList.apiList.organization.eq(organization);
    }

    private BooleanExpression isFilteredOrganizations(List<String> organizations) {
        return organizations != null
                ? Expressions.anyOf(organizations.stream().map(
                this::isFilteredOrganization).toArray(BooleanExpression[]::new)
        )
                : null;
    }

    private BooleanExpression isFilteredApiName(String name) {
        return name == null || name.isBlank()
                ? null
                : QApiList.apiList.name.contains(name);
    }

    @Override
    public List<ApiList> search(ApiListSearchDTO searchDTO) {
        return queryFactory
                .selectFrom(QApiList.apiList)
                .leftJoin(QMapApiListDatahub.mapApiListDatahub)
                .on(QApiList.apiList.seq.eq(QMapApiListDatahub.mapApiListDatahub.apiList.seq))
                .where(
                        isFilteredCategories(searchDTO.getCategory()),
                        isFilteredOrganizations(searchDTO.getOrganization()),
                        isFilteredOwnDatahubs(searchDTO.getOwnDatahub()),
                        isFilteredApiName(searchDTO.getName())
                )
                .offset((long) (searchDTO.getPage() - 1) * searchDTO.getItemPerPage())
                .limit((long) searchDTO.getItemPerPage())
                .fetch();
    }

    @Override
    public Long getCount(ApiListSearchDTO searchDTO) {
        return queryFactory
                .selectFrom(QApiList.apiList)
                .leftJoin(QMapApiListDatahub.mapApiListDatahub)
                .on(QApiList.apiList.seq.eq(QMapApiListDatahub.mapApiListDatahub.apiList.seq))
                .where(
                        isFilteredCategories(searchDTO.getCategory()),
                        isFilteredOrganizations(searchDTO.getOrganization()),
                        isFilteredOwnDatahubs(searchDTO.getOwnDatahub()),
                        isFilteredApiName(searchDTO.getName())
                )
                .fetchCount();
    }

    @Override
    public void save(ApiList apiList) {
        em.persist(apiList);
    }

    @Override
    public List<String> getOrganizations() {
        return em.createQuery("" +
                " SELECT DISTINCT apilist.organization FROM ApiList apilist" +
                " WHERE apilist.organization IS NOT NULL" +
                " AND apilist.organization <> ''", String.class)
                .getResultList();
    }
}
