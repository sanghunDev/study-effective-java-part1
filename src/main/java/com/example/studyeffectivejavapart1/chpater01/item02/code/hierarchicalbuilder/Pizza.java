package com.example.studyeffectivejavapart1.chpater01.item02.code.hierarchicalbuilder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

// 계층적으로 설계된 클래스와 잘 어울리는 빌더 패턴
// 여기서 사용한 '시뮬레이트한 셀프 타입(simulated self-type)' 관용구는 빌더뿐 아니라 임의의 유동적인 계층구조를 허용한다.

public abstract class Pizza {
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        // 하위 클래스는 이 메서드를 재정의(overriding) 해서 "this"를 반환하도록 해야 한다.
        // 해당 메서드를 사용해서 캐스팅을 하지 않아도 되게 처리
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone(); // 아이템 50 참조
    }
}
