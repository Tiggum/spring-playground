package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WordCounter {

    public static Map<String, Integer> count(String sentence){

        String[] words = sentence.replaceAll("[^a-zA-z ]", "").toLowerCase().split(" ");

        HashMap<String, Integer> wordCount = new HashMap<>();
        for (String word : words){
            if (wordCount.containsKey(word)){
                int count = wordCount.get(word);
                wordCount.put(word, count + 1);
            } else {
                wordCount.put(word, 1);
            }
        }

        return wordCount;
    }
}
