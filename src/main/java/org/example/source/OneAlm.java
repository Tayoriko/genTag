package org.example.source;

import org.example.db.*;

public abstract class OneAlm {

    private Enum<?> devType;  // Будет содержать eDevAI, eDevM или eDevV
    private Enum<?> devAlarm;  // Текст аварии из соответствующего enum
    private final OneTag almTag;
    private String almName = "";

    public OneAlm(OneTag sourceTag) {
        almTag = sourceTag;
        setDevType(almTag.getName());
    }

    // set dev type and alarm message by tag name
    private void setDevType (String devName) {
        String[] parts = devName.split("_");

        // set dev name
        String devicePart = parts[0];
        String alarmPart = parts[parts.length - 1];
        for (int i = 1; i < parts.length - 1; i++) {
            this.almName = this.almName + parts[i] + " ";
        }

        // set devType and devAlarm
        try {
            this.devType = Enum.valueOf(eDevAI.class, devicePart);
            this.devAlarm = Enum.valueOf(eAlarmAI.class, alarmPart);
        } catch (IllegalArgumentException e1) {
            try {
                this.devType = Enum.valueOf(eDevM.class, devicePart);
                this.devAlarm = Enum.valueOf(eAlarmM.class, alarmPart);
            } catch (IllegalArgumentException e2) {
                try {
                    this.devType = Enum.valueOf(eDevV.class, devicePart);
                    this.devAlarm = Enum.valueOf(eAlarmV.class, alarmPart);
                } catch (IllegalArgumentException e3) {
                    // Тип не распознан
                    this.devType = eDevM.EMPTY;
                    this.devAlarm = eAlarmM.EMPTY;
                }
            }
        }
    }

    public Enum<?> getDevType() {
        return devType;
    }

    public Enum<?> getDevAlarm() {
        return devAlarm;
    }

    public OneTag getAlmTag() {
        return almTag;
    }

    public String getAlmName() {
        return almName;
    }
}
