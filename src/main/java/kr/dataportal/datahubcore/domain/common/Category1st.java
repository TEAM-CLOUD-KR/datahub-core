package kr.dataportal.datahubcore.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category_1st")
@RequiredArgsConstructor
@Getter
public class Category1st {
    @Id
    @Column(name = "text", length = 255)
    private final String text;

    public Category1st() {
        this.text = null;
    }
}
