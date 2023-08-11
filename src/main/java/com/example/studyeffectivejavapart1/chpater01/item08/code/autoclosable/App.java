package com.example.studyeffectivejavapart1.chpater01.item08.code.autoclosable;

public class App {

    public static void main(String[] args) {
        // try-with-resource 로 가장 권장되는 방법
        // 자원을 사용 후 자동으로 정리된다
        try(AutoClosableIsGood good = new AutoClosableIsGood("")) {
            // TODO 자원 반납 처리가 됨.
        }
    }
}
