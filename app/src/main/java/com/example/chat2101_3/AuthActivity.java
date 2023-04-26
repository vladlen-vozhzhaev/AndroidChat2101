package com.example.chat2101_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AuthActivity extends AppCompatActivity {
    EditText loginEditText;
    EditText passEditText;
    AppCompatButton authBtn;
    DataOutputStream out;
    FrameLayout mainFrame;
    static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mainFrame = findViewById(R.id.mainFrame);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainFrame, new AuthFragment()).commit();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connector.connect(); // Подключаемся к серверу
            }
        });
        thread.start();

        /*loginEditText = findViewById(R.id.loginEditText);
        passEditText = findViewById(R.id.passEditText);
        authBtn = findViewById(R.id.authBtn);
        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String login = loginEditText.getText().toString(); // Получаем логин из поля ввода логина
                    String pass = passEditText.getText().toString(); // Получаем пароль из поля ввода пароля
                    JSONObject jsonObject = new JSONObject(); // Создаём JSON объект
                    jsonObject.put("login", login); // Добавляем в JSON login
                    jsonObject.put("pass", pass); // Добавляем в JSON pass
                    *//* В итоге получаем вот такой JSON файл (запись)
                    * {
                    *   "login": "ivan@mail.ru",
                    *   "pass": "123"
                    * }
                    * *//*
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                out.writeUTF(jsonObject.toString());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    thread.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("192.168.1.6", 9178);
                    out = new DataOutputStream(socket.getOutputStream());
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    while (true){
                        String response = in.readUTF();
                        AuthActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AuthActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();*/
    }
}