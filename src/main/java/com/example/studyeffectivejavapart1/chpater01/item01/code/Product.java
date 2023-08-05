package com.example.studyeffectivejavapart1.chpater01.item01.code;

import java.util.EnumSet;

public class Product {

    public static void main(String[] args) {
        // 같은 인스턴스가 반환된다
        // 즉 가져오는 쪽에서는 인스턴스에 대한 컨트롤이 안된다
        Settings settings1 = Settings.getInstance();
        Settings settings2 = Settings.getInstance();

        System.out.println(settings1);
        System.out.println(settings2);

        // 아래도 정적 팩토리 메소드로 강제한 예제다
        Boolean.valueOf(false);
        EnumSet.allOf(Difficulty.class);
    }
}
