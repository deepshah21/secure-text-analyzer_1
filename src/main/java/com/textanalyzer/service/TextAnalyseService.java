package com.textanalyzer.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.textanalyzer.data.TextData;

@Service
public class TextAnalyseService {

	public String analyzed(String text) {
		// TODO Auto-generated method stub
		text = text.toLowerCase();

        // Split text into words
        String[] words = text.split("\\s+");

        // Map to store word counts
        Map<String, Integer> wordCounts = new HashMap<>();

        // Variables to track total words and longest word
        int totalWords = 0;
        String longestWord = "";

        // Count occurrences of each word and find the longest word
        for (String word : words) {
            // Remove punctuation
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            // Increment word count in the map
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            // Update longest word if necessary
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }

        // Total number of words
        totalWords = words.length;
        
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("totalWords", totalWords);
        jsonResponse.put("WordCount",new JSONObject(wordCounts) );
        
        TextData.longestWord = longestWord;
        TextData.totalCount = totalWords;
        TextData.wordCount =wordCounts;
        TextData.TextData = text;
        
        return jsonResponse.toString();
	}

	
}
