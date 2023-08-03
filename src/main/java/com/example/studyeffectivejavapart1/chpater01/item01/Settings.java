package com.example.studyeffectivejavapart1.chpater01.item01;

/**
 * 생성자로는 인스턴스의 제공을 컨트롤 할 수 없지만
 * 정적 팩토리 메서드를 사용해서 하나의 인스턴스만 만들게 처리할 수 있다
 */
public class Settings {

    private boolean useAutoSteering;

    private boolean useABS;

    private Difficulty difficulty;

    private Settings() {}

    private static final Settings SETTINGS = new Settings();

    public static Settings getInstance() {
        return SETTINGS;
    }

}
