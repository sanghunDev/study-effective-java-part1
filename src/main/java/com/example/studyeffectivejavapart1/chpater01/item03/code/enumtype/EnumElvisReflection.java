package com.example.studyeffectivejavapart1.chpater01.item03.code.enumtype;

import java.lang.reflect.Constructor;

public class EnumElvisReflection {

    public static void main(String[] args) {
        try {
            Constructor<Elvis> declaredConstructor = Elvis.class.getDeclaredConstructor();
            System.out.println(declaredConstructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
