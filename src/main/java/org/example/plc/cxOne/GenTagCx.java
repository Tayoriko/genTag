package org.example.plc.cxOne;

import org.example.Console;
import org.example.ConsoleInputReader;
import org.example.ConsoleOptions;
import org.example.db.DatabaseRegistry;
import org.example.db.GenericDatabase;
import org.example.db.ePLC;
import org.example.plc.MenuPlcTags;

import java.util.*;
import java.util.stream.Collectors;

public class GenTagCx {

    public GenTagCx() {
    }

    public static String[] splitByTab(String input) {
        return input.split("\t");
    }

    public static OneTagCx genTag(String input) {
        String[] value = splitByTab(input);
        return new OneTagCx(value);
    }

    public static List<OneTagCx> genTagList(String input) {
        List<String> lines = input.lines()
                .filter(line -> !line.trim().isEmpty()) // Игнорируем пустые строки
                .collect(Collectors.toList());
        List<OneTagCx> oneTagCxList = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            oneTagCxList.add(GenTagCx.genTag(lines.get(i)));
        }
        return oneTagCxList;
    }

    public void menuPlcCx() {
        ConsoleOptions.printHeader("Генератор тегов и аварий");
        ConsoleOptions.printHelp("Выбрана среда разработки: CX-One");

        MenuPlcTags.checkPlcCx();

        System.out.println("1. Добавить переменные");
        System.out.println("2. Прочитать переменные");
        System.out.println("3. Очистить список");
        System.out.println(". . . ");
        System.out.println("9. Назад");
        System.out.println("0. Выход");

        int choice = ConsoleInputReader.readMenuSelection();

        switch (choice) {
            case 1 -> actionPlcCxAddNew();
            case 2 -> actionPlcCxReadAll();
            case 3 -> actionPlcCxCleanAll();
            case 9 -> MenuPlcTags.createPlcTagsMenu();
            case 0 -> ConsoleOptions.exit();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public void actionPlcCxAddNew() {
        System.out.println("Скопируйте теги из среды разработки CX-One\n");
        String stringInput = ConsoleInputReader.readMultiLineInput();
        // Создаём буферную базу с тегами
        List<OneTagCx> db = genTagList(stringInput);
        // Получаем или создаем базу для OneTagCx
        GenericDatabase<OneTagCx> tagDatabase = DatabaseRegistry.getInstance(OneTagCx.class);
        // Добавляем все записи в базу
        db.forEach(tagDatabase::addRecord);
        System.out.println("Добавлено! \n Общее количество тегов: " + db.size() + "\n");
        MenuPlcTags.addPlcSet(ePLC.CX);
        menuPlcCx();
    }

    public void actionPlcCxReadAll() {
        // Получаем или создаем базу для OneTagCx
        GenericDatabase<OneTagCx> tagDatabase = DatabaseRegistry.getInstance(OneTagCx.class);
        System.out.println("Все теги:");
        tagDatabase.printAllRecords();
        System.out.println("Общее количество тегов: " + tagDatabase.getRecords().size() + "\n");
        menuPlcCx();
    }

    public void actionPlcCxCleanAll() {
        // Получаем или создаем базу для OneTagCx
        GenericDatabase<OneTagCx> tagDatabase = DatabaseRegistry.getInstance(OneTagCx.class);
        tagDatabase.clear();
        System.out.println("\nОчищено!\n");
        MenuPlcTags.removePlcSet(ePLC.CX);
        menuPlcCx();
    }

}
