package org.example.plc.cxOne;

import org.example.db.eCxMemory;
import org.example.source.OneTag;

public class OneTagCx extends OneTag {

    private eCxMemory memory = eCxMemory.EMPTY;

    public OneTagCx(String[] tag) {
        super(tag);
        this.memory = eCxMemory.findByValue(tag[2].replaceAll("[^A-Z]", ""));
    }

    public eCxMemory getMemory() {
        return memory;
    }

    @Override
    public String toString() {
        return getName() + " - " +
                getType().toString() + " - " +
                getMemory().toString() +
                getAddr().toString() + " - " +
                getComment().toString();
    }
}
