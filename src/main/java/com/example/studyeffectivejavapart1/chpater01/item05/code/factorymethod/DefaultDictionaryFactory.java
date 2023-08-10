package com.example.studyeffectivejavapart1.chpater01.item05.code.factorymethod;

import com.example.studyeffectivejavapart1.chpater01.item05.code.DefaultDictionary;
import com.example.studyeffectivejavapart1.chpater01.item05.code.Dictionary;

public class DefaultDictionaryFactory implements DictionaryFactory {
    @Override
    public Dictionary getDictionary() {
        return new DefaultDictionary();
    }
}
