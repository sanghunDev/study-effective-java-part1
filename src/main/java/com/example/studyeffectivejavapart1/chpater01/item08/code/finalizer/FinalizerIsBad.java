package com.example.studyeffectivejavapart1.chpater01.item08.code.finalizer;

public class FinalizerIsBad {

    // 쓰지말라고 표시 되어 있음
    @Override
    protected void finalize() throws Throwable {
        System.out.print("");
    }
}
