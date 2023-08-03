package com.example.studyeffectivejavapart1.chpater01.item01;

public class Order {

    private boolean prime;

    private boolean urgent;

    private Product product;

    // 정적 팩토리를 사용해서 메소드에 맞게 이름을 부여해서 메소드의 표현을 더 잘 할 수 있다
    // 시그니처 중복 시 유용하다
    public static Order primeOrder(Product product) {
        Order order = new Order();
        order.prime = true;
        order.product = product;

        return order;
    }

    public static Order urgentOrder(Product product) {
        Order order = new Order();
        order.urgent = true;
        order.product = product;
        return order;
    }

    public static void main(String[] args) {

        Order order = new Order();

    }

}
