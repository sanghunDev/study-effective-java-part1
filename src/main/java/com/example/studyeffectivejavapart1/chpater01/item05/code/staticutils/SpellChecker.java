package com.example.studyeffectivejavapart1.chpater01.item05.code.staticutils;

import com.example.studyeffectivejavapart1.chpater01.item05.code.DefaultDictionary;
import com.example.studyeffectivejavapart1.chpater01.item05.code.Dictionary;

import java.util.List;

public class SpellChecker {

    private static final Dictionary dictionary = new DefaultDictionary();

    private SpellChecker() {
    }

    public static boolean isValid(String word) {
        // TODO 여기 SpellChecker 코드
        return dictionary.contains(word);
    }

    public static List<String> suggestions(String typo) {
        // TODO 여기 SpellChecker 코드
        return dictionary.closeWordsTo(typo);
    }
}
