package com.example.studyeffectivejavapart1.chpater01.item05.code.factorymethod;

import com.example.studyeffectivejavapart1.chpater01.item05.code.Dictionary;
import com.example.studyeffectivejavapart1.chpater01.item05.code.MockDictionary;

public class MockDictionaryFactory implements DictionaryFactory {
    @Override
    public Dictionary getDictionary() {
        return new MockDictionary();
    }
}
