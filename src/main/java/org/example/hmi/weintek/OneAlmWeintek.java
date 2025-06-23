package org.example.hmi.weintek;

import org.example.db.*;
import org.example.source.OneAlm;

public class OneAlmWeintek extends OneAlm {

    private OneTagWeintek tagCx;
    private String comment = "";
    private String address = "";

    public OneAlmWeintek(OneTagWeintek sourceTag) {
        super(sourceTag);
        this.tagCx = sourceTag;
        this.address = getTagCx().getAddr().replace(".","");
        this.comment = setComment();
    }

    private String setComment () {
        String devName = replaceSpacesBetweenNumbers(getAlmName());
        String name = "";
        String alarm = "";
        if (getDevType() instanceof eDevAI) {
            eDevAI eDev = (eDevAI) getDevType();
            eAlarmAI eAlarm = (eAlarmAI) getDevAlarm();
            name = eDev.getRu();
            alarm = eAlarm.getRu();
        }
        if (getDevType() instanceof eDevM) {
            eDevM eDev = (eDevM) getDevType();
            eAlarmM eAlarm = (eAlarmM) getDevAlarm();
            name = eDev.getRu();
            alarm = eAlarm.getRu();
        }
        if (getDevType() instanceof eDevV) {
            eDevV eDev = (eDevV) getDevType();
            eAlarmV eAlarm = (eAlarmV) getDevAlarm();
            name = eDev.getRu();
            alarm = eAlarm.getRu();
        }
        return name + " " + devName + alarm;
    }

    private String replaceSpacesBetweenNumbers(String input) {
        return input.replaceAll("(\\d) (\\d)", "$1/$2");
    }

    public String getComment() {
        return comment;
    }

    public OneTagWeintek getTagCx() {
        return tagCx;
    }

    @Override
    public String toString() {
        return "0: Category 0\tLow\tBit\t" +
                tagCx.getPlcName() + "\t" +
                tagCx.getMemory() + "\tFalse\tFalse\t" +
                address + "\tnull\t \tFalse\tFalse\t\t\tFalse\tFalse\t\tnull\tbt: 1\t0\t" +
                getComment();
    }
}
