package org.example.hmi;

import org.example.Console;
import org.example.ConsoleInputReader;
import org.example.ConsoleOptions;
import org.example.db.DatabaseRegistry;
import org.example.db.GenericDatabase;
import org.example.db.eHMI;
import org.example.hmi.weintek.GenWeintek;
import org.example.hmi.weintek.OneTagWeintek;

import java.util.HashSet;
import java.util.Set;

public class MenuHmiTags {

    private static Set<eHMI> hmiSet = new HashSet<>();

    public void createHmiTagsMenu() {


        ConsoleOptions.printHeader("Генератор тегов и аварий");
        ConsoleOptions.printHelp("Выберите производителя панели");


        System.out.println("1. Weintek");
        System.out.println("2. Omron NB-series");
        System.out.println("3. Omron NS-series");
        System.out.println(". . . ");
        System.out.println("9. Назад");
        System.out.println("0. Выход");

        int choice = ConsoleInputReader.readMenuSelection();

        switch (choice) {
            case 1 -> GenWeintek.menuHmiWeintekTag();
            //case 2 -> showDataMenu();
            case 9 -> Console.showMainMenu();
            case 0 -> ConsoleOptions.exit();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static void checkHmiCx() {
        if (hmiSet.contains(eHMI.Wientek)) {
            GenericDatabase<OneTagWeintek> tagDatabase = DatabaseRegistry.getInstance(OneTagWeintek.class);
            System.out.println("HMI: " + eHMI.Wientek + " - " + tagDatabase.getRecords().size());
        }
    }

    public Set<eHMI> getHmiSet() {
        return hmiSet;
    }

    public static boolean isHmi(eHMI hmi) {
        return hmiSet.contains(hmi);
    }

    public static void addHmiSet(eHMI hmi) {
        hmiSet.add(hmi);
    }

    public static void removeHmiSet(eHMI hmi) {
        if (hmiSet.contains(hmi)) {
            hmiSet.remove(hmi);
        }
    }


}
