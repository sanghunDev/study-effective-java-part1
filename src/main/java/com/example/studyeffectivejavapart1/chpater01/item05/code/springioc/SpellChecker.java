package com.example.studyeffectivejavapart1.chpater01.item05.code.springioc;

import com.example.studyeffectivejavapart1.chpater01.item05.code.Dictionary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpellChecker {

    private Dictionary dictionary;

    public SpellChecker(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isValid(String word) {
        // TODO 여기 SpellChecker 코드
        return dictionary.contains(word);
    }

    public List<String> suggestions(String typo) {
        // TODO 여기 SpellChecker 코드
        return dictionary.closeWordsTo(typo);
    }
}
