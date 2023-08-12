package com.example.studyeffectivejavapart1.chpater02.item10.code.composition;

import com.example.studyeffectivejavapart1.chpater02.item10.code.Color;
import com.example.studyeffectivejavapart1.chpater02.item10.code.Point;

import java.util.Objects;

// equals 규약을 지키면서 값 추가
public class ColorPoint {
    private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    /**
     * 이 ColorPoint의 Point 뷰를 반환한다.
     */
    public Point asPoint() {
        return point;
    }

    @Override public boolean equals(Object o) {
        if (!(o instanceof ColorPoint))
            return false;
        ColorPoint cp = (ColorPoint) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }

    @Override public int hashCode() {
        return 31 * point.hashCode() + color.hashCode();
    }
}
