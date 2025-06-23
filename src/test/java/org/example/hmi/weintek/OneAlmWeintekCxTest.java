package org.example.hmi.weintek;

import org.example.plc.cxOne.GenTagCx;
import org.example.plc.cxOne.OneTagCx;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OneAlmWeintekCxTest {

    OneTagCx oneTagCX;
    OneTagWeintek oneTagWeintekCx;
    OneAlmWeintek oneAlmWeintekCx;
    String source;
    String plcName;

    @BeforeEach
    void setUp() {
        plcName = "DD10";
        source = "LE_E_22_1_almHH\tBOOL\tW200.08\tLE Е 22.1 Аварийно высокий уровень\t0\t\n";
        oneTagCX = GenTagCx.genTag(source);
        oneTagWeintekCx = new OneTagWeintek(oneTagCX, plcName);
        oneAlmWeintekCx = new OneAlmWeintek(oneTagWeintekCx);
        //LE_E_22_1_almH	BOOL	W202.08	LE Е 22.1 Высокий уровень	0
    }

    @Test
    void getComment() {
        String expextedResult = "Уровень E 22/1 Аварийно высокие показания датчика";
        String result = oneAlmWeintekCx.getComment();
        Assertions.assertEquals(expextedResult, result);
    }

    @Test
    void testToString() {
        String expextedResult = "0: Category 0\tLow\tBit\tDD10\tW_Bit\tFalse\tFalse\t20008\tnull\t \tFalse\tFalse\t\t\tFalse\tFalse\t\tnull\tbt: 1\t0\tУровень E 22/1 Аварийно высокие показания датчика\n";
        String result = oneAlmWeintekCx.toString();
        Assertions.assertEquals(expextedResult, result);
    }

}