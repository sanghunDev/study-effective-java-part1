package com.example.studyeffectivejavapart1.chpater01.item05.code;

import java.util.List;

public interface Dictionary {

    boolean contains(String word);

    List<String> closeWordsTo(String typo);
}
