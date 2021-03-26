package kr.dataportal.datahubcore.domain.dataset.cctv;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Address {
    @Column(name = "address_road", length = 200, nullable = true)
    private final String road;

    @Column(name = "address_original", length = 200, nullable = true)
    private final String original;

    public Address() {
        this.road = null;
        this.original = null;
    }
}
