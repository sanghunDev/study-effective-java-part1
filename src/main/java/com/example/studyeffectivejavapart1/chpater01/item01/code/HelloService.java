package com.example.studyeffectivejavapart1.chpater01.item01.code;

public interface HelloService {

    String hello();

    static HelloService of(String lang) {
        if (lang.equals("ko")) {
            return new KoreanHelloService();
        } else {
            return new EnglishHelloService();
        }
    }

    // java 8
    static String hi() {
        prepareMessage();
        return "hi";
    }

    // java 9 (인터페이스 내부 공통적인 작업을 public 으로 노출하고 싶지 않은 경우 사용)
    static private void prepareMessage() {
    }

    static String hi1() {
        prepareMessage();
        return "hi";
    }

    static String hi2() {
        prepareMessage();
        return "hi";
    }

    default String bye() {
        return "bye";
    }
}
