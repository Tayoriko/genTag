package org.example;

public class ConsoleOptions {

    public static void printHeader(String title) {
        System.out.println("=== " + title + " ===");
    }

    public static void printHelp(String helpText) {
        System.out.println("[" + helpText + "]");
    }

    public static void exit() {
        System.out.println("Завершение работы программы...");
        Console.setRunning(false);
    }
}
