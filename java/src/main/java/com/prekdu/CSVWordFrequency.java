package com.prekdu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Create a basic Java Program that reads a CSV file and prints the top 3 repeated words in the file.
 * The CSV file is in the reosurces folder and the file name is input.csv.
 */

public class CSVWordFrequency {
  public static void main(String[] args) {
    String fileName = "src//main//resources/input.csv"; // Path to the CSV file
    Map<String, Integer> wordCount = new HashMap<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;

      // Skip the header line
      if ((line = br.readLine()) != null) {
        // Skip header
      }

      // Read each line in the file
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");

        for (String value : values) {
          if (!value.trim().isEmpty()) { // Ignore empty values
            wordCount.put(value, wordCount.getOrDefault(value, 0) + 1);
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading the file: " + e.getMessage());
    }

    // Sort by frequency and get the top 3 words
    List<Map.Entry<String, Integer>> topWords =
        wordCount.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .limit(3)
            .collect(Collectors.toList());

    // Print the top 3 repeated words
    System.out.println("Top 3 repeated words:");
    for (Map.Entry<String, Integer> entry : topWords) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }
  }
}
