package org.example.db;

public enum eVarType {
    EMPTY   ("EMPTY"),
    BIT     ("BIT"),
    BOOL    ("BOOL"),
    REAL    ("REAL"),
    INT     ("INT"),
    UINT    ("UINT"),
    DINT    ("DINT"),
    UDINT   ("UDINT"),
    STRING  ("STRING"),
    FLOAT   ("FLOAT"),
    DOUBLE  ("DOUBLE"),
    CHAR    ("CHAR"),
    BYTE    ("BYTE"),
    WORD    ("WORD"),
    DWORD   ("DWORD"),
    LWORD   ("LWORD"),
    IOLD    ("udtDevDiscrete"),
    IOLA    ("udtDevAnalog"),
    NETD    ("udtNetDataDiscrete"),
    NETA    ("udtNetDataAnalog"),
    PT      ("R_TRIG"),
    NT      ("F_TRIG");

    private final String alloc;

    eVarType(String typeName) {
        this.alloc = typeName;
    }

    public String getAlloc() {
        return alloc;
    }

    public String getValue() {
        return alloc;
    }

    //find type by string value
    public static eVarType findByValue (String value) {
        eVarType type = eVarType.EMPTY;
        for (eVarType item : eVarType.values())
        {
            if (item.getValue().equalsIgnoreCase(value)) {
                type = item;
                break;
            }
        }
        return type;
    }
}