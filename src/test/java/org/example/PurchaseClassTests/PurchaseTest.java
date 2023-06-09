package org.example.PurchaseClassTests;

import org.example.Purchase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class PurchaseTest {
    File fileSave = new File("./src\\test\\java\\org\\example\\PurchaseClassTests\\saveTxtText.txt");
    File fileLoad = new File("./src\\test\\java\\org\\example\\PurchaseClassTests\\loadFileTest.txt");

    @Test
        //TODO Тест проверяет метод  на то, запишет ли он информацию в файл формата-txt
    void saveToTxt() throws IOException {
        String expected = "";
        String readFromFile = "";
        //TODO запись
        Purchase product1 = new Purchase("чипсы", LocalDate.parse("2023-05-05"), 200);
        product1.saveToTxt(fileSave);
        //TODO проверка
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileSave))) {
            expected = "чипсы 2023-05-05 200 ";
            readFromFile = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Эта ошибка могла выпасть в двух случаях: 1) Файла нет. 2) Файл нельзя прочитать");
            e.printStackTrace();
        }
        Assertions.assertEquals(expected, readFromFile);
    }

    @Test
        //TODO тест проверяет метод считывания с loadFileTest файла в Java-объект
    void loadFromTxt() throws IOException {
        List<Purchase> purchaseList = new ArrayList<>();
        String expected = "{ " + "товар: " + "чипсы" + " " + "дата покупки: " + "2023-05-05" + " " + "сумма: " + "200" + " }";
        String readFromToFile = "";
        try {
            purchaseList = Purchase.loadFromTxt(fileLoad);
        } catch (IOException e) {
            System.out.println("Не удалось считать файл");
            e.printStackTrace();
        }
        for (Purchase purchase : purchaseList) {
            readFromToFile += purchase.toString();
        }
        Assertions.assertEquals(expected, readFromToFile);
    }
}