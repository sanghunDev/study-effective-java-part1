package com.example.studyeffectivejavapart1.chpater01.item09.code.puzzler;

import java.io.*;

// 자바 퍼즐러 예외 처리 코드 실수에 관한 코드
public class Copy {
    private static final int BUFFER_SIZE = 8 * 1024;

    // 자원이 둘 이상이면 try-finally 방식은 너무 지저분하다!
    static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        } finally {
            try {
                // 여기서 exception 발생시 두번째 try 의 자원종료가 되지 않음
                out.close();
            } catch (IOException e) {
                // TODO 이렇게 하면 되는거 아닌가?
            }

            try {
                in.close();
            } catch (IOException e) {
                // TODO 안전한가?
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String src = args[0];
        String dst = args[1];
        copy(src, dst);
    }
}
