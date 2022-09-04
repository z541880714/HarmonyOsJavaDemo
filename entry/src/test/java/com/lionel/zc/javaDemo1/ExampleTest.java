package com.lionel.zc.javaDemo1;

import com.lionel.zc.javaDemo1.tools.MyLog;
import org.junit.Test;

public class ExampleTest {
    @Test
    public void onStart() {
        System.out.println(1111);
        new Thread(() -> {
            System.out.println(2222);
        }).start();
        MyLog.i("ttttttttttttttttttt");
        MyLog.i("ttttttttttttttttttt");
        MyLog.i("ttttttttttttttttttt");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("eeeeee  end!!!!");
    }
}
