package org.example;

import java.util.HashMap;
import java.util.Map;

public class Counting {
    private Map<String, Integer> categories = new HashMap<>();

    public void setCategories(String title, int sum) {
        if(categories.containsKey(title)) {
            categories.put(title, categories.get(title) + sum);
        } else {
            categories.put(title, sum);
        }

    }
    public String maxCategories() {
        //categories.
        return "{\n" +
                "  \"maxCategory\": {\n" +
                "category" ;
    }
}
