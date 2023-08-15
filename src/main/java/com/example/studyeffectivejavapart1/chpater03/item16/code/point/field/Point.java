package com.example.studyeffectivejavapart1.chpater03.item16.code.point.field;

public class Point {
    // 좋지 않은 방법
    public double x;
    public double y;

    public static void main(String[] args) {
        Point point = new Point();
        point.x = 10;
        point.y = 20;

        System.out.println(point.x);
        System.out.println(point.y);
    }
}
