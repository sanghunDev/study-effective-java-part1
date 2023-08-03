package com.example.studyeffectivejavapart1.chpater01.item01;

/**
 * 생성자로는 인스턴스의 제공을 컨트롤 할 수 없지만
 * 정적 팩토리 메서드를 사용해서 하나의 인스턴스만 만들게 처리할 수 있다
 *
 * 이 클래스의 인스턴스는 #getInstance() 를 통해 사용합니다
 * @see #getInstance()
 */
public class Settings {

    private boolean useAutoSteering;

    private boolean useABS;

    private Difficulty difficulty;

    // 아래와 같이 기본 생성자를 private 로 막으면 해당 클래스의 상속이 불가능해진다
    private Settings() {}

    private static final Settings SETTINGS = new Settings();

    // Constructor Summary 에 표시되지 않음
    public static Settings getInstance() {
        return SETTINGS;
    }

}
