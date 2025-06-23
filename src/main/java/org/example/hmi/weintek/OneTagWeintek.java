package org.example.hmi.weintek;

import org.example.db.eVarType;
import org.example.plc.cxOne.OneTagCx;
import org.example.source.OneTag;

public class OneTagWeintek extends OneTag {

    private String memory = "W";
    private String plcName = "plc";
    private String varType = "unknown";

    public OneTagWeintek(OneTagCx sourceTag, String plcName) {
        super(new String[]{
                sourceTag.getName(),
                sourceTag.getType().toString(),
                sourceTag.getAddr(),
                sourceTag.getComment()
        });
        this.memory = sourceTag.getMemory().toString();
        if (sourceTag.getType().equals(eVarType.BOOL)) {
            this.memory += "_Bit";
        }
        this.plcName = plcName;
        this.varType = setVarType(sourceTag.getType());
    }

    public String getMemory() {
        return memory;
    }

    public String getPlcName() {
        return plcName;
    }

    private String setVarType(eVarType varType) {
        switch (varType) {
            case BOOL -> {
                return "Undesignated\n";
            }
            case WORD -> {
                return "16-bit Unsigned\n";
            }
            case REAL -> {
                return "32-bit Float\n";
            }
            case INT -> {
                return "16-bit Signed\n";
            }
            case UINT -> {
                return "16-bit Unsigned\n";
            }
            default -> {
                return "Undesignated";
            }
        }
    }

    public String getVarType() {
        return varType;
    }

    @Override
    public String toString() {
        return getName() + "\t" +
                getPlcName() + "\t" +
                getMemory() + "\t" +
                getAddr().replace(".","") + "\t" +
                getComment() + "\t" +
                getVarType();
    }
}
