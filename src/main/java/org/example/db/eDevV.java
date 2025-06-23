
package org.example.db;

public enum eDevV {
    EMPTY       ("empty",   "no information"),
    KE          ("KE",      "Клапан"),
    V           ("V",       "Кран"),
    Z           ("Z",       "Задвижка");


    //add name to enumerations
    private final String alloc;
    private final String ru;

    //initialisation enumerations
    eDevV(String alloc, String ru){
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
    public static eDevV findByValue (String value) {
        eDevV type = eDevV.EMPTY;
        for (eDevV item : eDevV.values())
        {
            if (item.getValue().equals(value)) {
                type = item;
                break;
            }
        }
        return type;
    }
}
