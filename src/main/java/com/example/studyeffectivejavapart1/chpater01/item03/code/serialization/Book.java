package com.example.studyeffectivejavapart1.chpater01.item03.code.serialization;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {

    // 기본적으로 직접 선언하지 않으면 jvm 에서 만들어주며 클래스 구조를 변경하면 serialVersionUID 가 변경된다
    // 역직렬화시 발생할 수 있는 serialVersionUID 오류를 방지하기 위해 직접 선언
    private static final long serialVersionUID = 1L;

    private String isbn;

    private String title;

    private LocalDate published;

    private String name;

    private transient int numberOfSold;

    public Book(String isbn, String title, String author, LocalDate published) {
        this.isbn = isbn;
        this.title = title;
        this.published = published;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", published=" + published +
                ", numberOfSold=" + numberOfSold +
                '}';
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public int getNumberOfSold() {
        return numberOfSold;
    }

    public void setNumberOfSold(int numberOfSold) {
        this.numberOfSold = numberOfSold;
    }
}
