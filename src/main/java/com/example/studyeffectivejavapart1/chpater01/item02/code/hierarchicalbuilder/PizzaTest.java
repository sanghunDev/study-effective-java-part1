package com.example.studyeffectivejavapart1.chpater01.item02.code.hierarchicalbuilder;

import static com.example.studyeffectivejavapart1.chpater01.item02.code.hierarchicalbuilder.NyPizza.Size.SMALL;
import static com.example.studyeffectivejavapart1.chpater01.item02.code.hierarchicalbuilder.Pizza.Topping.*;

// 계층적 빌더
public class PizzaTest {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE)
                .addTopping(ONION).build();

        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM).sauceInside().build();

        System.out.println(pizza);
        System.out.println(calzone);
    }
}
