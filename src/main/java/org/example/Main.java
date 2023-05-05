package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static File saveTxt = new File("saveTxt");

    public static File categoriesFile = new File("categories.tsv");

    public static Map<String, String> categories = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // TODO Считывание категорий
        readTsvFile(categoriesFile);
        //TODO запуск сервера
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            System.out.println("Сервер запущен");
            while (true) {
                List<Purchase> purchaseList;
                Counting counting = new Counting();
                try (Socket socket = serverSocket.accept(); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                    System.out.println("Подключился клиент с таким портом:" + socket.getPort());
                    //TODO проверка наличия файла с которого нужно делать восстановление
                    if (saveTxt.exists() && saveTxt.canRead() && !(saveTxt.length() == 0)) {
                        purchaseList = Purchase.loadFromTxt(saveTxt);
                        for (Purchase purchase : purchaseList) {
                            counting.setCategories(categories.getOrDefault(purchase.getTitle(), "другое"), purchase.getSum());
                        }
                    }
                    //TODO Подключается клиент с запросом
                    out.println("Жду твой запрос в json-формате");
                    String body = in.readLine();
                    //TODO конвертировал JSON в Java-объект
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.findAndRegisterModules();
                    purchaseList = objectMapper.readValue(body, new TypeReference<>() {
                    });
                    for (Purchase purchase : purchaseList) {
                        //TODO Заношу инф о покупке в txt-файл
                        purchase.saveToTxt(saveTxt);
                        //TODO Занес ее в спец класс расчета
                        counting.setCategories(categories.getOrDefault(purchase.getTitle(), "другое"), purchase.getSum());
                    }
                    //TODO Возвращаю json с максимально затратной категорией
                    out.println(counting.maxCategories());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
    public static void readTsvFile(File file) {
        try (Scanner scanner = new Scanner(new File("categories.tsv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\P{L}+");
                categories.put(parts[0], parts[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}