package kr.dataportal.datahubcore.domain.api;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import kr.dataportal.datahubcore.domain.dataset.DataSetList;
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

    @OneToOne(targetEntity = DataSetList.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "target_dataset")
    private DataSetList targetDataset;

    @Column(name = "target_column")
    private String targetColumn;

    @Column(name = "permission_group")
    @Enumerated(EnumType.STRING)
    private PermissionGroup permissionGroup;

    @Column(name = "api_desc")
    private String apiDesc;

    @Column(name = "category")
    private int category;

    @Column(name = "organization")
    private String organization;

    @Column(name = "publisher")
    private final int publisher;

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
        this.category = 0;
        this.organization = null;
        this.publisher = 0;
        this.publish_at = LocalDateTime.now();
        this.last_edit = LocalDateTime.now();
    }

    public ApiList(String name, DataSetList targetDataset, String targetColumn, PermissionGroup permissionGroup,
                   String apiDesc, int category, String organization, int publisher) {
        this.name = name;
        this.targetDataset = targetDataset;
        this.targetColumn = targetColumn;
        this.permissionGroup = permissionGroup;
        this.apiDesc = apiDesc;
        this.category = category;
        this.organization = organization;
        this.publisher = publisher;
        this.publish_at = LocalDateTime.now();
        this.last_edit = LocalDateTime.now();
    }

    public ApiList update(String name, DataSetList targetDataset, String targetColumn,
                          PermissionGroup permissionGroup, String apiDesc, int category, String organization) {
        this.name = name;
        this.targetDataset = targetDataset;
        this.targetColumn = targetColumn;
        this.permissionGroup = permissionGroup;
        this.apiDesc = apiDesc;
        this.category = category;
        this.organization = organization;

        return this;
    }
}
