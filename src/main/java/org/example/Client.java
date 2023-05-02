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
            out.println("[{" + "\"title\": " + "\"булка\", "+ "\"date\": " + "\"2022.02.08\", " + "\"sum\": " + "200" + "}, {" + "\"title\": " + "\"молоко\", "+ "\"date\": " + "\"2022.02.09\", " + "\"sum\": " + "150" + "}]");
            out.flush();
        }
    }
}
