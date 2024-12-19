package com.prekdu;

import java.util.Scanner;

/*
 * Create a basic Java Program that takes two strings as input and produces the following output.
 * Print the length of the first string
 * Print the length of the second string
 * Print if the length matches or not
 * Print if the two strings are the same
 */

public class StringComparison {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Take two strings as input
    System.out.print("Enter the first string: ");
    String firstString = scanner.nextLine();

    System.out.print("Enter the second string: ");
    String secondString = scanner.nextLine();

    // Calculate lengths of both strings
    int length1 = firstString.length();
    int length2 = secondString.length();

    // Print the lengths
    System.out.println("Length of the first string: " + length1);
    System.out.println("Length of the second string: " + length2);

    // Check if lengths match
    if (length1 == length2) {
      System.out.println("Lengths match");
    } else {
      System.out.println("Lengths do not match");
    }

    // Check if the two strings are the same
    if (firstString.equals(secondString)) {
      System.out.println("The two strings are the same");
    } else {
      System.out.println("The two strings are different");
    }

    scanner.close();
  }
}
