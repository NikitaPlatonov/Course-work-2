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
    //TODO тут идет проверка на то, что категории, которых нет в categories.tsv определяются в категорию - другое.
    void setCategoriesOther() {
        counting.setCategories("Такого нет в categories.tsv", 5000);

        String expected = "{"+
                "\"maxCategory\""+":"+ "{"+""+
                "\"category\""+":" + "\"" + "Такого нет в categories.tsv" + "\""+""+
                "\"sum\"" + ":" + "5000" + "" +
                "}"+""+
                "}";

        Assertions.assertEquals(expected, counting.maxCategories());
    }
    @Test
    //TODO здесь идет проверка на правильный расчет самой затратной категории.
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