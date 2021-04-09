package kr.dataportal.datahubcore.domain.api;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.domain.common.Category2nd;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
import kr.dataportal.datahubcore.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_list")
@Getter
public class ApiList {
    @Id
    @Column(name = "seq")
    private int seq;

    @Column(name = "name", length = 255)
    private String name;

    @ManyToOne(targetEntity = DataSetList.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "target_dataset")
    private DataSetList targetDataset;

    @Column(name = "target_column")
    private String targetColumn;

    @Column(name = "permission_group")
    @Enumerated(EnumType.STRING)
    private PermissionGroup permissionGroup;

    @Column(name = "api_desc")
    private String apiDesc;

    @ManyToOne(targetEntity = Category1st.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_1st")
    private Category1st category1st;

    @ManyToOne(targetEntity = Category2nd.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_2nd")
    private Category2nd category2nd;

    @Column(name = "organization")
    private String organization;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher")
    private final User publisher;

    @Column(name = "publish_at", nullable = true)
    private final LocalDateTime publish_at;

    @Column(name = "last_edit", nullable = true)
    private final LocalDateTime last_edit;

    public ApiList() {
        this.name = null;
        this.targetDataset = null;
        this.targetColumn = null;
        this.permissionGroup = null;
        this.apiDesc = null;
        this.category1st = null;
        this.category2nd = null;
        this.organization = null;
        this.publisher = null;
        this.publish_at = LocalDateTime.now();
        this.last_edit = LocalDateTime.now();
    }

    public ApiList(String name, DataSetList targetDataset, String targetColumn,
                   PermissionGroup permissionGroup, String apiDesc, Category1st category1st,
                   Category2nd category2nd, String organization, User publisher) {
        this.name = name;
        this.targetDataset = targetDataset;
        this.targetColumn = targetColumn;
        this.permissionGroup = permissionGroup;
        this.apiDesc = apiDesc;
        this.category1st = category1st;
        this.category2nd = category2nd;
        this.organization = organization;
        this.publisher = publisher;
        this.publish_at = LocalDateTime.now();
        this.last_edit = LocalDateTime.now();
    }

    public ApiList update(String name, DataSetList targetDataset, String targetColumn,
                          PermissionGroup permissionGroup, String apiDesc,
                          Category1st category1st, Category2nd category2nd, String organization) {
        this.name = name;
        this.targetDataset = targetDataset;
        this.targetColumn = targetColumn;
        this.permissionGroup = permissionGroup;
        this.apiDesc = apiDesc;
        this.category1st = category1st;
        this.category2nd = category2nd;
        this.organization = organization;

        return this;
    }
}
