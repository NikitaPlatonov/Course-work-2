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
            out.println("[{" + "\"title\": " + "\"танки\", "+ "\"date\": " + "\"2022_02_08\", " + "\"sum\": " + "10000" + "}, {" + "\"title\": " + "\"молоко\", "+ "\"date\": " + "\"2022_02_09\", " + "\"sum\": " + "150" + "}]");
            out.flush();
            String serverMessage = in.readLine();
            String answer = in.readLine();
            System.out.println(serverMessage);
            System.out.println(answer);
        }
    }
}
