package com.example.studyeffectivejavapart1.chpater01.item08.code.autoclosable;

import java.io.*;

public class AutoClosableIsGood implements Closeable {

    private BufferedReader reader;

    public AutoClosableIsGood(String path) {
        try {
            this.reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(path);
        }
    }

    // 구체적인 예외를 던지면 클라이언트 코드에서 무조건 처리하게 된다
    // 클라이언트 코드에서 처리하는게 아닌 해당 메서드 내부에서 예외 처리를 할 수도 있다
    @Override
    public void close() throws IOException {
        reader.close();
    }
}
