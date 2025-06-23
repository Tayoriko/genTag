package org.example.plc.cxOne;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class GenTagCxTest {

    String source;
    String[] tagSource = new String[5];

    @BeforeEach
    void setUp() {
        tagSource[0] = "LS_Ed_2_2_LL";
        tagSource[1] = "BOOL";
        tagSource[2] = "W100.01";
        tagSource[3] = "LS Ed 2.2 LL";
        tagSource[4] = "0";
        source = "LS_Ed_2_2_LL	BOOL	W100.01	LS Ed 2.2 LL	0";
    }

    @Test
    void splitByTab() {
        String[] tagData = GenTagCx.splitByTab(source);
        Assertions.assertEquals(Arrays.stream(tagSource).toList(),Arrays.stream(tagData).toList());
    }

    @Test
    void genTag() {
        OneTagCx oneTagCX = GenTagCx.genTag(source);
        String expectedResult = "LS_Ed_2_2_LL - BOOL - W100.01 - LS Ed 2.2 LL";
        String result = oneTagCX.toString();
        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    void genTagList() {
        source = "LS_I_1_2_AHL\tBOOL\tW100.02\tLS I 1.2 AHL\t0\t\n" +
                "LS_I_2_HL\tBOOL\tW100.03\tLS I 1.1 HL\t0\t\n" +
                "LS_I_2_LL\tBOOL\tW100.04\tLS I 1.1 LL\t0\t\n" +
                "LS_I_1_2_ALL\tBOOL\tW100.05\tLS I 1.2 ALL\t0\t\n";
        List<OneTagCx> oneTagCxSet = GenTagCx.genTagList(source);
        System.out.println(oneTagCxSet.size());
        String expectedResult = "LS_I_1_2_ALL - BOOL - W100.05 - LS I 1.2 ALL";
        OneTagCx tagCx = oneTagCxSet.get(3);
        String result = tagCx.toString();
        int length = oneTagCxSet.size();
        Assertions.assertEquals(expectedResult,result);
        Assertions.assertEquals(4, length);
    }
}