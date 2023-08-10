package com.example.studyeffectivejavapart1.chpater01.item03.code.enumtype;

// 열거 타입 방식의 싱글턴
// 가장 권장되는 바람직한 방법 , 테스트가 불편해지면 인터페이스를 생성 후 구현해서 테스트 실행
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("기다려 자기야, 지금 나갈께!");
    }

    // 이 메서드는 보통 클래스 바깥(다른 클래스)에 작성해야 한다!
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
