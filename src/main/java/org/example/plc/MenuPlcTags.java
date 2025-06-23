package org.example.plc;

import org.example.Console;
import org.example.ConsoleInputReader;
import org.example.ConsoleOptions;
import org.example.db.DatabaseRegistry;
import org.example.db.GenericDatabase;
import org.example.db.ePLC;
import org.example.plc.cxOne.GenTagCx;
import org.example.plc.cxOne.OneTagCx;

import java.util.HashSet;
import java.util.Set;

public class MenuPlcTags {

    private static Set<ePLC> plcSet = new HashSet<>();

    private static GenTagCx genTagCx = new GenTagCx();

    public static void createPlcTagsMenu() {

        genTagCx = new GenTagCx();

        ConsoleOptions.printHeader("Генератор тегов и аварий");
        ConsoleOptions.printHelp("Выберите среду разработки");

        checkPlcCx();

        System.out.println("1. CxOne");
        System.out.println("2. TIA Portal");
        System.out.println("3. CoDeSyS v2.3");
        System.out.println("4. CoDeSyS v3.5");
        System.out.println(". . . ");
        System.out.println("9. Назад");
        System.out.println("0. Выход");

        int choice = ConsoleInputReader.readMenuSelection();

        switch (choice) {
            case 1 -> genTagCx.menuPlcCx();
            //case 2 -> showDataMenu();
            case 9 -> Console.showMainMenu();
            case 0 -> ConsoleOptions.exit();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static void checkPlcCx() {
        if (plcSet.contains(ePLC.CX)) {
            GenericDatabase<OneTagCx> tagDatabase = DatabaseRegistry.getInstance(OneTagCx.class);
            System.out.println("PLC: " + ePLC.CX + " - " + tagDatabase.getRecords().size());
        }
    }

    public static boolean isPlc(ePLC plc) {
        return plcSet.contains(plc);
    }

    public Set<ePLC> getPlcSet() {
        return plcSet;
    }

    public static void addPlcSet(ePLC plc) {
        plcSet.add(plc);
    }

    public static void removePlcSet(ePLC plc) {
        if (plcSet.contains(plc)) {
            plcSet.remove(plc);
        }
    }
}
