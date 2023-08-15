package com.example.studyeffectivejavapart1.chpater03.item16.code.dimension;

import java.awt.*;

public class DimensionExample {

    public static void main(String[] args) {
        Button button = new Button("hello button");
        button.setBounds(0, 0, 20, 10);

        // 내부 필드를 public 하게 노출했음
        Dimension size = button.getSize();
        // 필드 접근으로 사용가능
        System.out.println(size.height);
        System.out.println(size.width);
    }

}
