package com.prekdu;

import java.util.*;

/*
 * Create a basic Java Program that takes 10 strings as input and adds them to an ArrayList and
 * HashSet. Also, create a HashMap by populating the words as key and frequency as the value in the
 * map. Iterate the list, set and map and print the content of the collection.
 */

public final class CollectionsExample {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Create an ArrayList, HashSet, and HashMap
    ArrayList<String> arrayList = new ArrayList<>();
    HashSet<String> hashSet = new HashSet<>();
    HashMap<String, Integer> hashMap = new HashMap<>();

    // Take 10 strings as input
    System.out.println("Enter 10 strings:");
    for (int i = 0; i < 10; i++) {
      String input = scanner.nextLine();

      // Add to ArrayList and HashSet
      arrayList.add(input);
      hashSet.add(input);

      // Update frequency in HashMap
      hashMap.put(input, hashMap.getOrDefault(input, 0) + 1);
    }

    // Print ArrayList
    System.out.println("\nArrayList contents:");
    for (String str : arrayList) {
      System.out.println(str);
    }

    // Print HashSet
    System.out.println("\nHashSet contents:");
    for (String str : hashSet) {
      System.out.println(str);
    }

    // Print HashMap
    System.out.println("\nHashMap contents (word -> frequency):");
    for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }

    scanner.close();
  }
}
