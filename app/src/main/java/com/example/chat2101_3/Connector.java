package com.example.chat2101_3;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connector {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static JSONObject jsonObject = new JSONObject();
    private static String response;

    public static void connect(){
        try {
            socket = new Socket("192.168.1.6", 9178);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void sendMessage(String message){
        try {
            jsonObject.put("public", true);
            jsonObject.put("msg", message);
            out.writeUTF(jsonObject.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static String getMessage(){
        try {
            response = in.readUTF();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean auth(String login, String pass){
        try {
            jsonObject.put("action", "login"); //
            jsonObject.put("login", login);
            jsonObject.put("pass", pass);
            out.writeUTF(jsonObject.toString());
            response = in.readUTF();
            return response.equals("success");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
