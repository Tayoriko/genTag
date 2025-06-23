package org.example;

import org.example.hmi.MenuHmiTags;
import org.example.plc.MenuPlcTags;

import java.util.Scanner;

public class Console {

    private static boolean isRunning = true;
    private static MenuPlcTags menuPlcTags = new MenuPlcTags();
    private static MenuHmiTags menuHmiTags = new MenuHmiTags();

    public static void main(String[] args) {
        while (isRunning) {
            showMainMenu();
        }
    }

    public static void showMainMenu() {
        ConsoleOptions.printHeader("Генератор тегов и аварий");
        ConsoleOptions.printHelp("Используйте цифры для навигации. Для выхода введите '0'");

        System.out.println("1. PLC");
        System.out.println("2. HMI");
        System.out.println("0. Выход");

        int choice = ConsoleInputReader.readMenuSelection();

        switch (choice) {
            case 1 -> menuPlcTags.createPlcTagsMenu();
            case 2 -> menuHmiTags.createHmiTagsMenu();
            case 0 -> ConsoleOptions.exit();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public int getChoice(int maxOption) {
        System.out.print("Выберите действие: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0 || choice == 9 || (choice >= 1 && choice <= maxOption)) {
                    return choice;
                }
                System.out.print("Неверный ввод. Попробуйте снова: ");
            } catch (NumberFormatException e) {
                System.out.print("Введите число: ");
            }
        }
    }

    public static void setRunning(boolean running) {
        isRunning = running;
    }
}
