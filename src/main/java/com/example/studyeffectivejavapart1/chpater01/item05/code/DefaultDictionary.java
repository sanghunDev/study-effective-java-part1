package com.example.studyeffectivejavapart1.chpater01.item05.code;

import java.util.List;

public class DefaultDictionary implements Dictionary {

    @Override
    public boolean contains(String word) {
        return false;
    }

    @Override
    public List<String> closeWordsTo(String typo) {
        return null;
    }
}
