package com.bootstrap.tomcat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    
    // 关闭命令
    private static final String SHUTDOWN = "/SHUTDOWN";
    
    // 是否关闭
    private boolean shutdown = false;
    
    public static void main(String[] args) throws Exception {
        HttpServer server = new HttpServer();
        server.await();
    }
    
    @SuppressWarnings("all")
    public void await() throws Exception {
        ServerSocket server = null;
        int port = 8080;
        server = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        while (!shutdown) {
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            socket = server.accept();
            in = socket.getInputStream();
            byte[] b = new byte[in.available()];
            ByteArrayOutputStream baos = new ByteArrayOutputStream(in.available());
            baos.write(b);
            System.out.println(b.toString());
            out = socket.getOutputStream();
            out.write("你好".getBytes());
            out.flush();
            socket.close();
        }
    }
}
