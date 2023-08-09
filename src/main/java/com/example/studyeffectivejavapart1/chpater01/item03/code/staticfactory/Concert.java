package com.example.studyeffectivejavapart1.chpater01.item03.code.staticfactory;

import java.util.function.Supplier;

public class Concert {

    public void start(Supplier<Singer> singerSupplier) {
        Singer singer = singerSupplier.get();
        singer.sing();
    }

    public static void main(String[] args) {
        Concert concert = new Concert();
        concert.start(Elvis::getInstance);
    }
}
