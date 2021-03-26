package kr.dataportal.datahubcore.domain.dataset.cctv;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Camera {
    @Column(name = "camera_cnt", length = 10, nullable = true)
    private final String cnt;

    @Column(name = "camera_pixel", length = 10, nullable = true)
    private final String pixel;

    @Column(name = "camera_angle", length = 20, nullable = true)
    private final String angle;

    public Camera() {
        this.cnt = null;
        this.pixel = null;
        this.angle = null;
    }
}
