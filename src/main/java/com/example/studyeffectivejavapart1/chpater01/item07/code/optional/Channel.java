package com.example.studyeffectivejavapart1.chpater01.item07.code.optional;

import java.util.Optional;

public class Channel {

    private int numOfSubscribers;

    public Optional<MemberShip> defaultMemberShip() {
        if (this.numOfSubscribers < 2000) {
//            throw new IllegalStateException(); // 예외를 던지는 방법도 있다
            return Optional.empty();    // java8 부터 사용
        } else {
            return Optional.of(new MemberShip());
        }
    }
}
