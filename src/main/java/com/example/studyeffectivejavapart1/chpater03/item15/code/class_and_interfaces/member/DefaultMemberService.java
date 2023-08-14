package com.example.studyeffectivejavapart1.chpater03.item15.code.class_and_interfaces.member;

import java.util.Arrays;

// 구현체이기 때문에 외부 패키지에 제공할 필요가 없다고 판단 public 을 사용하지 않는다
class DefaultMemberService implements MemberService {

    private String name;

    // inner 클래스지만 외부 인스턴스를 참조하지 않음
    // 감싸는 쪽에서만 inner 클래스를 사용하는 경우에는 private static 이 어울린다
    private static class PrivateStaticClass {
    }

    // 외부 인스턴스 참조 한다 (보이지 않지만 DefaultMemberService 필드를 가지고 있다)
    // 외부 참조로 인한 GC 누수 가능성 존재
    private class PrivateClass {
    }

    public static void main(String[] args) {
        Arrays.stream(PrivateClass.class.getDeclaredFields()).forEach(System.out::println);
    }

}
