package kr.dataportal.datahubcore.domain.api.custom_list;

import kr.dataportal.datahubcore.domain.PermissionGroup;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "api_custom_list")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class APICustomList {
    @Id
    @Column(name = "seq")
    private final int seq;

    @Column(name = "name", length = 255)
    private final String name;

    @Column(name = "target_dataset")
    private String targetDataset;

    @Column(name = "target_column")
    private String targetColumn;

    @Column(name="permission_group")
    @Enumerated(EnumType.STRING)
    private PermissionGroup permissionGroup;
}
