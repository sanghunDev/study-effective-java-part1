package com.example.studyeffectivejavapart1.chpater02.item13.code.copy_constructor;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class HashSetExample {

    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("keesun");
        hashSet.add("whiteship");
        System.out.println("HashSet: " + hashSet);

        Set<String> treeSet = new TreeSet<>(hashSet);

        System.out.println("TreeSet: " + treeSet);
    }
}
