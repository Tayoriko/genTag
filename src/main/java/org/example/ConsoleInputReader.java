package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readMultiLineInput() {
        StringBuilder input = new StringBuilder();

        try {
            System.out.println("Enter your data (press Enter twice to finish):");
            String line;
            boolean previousEmpty = false;

            while ((line = reader.readLine()) != null) {
                // Check for two consecutive empty lines
                if (line.trim().isEmpty()) {
                    if (previousEmpty) {
                        break;
                    }
                    previousEmpty = true;
                } else {
                    previousEmpty = false;
                    input.append(line).append("\n");
                }
            }

            // Remove the last newline if present
            if (input.length() > 0 && input.charAt(input.length() - 1) == '\n') {
                input.setLength(input.length() - 1);
            }

            return input.toString();

        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            return "";
        }
    }

    public static Integer readMenuSelection() {
        try {
            System.out.println("Enter menu item number:");
            String input = reader.readLine().trim();

            // Validate and convert input to Integer
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                return readMenuSelection(); // Recursive call for retry
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                return readMenuSelection(); // Recursive call for retry
            }

        } catch (IOException e) {
            System.err.println("Error reading menu selection: " + e.getMessage());
            return readMenuSelection(); // Recursive call for retry
        }
    }
}