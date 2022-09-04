package com.lionel.zc.javaDemo1.tools;


import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyLog {
    private static Client client = new Client("192.168.1.2", "3005");

    public static void i(Object message) {
        client.emit(String.valueOf(message));
    }

    private static class Client {

        boolean connected = false;
        private Socket socket;
        private final String EMIT_TAG = "message";

        final String domain;
        final String port;

        Client(String domain, String port) {
            this.domain = domain;
            this.port = port;
        }

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        private ArrayList<String> msgList = new ArrayList<>();

        void emit(String message) {
            //连接尝试了, 就不继续了. .
            threadPool.execute(() -> {
                msgList.add(message);
                if (connected) {
                    sendMsgList();
                    return;
                }
                if (connect()) {
                    sendMsgList();
                }
            });
        }

        void sendMsgList() {
            while (!msgList.isEmpty()) {
                socket.emit(EMIT_TAG, msgList.remove(0));
            }
        }

        boolean connect() {
            if (connected) return true;
            CountDownLatch cdl = new CountDownLatch(1);
            try {
                String url = String.format("http://%s:%s", domain, port);
                IO.Options options = new IO.Options();
                options.transports = new String[]{"websocket"};
                options.reconnectionAttempts = 10;
                options.reconnectionDelay = 1000;
                options.timeout = 1000;
                socket = IO.socket(url, options);
                socket.on(Socket.EVENT_CONNECT, objects -> {
                    connected = true;
                    cdl.countDown();
                });
                socket.on(Socket.EVENT_DISCONNECT, objects -> {
                    connected = false;
                    cdl.countDown();
                });
                socket.on(Socket.EVENT_CONNECT_TIMEOUT, objects -> {
                    msgList.clear(); //超时了, 就把消息列队清楚掉...
                    cdl.countDown();
                });

                socket.on(Socket.EVENT_CONNECT_ERROR, objects -> {
                    msgList.clear();
                    cdl.countDown();
                });

                socket.connect();
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return false;
            }

            try {
                return cdl.await(10_000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }
}