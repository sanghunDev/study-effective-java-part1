package com.example.studyeffectivejavapart1.chpater01.item01;

import java.util.Arrays;
import java.util.EnumMap;

public class Order {

    private boolean prime;

    private boolean urgent;

    private Product product;

    // 해당 필드는 해당 클래스의 정해진 값만 가질 수 있음 (Type-Safety)
    private OrderStatus orderStatus;

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
        // enum 타입이 가지고 있는 모든 값을 순회하며 출력
        Arrays.stream(OrderStatus.values()).forEach(System.out::println);

        // enum 타입의 Map 생성
        // Hash 는 해싱 작업을 통해 성능 향상을 이루지만 Enum 은 단일 객체를 보장하기 때문에 해싱이 필요가 없어 검색 속도가 빠르다
        // Enum 에 명시된 순서를 기억한다
        EnumMap<OrderStatus, Object> enumMap = new EnumMap<>(OrderStatus.class);

    }

}
