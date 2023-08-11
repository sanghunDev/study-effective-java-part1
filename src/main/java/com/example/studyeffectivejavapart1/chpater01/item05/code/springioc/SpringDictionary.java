package com.example.studyeffectivejavapart1.chpater01.item05.code.springioc;

import com.example.studyeffectivejavapart1.chpater01.item05.code.Dictionary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringDictionary implements Dictionary {

    @Override
    public boolean contains(String word) {
        System.out.println("contains " + word);
        return false;
    }

    @Override
    public List<String> closeWordsTo(String typo) {
        return null;
    }
}
