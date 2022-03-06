package com.example.se2testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            String sentence;
                            String modifiedSentence;

                            EditText edit1 = findViewById(R.id.serverInput);
                            TextView text1 = findViewById(R.id.serverOutput);

                            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(edit1.getText().toString().getBytes(StandardCharsets.UTF_8))));

                            Socket clientSocket = new Socket ("se2-isys.aau.at",53212);

                            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

                            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                            sentence=inFromUser.readLine();

                            outToServer.writeBytes(sentence+'\n');

                            modifiedSentence = inFromServer.readLine();

                            //System.out.print("From server: "+modifiedSentence);
                            text1.setText(modifiedSentence);

                            clientSocket.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();

            }
        });
    }


    public void connectToServer(View view) throws IOException {
        String sentence;
        String modifiedSentence;

        EditText edit1 = findViewById(R.id.serverInput);
        TextView text1 = findViewById(R.id.serverOutput);

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(edit1.getText().toString().getBytes(StandardCharsets.UTF_8))));

        Socket clientSocket = new Socket ("se2-isys.aau.at",53212);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence=inFromUser.readLine();

        outToServer.writeBytes(sentence+'\n');

        modifiedSentence = inFromServer.readLine();

        //System.out.print("From server: "+modifiedSentence);
        text1.setText(modifiedSentence);

        clientSocket.close();
    }
}