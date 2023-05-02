package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Main {
    public static File saveTxt = new File("saveTxt");

    public static void main(String[] args) throws IOException {
        // TODO Считывание категорий
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
            System.out.println("Сервер запущен");
            List<Purchase> purchaseList;
            Counting counting = new Counting();
            while (true) {
                try (Socket socket = serverSocket.accept(); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                    System.out.println("Подключился клиент с таким портом:" + socket.getPort());
                    //TODO Подключается клиент с запросом
                    out.println("Жду твой запрос в json-формате");
                    String body = in.readLine();
                    //TODO 1) Узнал категорию товара
                    ObjectMapper objectMapper = new ObjectMapper();
                    purchaseList = objectMapper.readValue(body, new TypeReference<>() {
                    });
                    for (Purchase purchase : purchaseList) {
                        //TODO Заношу инф о покупку в txt-файл
                        purchase.saveToTxt(saveTxt);
                        //TODO 2) Занес ее в спец класс расчета
                        counting.setCategories(categories.get(purchase.getTitle()), purchase.getSum());
                    }
                    //TODO Возвращаю json с максимально затратной категорией
                    out.println(counting.maxCategories());
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}