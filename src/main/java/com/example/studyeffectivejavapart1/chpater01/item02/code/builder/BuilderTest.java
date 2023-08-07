package com.example.studyeffectivejavapart1.chpater01.item02.code.builder;

public class BuilderTest {

    public static void main(String[] args) {
        new NutritionFacts.Builder(10, 10)
                .calories(10)
                .build();
    }
}
