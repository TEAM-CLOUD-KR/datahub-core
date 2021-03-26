package kr.dataportal.datahubcore.domain.dataset.cctv;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Organization {
    @Column(name = "organization_name", length = 100, nullable = false)
    private final String name;

    @Column(name = "organization_telephone", length = 30, nullable = false)
    private final String telephone;

    public Organization() {
        this.name = null;
        this.telephone = null;
    }
}
