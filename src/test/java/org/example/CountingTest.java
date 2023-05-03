package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CountingTest {

    Counting counting;

    @BeforeEach
    void init() {
        counting = new Counting();
    }
    @Test
    void maxCategories() {
        counting.setCategories("Бытовая техника", 10000);
        counting.setCategories("Еда", 1783);
        counting.setCategories("Одежда", 9999);
        String expectedReturn = "{"+
                "\"maxCategory\""+":"+ "{"+""+
                "\"category\""+":" + "\"" + "Бытовая техника" + "\""+""+
                "\"sum\"" + ":" + "10000" + "" +
                "}"+""+
                "}";
        Assertions.assertEquals(expectedReturn, counting.maxCategories());
    }
}