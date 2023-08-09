package com.example.studyeffectivejavapart1.chpater01.item03.code.field;

import java.io.*;

// 역직렬화로 여러 인스턴스 만들기
public class ElvisSerialization {

    public static void main(String[] args) {
        // 저장
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("elvis.obj"))) {
            out.writeObject(Elvis.INSTANCE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 읽어오기 (새로운 인스턴스가 생성 - 싱글톤이 깨진다)
        try (ObjectInput in = new ObjectInputStream(new FileInputStream("elvis.obj"))) {
            // 해당 메서드를 구현을 해준뒤 사용할 인스턴스를 반환시켜야 싱글톤이 보장된다
            Elvis elvis3 = (Elvis) in.readObject();
            System.out.println(elvis3 == Elvis.INSTANCE);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
