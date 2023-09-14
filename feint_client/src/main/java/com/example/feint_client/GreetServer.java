package com.example.feint_client;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class GreetServer {
    private Socket clientSocket = null;
    private ServerSocket serverSocket = null;
    private InputStream in = null;
    private OutputStream out = null;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client accepted");

                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());

                byte[] request = unWrap(in);
                System.out.println("Received from client: " + Arrays.toString(request));

                String str = new String(request, StandardCharsets.UTF_8);
                System.out.println(str);

                String responseMessage = "Hello from the server";
                byte[] response = str.getBytes("UTF-8");
                byte[] wrappedResponse = wrap(response);

                // Send the response back to the client
                out.write(wrappedResponse);
                out.flush();
                System.out.println("Sent to client: " + Arrays.toString(wrappedResponse));
            }
        } catch (IOException e) {
            System.err.println("Error handling client connection: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] wrap(byte[] msg) throws Exception {
        int len = msg.length;
        if (len > 65535) {
            throw new IllegalArgumentException("Exceeds 65535 bytes.");
        }
        byte firstByte = (byte) (len >>> 8);
        byte secondByte = (byte) len;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(len + 2);
        baos.write(firstByte);
        baos.write(secondByte);
        baos.write(msg);
        return baos.toByteArray();
    }

    public byte[] unWrap(InputStream in) throws IOException {
        byte[] lengthBytes = new byte[2];
        in.read(lengthBytes);
        System.out.println(lengthBytes.toString());

        int length = ((lengthBytes[0] & 0xFF) << 8) | (lengthBytes[1] & 0xFF);
        byte[] message = new byte[length];
        in.read(message);

        return message;
    }


    public static void main(String[] args) throws IOException {
        GreetServer server = new GreetServer();
        server.start(8080);
    }
}


