package org.example.hmi.weintek;

import org.example.ConsoleInputReader;
import org.example.ConsoleOptions;
import org.example.db.DatabaseRegistry;
import org.example.db.GenericDatabase;
import org.example.db.eHMI;
import org.example.db.ePLC;
import org.example.hmi.MenuHmiTags;
import org.example.plc.MenuPlcTags;
import org.example.plc.cxOne.OneTagCx;

import java.util.ArrayList;
import java.util.List;

public class GenWeintek {

    private static String plcName = "";

    public GenWeintek() {
    }

    public static List<OneTagWeintek> genTagList(List<OneTagCx> tagCxList, String plcName) {
        List<OneTagWeintek> oneTagWeintekCxList = new ArrayList<>();
        for (OneTagCx tagCx : tagCxList) {
            oneTagWeintekCxList.add(new OneTagWeintek(tagCx, plcName));
        }
        return oneTagWeintekCxList;
    }

    public static List<OneAlmWeintek> genAlarmList(List<OneTagWeintek> tagCxList) {
        List<OneAlmWeintek> oneAlmWeintekCxList = new ArrayList<>();
        for (OneTagWeintek tagCx : tagCxList) {
            oneAlmWeintekCxList.add(new OneAlmWeintek(tagCx));
        }
        return oneAlmWeintekCxList;
    }

    public static void menuHmiWeintekTag() {
        ConsoleOptions.printHeader("Генератор тегов и аварий");
        ConsoleOptions.printHelp("Выбрана производитель HMI: Weintek");

        MenuPlcTags.checkPlcCx();
        MenuHmiTags.checkHmiCx();

        if (!plcName.isEmpty()) {
            System.out.println("Имя PLC: " + plcName);
        } else {
            System.out.println("Имя PLC не задано!");
        }
        System.out.println("1. Задать имя PLC");
        if (MenuPlcTags.isPlc(ePLC.CX) && !plcName.isEmpty()) {
            System.out.println("2. Импортировать теги из CX-One");
        }
        System.out.println("3. Посмотреть все теги");
        System.out.println("4. Удалить все теги");
        System.out.println("10. Сформировать аварии");
        System.out.println("11. Посмотреть аварии");
        System.out.println("12. Удалить все аварии");
        System.out.println(". . . ");
        System.out.println("9. Назад");
        System.out.println("0. Выход");

        int choice = ConsoleInputReader.readMenuSelection();

        switch (choice) {
            case 1 -> setPlcName();
            case 2 -> actionHmiImportCx();
            case 3 -> actionHmiWeintekReadTagAll();
            case 4 -> actionHmiWeintekCleanTagAll();
            case 10 -> actionHmiImportAlarms();
            case 11 -> actionHmiWeintekReadAlmAll();
            case 12 -> actionHmiWeintekCleanAlmAll();
            case 9 -> MenuPlcTags.createPlcTagsMenu();
            case 0 -> ConsoleOptions.exit();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    private static void setPlcName() {
        System.out.println("Введите имя PLC");
        plcName = ConsoleInputReader.readMultiLineInput().trim();
        menuHmiWeintekTag();
    }

    private static void actionHmiImportCx() {
        // Получаем или создаем базы данных
        GenericDatabase<OneTagCx> dbSource = DatabaseRegistry.getInstance(OneTagCx.class);
        GenericDatabase<OneTagWeintek> dbWeintek = DatabaseRegistry.getInstance(OneTagWeintek.class);
        List<OneTagWeintek> db = genTagList(dbSource.getRecords(), plcName);
        db.forEach(dbWeintek::addRecord);
        System.out.println("Добавлено! \n Общее количество тегов: " + db.size() + "\n");
        MenuHmiTags.addHmiSet(eHMI.Wientek);
        menuHmiWeintekTag();
    }

    private static void actionHmiImportAlarms() {
        // Получаем или создаем базы данных
        GenericDatabase<OneTagWeintek> dbSource = DatabaseRegistry.getInstance(OneTagWeintek.class);
        GenericDatabase<OneAlmWeintek> dbWeintekAlm = DatabaseRegistry.getInstance(OneAlmWeintek.class);
        List<OneAlmWeintek> db = genAlarmList(dbSource.getRecords());
        db.forEach(dbWeintekAlm::addRecord);
        System.out.println("Добавлено! \n Общее количество аварий: " + db.size() + "\n");
        menuHmiWeintekTag();
    }

    private static void actionHmiWeintekReadTagAll() {
        // Получаем или создаем базу для OneTagCx
        GenericDatabase<OneTagWeintek> tagDatabase = DatabaseRegistry.getInstance(OneTagWeintek.class);
        System.out.println("Все теги:");
        tagDatabase.printAllRecords();
        System.out.println("Общее количество тегов: " + tagDatabase.getRecords().size() + "\n");
        menuHmiWeintekTag();
    }

    private static void actionHmiWeintekReadAlmAll() {
        // Получаем или создаем базу для OneTagCx
        GenericDatabase<OneAlmWeintek> tagDatabase = DatabaseRegistry.getInstance(OneAlmWeintek.class);
        System.out.println("Все аварии:");
        tagDatabase.printAllRecords();
        System.out.println("Общее количество аварий: " + tagDatabase.getRecords().size() + "\n");
        menuHmiWeintekTag();
    }

    private static void actionHmiWeintekCleanTagAll() {
        GenericDatabase<OneTagWeintek> tagDatabase = DatabaseRegistry.getInstance(OneTagWeintek.class);
        tagDatabase.clear();
        System.out.println("\nОчищено!\n");
        MenuHmiTags.removeHmiSet(eHMI.Wientek);
        menuHmiWeintekTag();
    }

    private static void actionHmiWeintekCleanAlmAll() {
        GenericDatabase<OneAlmWeintek> tagDatabase = DatabaseRegistry.getInstance(OneAlmWeintek.class);
        tagDatabase.clear();
        System.out.println("\nОчищено!\n");
        MenuHmiTags.removeHmiSet(eHMI.Wientek);
        menuHmiWeintekTag();
    }

    public String getPlcName() {
        return plcName;
    }

    public void setPlcName(String plcName) {
        this.plcName = plcName;
    }
}
