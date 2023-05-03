package org.example;

import java.util.HashMap;
import java.util.Map;

public class Counting {
    private final Map<String, Integer> categories = new HashMap<>();

    {
        categories.put("другое", 0);
    }
    public void setCategories(String title, int sum) {
        if(categories.containsKey(title)) {
            categories.put(title, categories.get(title) + sum);
        } else {
            categories.put(title, sum);
        }

    }
    public String maxCategories() {
        int maxSum = Integer.MIN_VALUE;
        String maxCategory = "";
        for (Map.Entry<String, Integer> entry : categories.entrySet()) {
            String category = entry.getKey();
            int sum = entry.getValue();
            if (sum > maxSum) {
                maxSum = sum;
                maxCategory = category;
            }
        }
        return "{"+
                "\"maxCategory\""+":"+ "{"+""+
                "\"category\""+":" + "\"" + maxCategory+ "\""+""+
                "\"sum\"" + ":" + maxSum +""+
                "}"+""+
                "}";
    }
}
