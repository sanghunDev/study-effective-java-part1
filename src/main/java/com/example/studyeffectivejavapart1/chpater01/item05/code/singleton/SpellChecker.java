package com.example.studyeffectivejavapart1.chpater01.item05.code.singleton;

import com.example.studyeffectivejavapart1.chpater01.item05.code.DefaultDictionary;
import com.example.studyeffectivejavapart1.chpater01.item05.code.Dictionary;

import java.util.List;

public class SpellChecker {

    // 의존성 주입이 아닌 직접 주입으로 만든다면 DefaultDictionary 를 변경하기가 어려워진다
    // 만약 Dictionary 가 인터페이스이고 의존성 주입을 사용한다면 구현 객체가 변경 되더라도 규약이 존재하기 때문에 SpellChecker 의 모든 코드는 재사용 가능해진다
    private final Dictionary dictionary = new DefaultDictionary();

    private SpellChecker() {
    }

    public static final SpellChecker INSTANCE = new SpellChecker();

    public boolean isValid(String word) {
        // TODO 여기 SpellChecker 코드
        return dictionary.contains(word);
    }

    public List<String> suggestions(String typo) {
        // TODO 여기 SpellChecker 코드
        return dictionary.closeWordsTo(typo);
    }
}
