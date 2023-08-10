package com.example.studyeffectivejavapart1.chpater01.item05.code.dependencyinjection;

import com.example.studyeffectivejavapart1.chpater01.item05.code.DefaultDictionary;

public class DictionaryFactory {
    public static DefaultDictionary get() {
        return new DefaultDictionary();
    }
}
