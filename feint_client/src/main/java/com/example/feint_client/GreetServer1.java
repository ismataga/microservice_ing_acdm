package com.example.feint_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer1 {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private OutputStream outputStream;
    private InputStream inputStream;

    public  void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        outputStream = clientSocket.getOutputStream();
        inputStream = clientSocket.getInputStream();
        int greeting = inputStream.read();
        if ("hello server".getBytes().equals(greeting)) {
            inputStream.read("hello client".getBytes());
        } else {
            outputStream.write("unrecognised greeting".getBytes());
        }
    }

    public void stop() throws IOException {
        inputStream.close();
        outputStream.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        GreetServer1 server = new GreetServer1();
        while (true)
        server.start(8080);

    }
}


