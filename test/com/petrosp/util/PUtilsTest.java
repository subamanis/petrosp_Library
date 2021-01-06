package com.petrosp.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PUtilsTest
{
    @Test
    void toFixed()
    {
        assertEquals(4136.0153d , PUtils.toFixed((269875d / 65.25d), 4, false));

        assertEquals(478.193649993d , PUtils.toFixed((3795423d / 7937d), 9, false));
        assertEquals(478.193649994d , PUtils.toFixed((3795423d / 7937d), 9, true));

        assertEquals(0.245212d, PUtils.toFixed(0.2452125245,6, false));
        assertEquals(0.245213d, PUtils.toFixed(0.2452125245,6, true));
    }

    @Test
    void bytesToKBs()
    {
        assertEquals(462848d , PUtils.KBsToBytes(452));
        assertEquals(4634044416L , PUtils.KBsToBytes(4525434));
        assertEquals(746980094976L , PUtils.KBsToBytes(729472749));
        assertEquals(0 , PUtils.KBsToBytes(0));
    }

    @Test
    void bytesToMBs()
    {
        assertEquals(8.87, PUtils.toFixed(PUtils.bytesToMBs(9306000),2, false));
        assertEquals(1.74, PUtils.toFixed(PUtils.bytesToMBs(1833601), 2, false));
        assertEquals(0, PUtils.bytesToMBs(0));
    }

    @Test
    void MD5Hash()
    {
        String tobeHashed = "abcdef123";
        String hash = PUtils.MD5Hash(tobeHashed);

        assertEquals("AA7C9C12FC740955EF4DFAD670250FF4", hash.toUpperCase());
    }
}