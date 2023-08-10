package com.example.studyeffectivejavapart1.chpater01.item03.code.functionalinterface;

// 해당 어노테이션을 사용하면 컴파일시 함수형 인터페이스에 대해서 검증해준다
@FunctionalInterface
public interface MyFunction {

    String valueOf(Integer integer);

    static String hello() {
        return "hello";
    }
}
