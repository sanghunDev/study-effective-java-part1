package com.example.studyeffectivejavapart1.chpater01.item06.code;

import java.util.regex.Pattern;

// 값비싼 객체를 재사용해 성능을 개선한다
public class RomanNumerals {
    // 성능을 훨씬 더 끌어올릴 수 있다
    static boolean isRomanNumeralSlow(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    // 값비싼 객체를 재사용해 성능을 개선
    // (정규식으로 만드는 패턴이 비용이 비싼편이기 때문에 필드로 선언해서 사용하는게 효율적이다)
    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeralFast(String s) {
        return ROMAN.matcher(s).matches();
    }

    public static void main(String[] args) {
        boolean result = false;
        long start = System.nanoTime();
        for (int j = 0; j < 100; j++) {
            //TODO 성능 차이를 확인하려면 xxxSlow 메서드를 xxxFast 메서드로 바꿔 실행해보자.
            result = isRomanNumeralSlow("MCMLXXVI");
        }
        long end = System.nanoTime();
        System.out.println(end - start);
        System.out.println(result);
    }
}

