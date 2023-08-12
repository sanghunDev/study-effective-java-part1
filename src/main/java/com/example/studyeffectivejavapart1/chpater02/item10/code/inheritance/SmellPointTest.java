package com.example.studyeffectivejavapart1.chpater02.item10.code.inheritance;

import com.example.studyeffectivejavapart1.chpater02.item10.code.Color;

public class SmellPointTest {

    public static void main(String[] args) {
        SmellPoint p1 = new SmellPoint(1, 0, "sweat");
        ColorPoint p2 = new ColorPoint(1, 0, Color.RED);
        p1.equals(p2);
    }
}
