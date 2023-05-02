package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Purchase {
    private final String title;
    private final LocalDate date;
    private final int sum;

    public Purchase(@JsonProperty("title") String title, @JsonProperty("date") LocalDate date, @JsonProperty("sum") int sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
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
    public void saveToTxt(File txt) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(txt))) {
            bufferedWriter.write(getTitle() + " ");
            bufferedWriter.write(getDate() + " ");
            bufferedWriter.write(getSum() + " ");
            bufferedWriter.newLine();
        }
    }
}
