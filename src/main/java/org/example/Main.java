package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, String> categories = new HashMap<>();
        try (Scanner scanner = new Scanner(new File("categories.tsv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");

                if (parts.length == 2) {
                    categories.put(parts[0], parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            while (true) {
                try (Socket socket = serverSocket.accept(); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                    //TODO Подключается клиент с запросом
                    out.println("Жду твой запрос в json-формате");
                        //TODO 1) Узнал категорию товара
                    String body = in.readLine();
                    ObjectMapper objectMapper = new ObjectMapper();
                    Purchase purchase = objectMapper.readValue(body, new TypeReference<>() {
                    });
                    if(categories.containsKey(purchase.getTitle())) {
                        Counting counting = new Counting();
                        counting.setCategories(categories.get(purchase.getTitle()), purchase.getSum());
                    }
                        //TODO 2) Занес ее в спец файл расчета
                    //TODO Заношу инф о покупку в csv-файл
                    //TODO Запрос заношу в спец.класс
                    //TODO Обрабатываю запрос с помощью counting
                    //TODO Возвращаю json с максимально затратной категорией
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}