package org.example.db;

public enum eAlarmAI {
    EMPTY       ("empty",       "no information"),
    almLL       ("almLL",       "Аварийно низкие показания датчика"),
    almHH       ("almHH",       "Аварийно высокие показания датчика");


    //add name to enumerations
    private final String alloc;
    private final String ru;

    //initialisation enumerations
    eAlarmAI(String alloc, String ru){
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
    public static eAlarmAI findByValue (String value) {
        eAlarmAI type = eAlarmAI.EMPTY;
        for (eAlarmAI item : eAlarmAI.values())
        {
            if (item.getValue().equals(value)) {
                type = item;
                break;
            }
        }
        return type;
    }
}
