package com.example.studyeffectivejavapart1.chpater01.item08.code.outerclass;

import java.lang.reflect.Field;

// 정적이 아닌 중첩 클래스 사용
public class OuterClass {
    private void hi() {
    }

    class InnerClass {
        public void hello() {
            OuterClass.this.hi();
        }
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();

        System.out.println(innerClass);

        outerClass.printFiled();
    }

    private void printFiled() {
        Field[] declaredFields = InnerClass.class.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("field type:" + field.getType());
            System.out.println("field name:" + field.getName());
        }
    }
}
