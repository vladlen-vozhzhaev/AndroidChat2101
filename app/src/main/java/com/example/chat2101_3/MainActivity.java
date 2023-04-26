package com.example.chat2101_3;

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
    EditText editTextMessage; // Поле для ввода сообщений
    Button sendBtn; // Кнопка для отправки сообщений
    TextView messageTextView; // TextView для вывода сообщений
    DataOutputStream out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*editTextMessage = findViewById(R.id.editTextMessage);
        sendBtn = findViewById(R.id.sendBtn);
        messageTextView = findViewById(R.id.messageTextView);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Сохраняем текст из поля ввода
                String message = editTextMessage.getText().toString();
                // Помещаем сообщение в поле вывода
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
                    Socket socket = new Socket("172.22.152.105", 9178);
                    out = new DataOutputStream(socket.getOutputStream());
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    while (true){
                        String response = in.readUTF(); // Ждём сообщения от сервера
                        // Запускаем поток который работает с GUI
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
        thread.start();*/
    }
}