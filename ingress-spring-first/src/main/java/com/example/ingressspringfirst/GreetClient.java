package com.example.ingressspringfirst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

public class GreetClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        clientSocket.setSoTimeout(30000);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("192.168.1.83", 8818)) {
            socket.setSoTimeout(30000);
            String receivedMessage;

            OutputStream outputStream = socket.getOutputStream();

            String xmlMessage = "<ipayMsg client='GOLDP05092023' term='0001' seqNum='" + "sessionId" + "' time='2010" +
                    "-04" +
                    "-01 11:35:21 +0200'>" +
                    "<gen2wayMsg ver='1.0' resource='gas'>" +
                    "<custInfoReq>" +
                    "<ref>" + "sessionId" + "</ref>" +
                    "<talexusToken serialNum='" + "cardNumber" + "' resourceType='gas' tokenType='smartCard'>" +
                    "<tokenFile>" + "token1" + "</tokenFile>" +
                    "<tokenFile>" + "token2" + "</tokenFile>" +
                    "<tokenFile>" + "token3" + "</tokenFile>" +
                    "<tokenFile>" + "token4" + "</tokenFile>" +
                    "</talexusToken>" +
                    "</custInfoReq>" +
                    "</gen2wayMsg>" +
                    "</ipayMsg>";


            // Convert the XML message to bytes in UTF-8 encoding
            byte[] messageBytes = xmlMessage.getBytes("UTF-8");

            // Calculate the message length and convert it to network byte order (big-endian)
            int messageLength = messageBytes.length;

            byte[] lengthBytes = ByteBuffer.allocate(2).putShort((short) messageLength).array();

            // Send the message length indicator (2 bytes) followed by the message
            outputStream.write(lengthBytes);
            outputStream.write(messageBytes);
            outputStream.flush();
//                String res = makeRequest(outputStream);

            // Create an input stream for receiving messages
            InputStream inputStream = socket.getInputStream();

            // Read the message length indicator (2 bytes) to determine the message length
            byte[] lengthIndicatorBytes = new byte[2];
            inputStream.read(lengthIndicatorBytes);
            int receivedLength = ByteBuffer.wrap(lengthIndicatorBytes).getShort();

            // Read the message payload based on the received length
            byte[] receivedMessageBytes = new byte[receivedLength];
            inputStream.read(receivedMessageBytes);

            // Convert the received message to a string (assuming it's UTF-8 encoded)
            receivedMessage = new String(receivedMessageBytes, "UTF-8");

            System.out.println("Received Message: " + receivedMessage);
        } catch (SocketTimeoutException e) {
            System.err.println("Socket timeout occurred: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        GreetClient gr = new GreetClient();
        gr.startConnection("127.0.0.1", 8080);
        String az = gr.sendMessage("hello server");
        System.out.println(az);

    }
}

