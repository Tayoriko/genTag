package org.example.db;

public enum eDevAI {
    EMPTY       ("empty",   "no information"),
    PE          ("PE",      "Давление"),
    LE          ("LE",      "Уровень"),
    QE          ("QE",      "Проводимость"),
    FE          ("FE",      "Расход"),
    pH          ("pH",      "Кислотность");


    //add name to enumerations
    private final String alloc;
    private final String ru;

    //initialisation enumerations
    eDevAI(String alloc, String ru){
        this.alloc = alloc;
        this.ru = ru;
    }

    //get string description on vars
    public String getValue() {
        return this.alloc;
    }

    public String getRu() {
        return this.ru;
    }

    //find type by string value
    public static eDevAI findByValue (String value) {
        eDevAI type = eDevAI.EMPTY;
        for (eDevAI item : eDevAI.values())
        {
            if (item.getValue().equals(value)) {
                type = item;
                break;
            }
        }
        return type;
    }
}
