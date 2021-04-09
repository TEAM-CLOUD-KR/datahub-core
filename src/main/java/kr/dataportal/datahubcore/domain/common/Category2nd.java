package kr.dataportal.datahubcore.domain.common;

import kr.dataportal.datahubcore.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "category_2nd")
@RequiredArgsConstructor
@Getter
public class Category2nd {
    @Id
    @Column(name = "text", length = 255)
    private final String text;

    @ManyToOne(targetEntity = Category1st.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent")
    private final Category1st parent;

    public Category2nd() {
        this.text = null;
        this.parent = new Category1st();
    }
}
