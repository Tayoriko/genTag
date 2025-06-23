package org.example.source;


import org.example.db.eVarType;

public abstract class OneTag {

    private String name = "empty";
    private int word = 0;
    private int bit = 0;
    private String comment = "unknown tag";
    private eVarType type = eVarType.EMPTY;

    public OneTag(String[] tag) {
        if (tag.length >= 4) {
            this.name = tag[0];
            this.type = eVarType.findByValue(tag[1]);
            if (this.type.equals(eVarType.BOOL)) {
                String[] address = splitAddress(tag[2]);
                this.word = Integer.parseInt(address[0]);
                this.bit = Integer.parseInt(address[1]);
            } else {
                this.word = Integer.parseInt(tag[2]);
            }
            this.comment = tag[3];
        }
    }

    private String[] splitAddress(String address) {
        String[] parts = address.split("\\.");
        String word = parts[0].replaceAll("\\D", ""); // Убирает все символы, кроме цифр
        String bit = parts[1].replaceAll("\\D", "");  // Убирает все символы, кроме цифр
        return new String[]{word, bit};
    }

    public String getName() {
        return name;
    }

    public eVarType getType() {
        return type;
    }

    public int getWord() {
        return word;
    }

    public int getBit() {
        return bit;
    }

    public String getComment() {
        return comment;
    }

    public String getAddr(){
        if (type.equals(eVarType.BOOL)) {;
            return getWord() + "." + String.format("%02d", this.bit);
        } else {
            return String.valueOf(getWord());
        }
    }
}
