package com.example.studyeffectivejavapart1.chpater02.item10.code.record;

public class PointTest {

    public static void main(String[] args) {
        PointRecord p1 = new PointRecord(1, 0);
        PointRecord p2 = new PointRecord(1, 0);
        System.out.println(p1.equals(p2));
        System.out.println(p1);

        System.out.println(p1.x());
        System.out.println(p1.y());
    }
}
