package com.example.chat2101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText editTextMessage;
    Button sendBtn;
    TextView messageTextView;
    DataOutputStream out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMessage = findViewById(R.id.editTextMessage);
        sendBtn = findViewById(R.id.sendBtn);
        messageTextView = findViewById(R.id.messageTextView);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString();
                messageTextView.append("Вы: "+message+"\n");
                editTextMessage.setText("");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            out.writeUTF(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("192.168.1.8", 9178);
                    out = new DataOutputStream(socket.getOutputStream());
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    while (true){
                        String response = in.readUTF();
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                messageTextView.append("Вы: "+response+"\n");
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}