package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Purchase {
    private final String title;
    @JsonFormat(pattern = "yyyy_MM_dd")
    private final LocalDate date;
    private final int sum;

    public Purchase(@JsonProperty("title") String title, @JsonProperty("date") LocalDate date, @JsonProperty("sum") int sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }
    public void saveToTxt(File txt) throws FileNotFoundException, IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(txt, true))) {
            bufferedWriter.write(getTitle() + " ");
            bufferedWriter.write(getDate() + " ");
            bufferedWriter.write(getSum() + " ");
            bufferedWriter.newLine();
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Не удалось записать файл");
            e.printStackTrace();
        }
    }

    public static List<Purchase> loadFromTxt(File txt) throws IOException {
        List<Purchase> purchaseList = new ArrayList<>();
        try (Scanner scanner = new Scanner(txt)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String title = parts[0];
                LocalDate date = LocalDate.parse(parts[1]);
                int sum = Integer.parseInt(parts[2]);
                purchaseList.add(new Purchase(title, date, sum));
            }
        }
        return purchaseList;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "{ " + "товар: " + getTitle() + " " + "дата покупки: " + getDate() + " " + "сумма: " + getSum() + " }";
    }
}
