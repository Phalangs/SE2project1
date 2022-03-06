package com.example.se2testproject;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class TCPClient {

    public static String connect(String str) throws IOException {
        String sentence;
        String modifiedSentence;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8))));

        Socket clientSocket = new Socket ("se2-isys.aau.at",53212);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence=inFromUser.readLine();

        outToServer.writeBytes(sentence+'\n');

        modifiedSentence = inFromServer.readLine();

        //System.out.print("From server: "+modifiedSentence);

        clientSocket.close();

        return modifiedSentence;
    }

}
