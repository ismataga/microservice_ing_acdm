package com.example.ingressspringfirst;// A Java program for a Client

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Client {
    // initialize socket and input output streams
    private Socket socket;
    private OutputStream out;
    private InputStream in;

    // constructor to put ip address and port
    public Client(String address, int port) {
        // establish a connection
        try {
            socket = new Socket(address, port);
            socket.setSoTimeout(30_000);
            if (socket.isConnected()) {
                System.out.println("Connected");
            }

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
            //takes input from socket
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException i) {
            System.out.println(i);
        }

        try {
            String az = "Salam dunya";

            byte[] arr = az.getBytes("UTF-8");
            out.write(wrap(arr));

            String req = Arrays.toString(wrap(arr));
            //printing request to console
            System.out.println("Sent to server : " + req);

            // Receiving reply from server
            byte[] res = unWrap(in);

            String str = new String(res, StandardCharsets.UTF_8);
            System.out.println("byte array response: " + res);

            System.out.println("Recieved from server : " + str);
        } catch (IOException i) {
            System.out.println(i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // close the connection
        try {
            // input.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public byte[] wrap(byte[] msg) throws Exception {
        int len = msg.length;
        if (len > 65535) {
            throw new IllegalArgumentException("Exceeds 65535 bytes.");
        }
        byte firstByte = (byte) (len >>> 8);
        byte secondByte = (byte) len;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(firstByte);
        baos.write(secondByte);
        baos.write(msg);

        return baos.toByteArray();
    }

    public byte[] unWrap(InputStream inputStream) throws Exception {
        int firstByte = inputStream.read();
        if (firstByte == -1) {
            throw new IOException("End of Stream while trying to read vli byte 1");
        }
        int firstByteValue = firstByte << 8;
        int secondByteValue = inputStream.read();
        if (secondByteValue == -1) {
            throw new IOException("End of Stream reading vli byte 2");
        }
        int len = firstByteValue + secondByteValue;
        byte[] message = new byte[len];
        int requestLen;
        int readLen;
        int currentIndex = 0;
        while (true) {
            requestLen = len - currentIndex;
            readLen = inputStream.read(message, currentIndex, requestLen);
            if (readLen == requestLen) {
                break; // Message is complete.
            }
            // Either data was not yet available, or End of Stream.
            currentIndex += readLen;
            int nextByte = inputStream.read();
            if (nextByte == -1) {
                throw new IOException("End of Stream at " + currentIndex);
            }
            message[currentIndex++] = (byte) nextByte;
        }
        return message;
    }


    public static void main(String args[]) {

        new Client("127.0.0.1", 8080);
    }
}