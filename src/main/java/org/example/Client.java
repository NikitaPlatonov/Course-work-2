package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    final static String host = "127.0.0.1";
    final static int port = 8989;
    public static void main(String[] args) throws Exception {
        try(Socket socket = new Socket(host, port); PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println("[{" + "\"title\": " + "\"Шахматный_курс_Максоуна\", "+ "\"date\": " + "\"2023_04_20\", " + "\"sum\": " + "3000" + "}]");
            out.flush();
            String serverMessage = in.readLine();
            String answer = in.readLine();
            System.out.println(serverMessage);
            System.out.println(answer);
        }
    }
}