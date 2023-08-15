package com.example.studyeffectivejavapart1.chpater03.item16.code.point.method;

// 접근자와 변경자(mutator) 메서드를 활용해 데이터를 캡슐화한다.
class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // 메서드를 통해 접근 (필드명을 변경해도 문제가 없고 부가 작업도 처리 가능)
    public double getX() {
        // 부가 작업
        return x;
    }
    public double getY() { return y; }

    public void setX(double x) {
        // 부가 작업
        this.x = x;
    }
    public void setY(double y) { this.y = y; }
}
