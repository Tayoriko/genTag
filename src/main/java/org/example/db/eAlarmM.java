package org.example.db;

public enum eAlarmM {
    EMPTY       ("empty",       "no information"),
    almQF       ("almQF",       "Сработала тепловая защита"),
    almStart    ("almStart",    "Ошибка при запуске"),
    almStop     ("almStop",     "Ошибка при останове"),
    almTS       ("almTS",       "Перегрв двигателя"),
    almSF       ("almSF",       "Отключение по датчику безопасности"),
    almExt      ("almExt",      "Внешнаяя авария"),
    almLink     ("almLink",     "Нет связи с устройством");


    //add name to enumerations
    private final String alloc;
    private final String ru;

    //initialisation enumerations
    eAlarmM(String alloc, String ru){
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
    public static eAlarmM findByValue (String value) {
        eAlarmM type = eAlarmM.EMPTY;
        for (eAlarmM item : eAlarmM.values())
        {
            if (item.getValue().equals(value)) {
                type = item;
                break;
            }
        }
        return type;
    }
}
