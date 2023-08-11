package com.example.studyeffectivejavapart1.chpater01.item06.code;

public class Strings {

    public static void main(String[] args) {
        String hello = "hello";

        //TODO 이 방법은 권장하지 않습니다. (불필요한 객체 생성)
        String hello2 = new String("hello");

        String hello3 = "hello";

        System.out.println(hello == hello2);
        System.out.println(hello.equals(hello2));
        System.out.println(hello == hello3);
        System.out.println(hello.equals(hello3));
    }
}
