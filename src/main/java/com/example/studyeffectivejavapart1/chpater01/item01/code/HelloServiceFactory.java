package com.example.studyeffectivejavapart1.chpater01.item01.code;

public class HelloServiceFactory {

    public static void main(String[] args) {
        // 입력되는 파라미터에 따라 다른 구현체를 제공한다
        String hello = HelloService.of("ko").hello();
        System.out.println("hello = " + hello);
    }

}
