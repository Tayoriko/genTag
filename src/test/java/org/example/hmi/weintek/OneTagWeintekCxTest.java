package org.example.hmi.weintek;

import org.example.plc.cxOne.GenTagCx;
import org.example.plc.cxOne.OneTagCx;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OneTagWeintekCxTest {

    String source;
    String plcName;
    String[] tagSource = new String[5];

    @BeforeEach
    void setUp() {
        tagSource[0] = "LS_Ed_2_LL";
        tagSource[1] = "BOOL";
        tagSource[2] = "W100.01";
        tagSource[3] = "LS Ed 2 LL";
        tagSource[4] = "0";
        plcName = "DD10";
        source = "LS_Ed_2_LL	BOOL	W100.01	LS Ed 2 LL	0";
    }

    @Test
    void testToString() {
        OneTagCx oneTagCX = GenTagCx.genTag(source);
        OneTagWeintek oneTagWeintekCx = new OneTagWeintek(oneTagCX, plcName);
        String result = oneTagWeintekCx.toString();
        String expectedResult = "LS_Ed_2_LL\tDD10\tW_Bit\t10001\tLS Ed 2 LL\tUndesignated\n";
        Assertions.assertEquals(expectedResult,result);
    }
}