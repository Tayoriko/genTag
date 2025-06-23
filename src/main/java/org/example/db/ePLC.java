package org.example.db;

public enum ePLC {
    EMPTY       ("empty"),
    CX          ("CX-One"),
    TIA         ("TIA Portal");


    //add name to enumerations
    private final String alloc;

    //initialisation enumerations
    ePLC(String alloc){
        this.alloc = alloc;
    }

    //get string description on vars
    public String getValue() {
        return this.alloc;
    }

    //find type by string value
    public static ePLC findByValue (String value) {
        ePLC type = ePLC.EMPTY;
        for (ePLC item : ePLC.values())
        {
            if (item.getValue().equals(value)) {
                type = item;
                break;
            }
        }
        return type;
    }
}
