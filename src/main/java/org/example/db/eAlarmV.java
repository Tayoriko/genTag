package org.example.db;

public enum eAlarmV {
    EMPTY       ("empty",       "no information"),
    almQF       ("almQF",       "Сработала тепловая защита"),
    almOpen     ("almOpen",     "Ошибка при открытии"),
    almClose    ("almClose",    "Ошибка при закрытии"),
    almTS       ("almTS",       "Перегрв двигателя"),
    almSF       ("almSF",       "Отключение по датчику безопасности"),
    almExt      ("almExt",      "Внешнаяя авария"),
    almLink     ("almLink",     "Нет связи с устройством");


    //add name to enumerations
    private final String alloc;
    private final String ru;

    //initialisation enumerations
    eAlarmV(String alloc, String ru){
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
    public static eAlarmV findByValue (String value) {
        eAlarmV type = eAlarmV.EMPTY;
        for (eAlarmV item : eAlarmV.values())
        {
            if (item.getValue().equals(value)) {
                type = item;
                break;
            }
        }
        return type;
    }
}
