package org.example.db;

public enum eDevM {
    EMPTY       ("empty",   "no information"),
    M           ("M",       "Мешалка"),
    ME          ("M",       "Мешалка"),
    MR          ("M",       "Мешалка"),
    MSO         ("M",       "Мешалка"),
    MEsm        ("M",       "Мешалка"),
    N           ("N",       "Насос"),
    TK          ("TK",      "Воздуходувка"),
    Compr       ("Compr",   "Компрессор");


    //add name to enumerations
    private final String alloc;
    private final String ru;

    //initialisation enumerations
    eDevM(String alloc, String ru){
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
    public static eDevM findByValue (String value) {
        eDevM type = eDevM.EMPTY;
        for (eDevM item : eDevM.values())
        {
            if (item.getValue().equals(value)) {
                type = item;
                break;
            }
        }
        return type;
    }
}
