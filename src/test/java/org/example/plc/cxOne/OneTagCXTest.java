package org.example.plc.cxOne;

import org.example.db.eCxMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OneTagCXTest {

    static OneTagCx oneTagCX;
    String[] tag = new String[4];

    @BeforeEach
    void setUp() {
        tag[0] = "LS_E_27_HL";
        tag[1] = "BOOL";
        tag[2] = "W100.13";
        tag[3] = "Датчик нижнего уровня";
        oneTagCX = new OneTagCx(tag);
    }

    @Test
    void getMemory() {
        eCxMemory expectedResult = eCxMemory.W;
        Assertions.assertEquals(expectedResult,oneTagCX.getMemory());
    }

    @Test
    void testToString() {
        String expectedResult = "LS_E_27_HL - BOOL - W100.13 - Датчик нижнего уровня";
        oneTagCX = new OneTagCx(tag);
        Assertions.assertEquals(expectedResult,oneTagCX.toString());
    }
}