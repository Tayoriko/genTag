package org.example.db;

public enum eCxMemory {
    EMPTY   ("EMPTY"),
    W       ("W"),
    CIO     (""),
    H       ("H"),
    D       ("D");


    private final String alloc;

    eCxMemory(String typeName) {
        this.alloc = typeName;
    }

    public String getAlloc() {
        return alloc;
    }

    public String getValue() {
        return alloc;
    }

    //find type by string value
    public static eCxMemory findByValue (String value) {
        eCxMemory type = eCxMemory.EMPTY;
        for (eCxMemory item : eCxMemory.values())
        {
            if (item.getValue().equalsIgnoreCase(value)) {
                type = item;
                break;
            }
        }
        return type;
    }
}