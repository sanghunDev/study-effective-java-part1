package com.example.studyeffectivejavapart1.chpater01.item03.code.methodreference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Person {

    LocalDate birthday;

    public Person() {

    }

    public Person(LocalDate birthday) {
        this.birthday = birthday;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person(LocalDate.of(1982, 7, 15)));
        people.add(new Person(LocalDate.of(2011, 3, 2)));
        people.add(new Person(LocalDate.of(2013, 1, 28)));

        // java 8 이전 - 익명 내부 클래스 사용
        people.sort(new Comparator<Person>() {
            @Override
            public int compare(Person a, Person b) {
                return a.birthday.compareTo(b.birthday);
            }
        });

        // 람다 사용
        people.sort((p1, p2) -> p1.birthday.compareTo(p2.birthday));

        // 메소드 참조 사용 - 스테틱 메서드 참조 (Comparator 와 같은 메소드 시그니처를 가지고 있기 때문에 참조 가능)
        people.sort(Person::compareByAge);
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

}
