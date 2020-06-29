package com.petrosp.assist.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PStringsTest
{

    @Test
    void extractPathAndFileName()
    {
        String s1 = "erffsd/erf/ers.txt";
        String s2 = "fdddddf/dfd";
        String s3 = "fdddddf//dfd//";
        String s4 = "dd//dd//t.txt";
        String s5 = "fdf/.txt";
        String s6 = "dd\\dd\\t.txt";
        String s7 = "dfdsdd";
        String s8 = "t.txt";
        String s9 = "";
        String s10 = ".";
        String s11 = "one/tw.o/three";

        String s12 = "fdf/a.";
        assertEquals("ers.txt", PStrings.extractFileName(s1));
        assertEquals("", PStrings.extractFileName(s2));
        assertEquals("", PStrings.extractFileName(s3));
        assertEquals("t.txt", PStrings.extractFileName(s4));
        assertEquals("", PStrings.extractFileName(s5));
        assertEquals("t.txt", PStrings.extractFileName(s6));
        assertEquals("", PStrings.extractFileName(s7));
        assertEquals("t.txt", PStrings.extractFileName(s8));
        assertEquals("", PStrings.extractFileName(s10));
        assertEquals("", PStrings.extractFileName(s11));
        assertEquals("", PStrings.extractFileName(s12));


        assertEquals("erffsd/erf/", PStrings.extractPath(s1));
        assertEquals("fdddddf/dfd", PStrings.extractPath(s2));
        assertEquals("fdddddf//dfd//", PStrings.extractPath(s3));
        assertEquals("dd//dd//", PStrings.extractPath(s4));
        assertEquals("fdf/", PStrings.extractPath(s5));
        assertEquals("dd\\dd\\", PStrings.extractPath(s6));
        assertEquals("dfdsdd", PStrings.extractPath(s7));
        assertEquals("", PStrings.extractPath(s8));
        assertEquals("", PStrings.extractPath(s9));
        assertEquals("", PStrings.extractPath(s10));
        assertEquals(s11, PStrings.extractPath(s11));
    }

    @Test
    void validNumberTransformation()
    {
        String s1 = "212.12293";
        String s2 = "123456789";
        String s3 = "12345678910111213141516";
        String s4 = String.valueOf(Long.MAX_VALUE);
        String s5 = String.valueOf(Double.MAX_VALUE);
        String s6 = "-3244";
        String s7 = "0";
        String s8 = "fd";
        String s9 = "0x021";
        String s10 = String.valueOf(Long.MAX_VALUE);

        assertTrue(PStrings.containsOnlyNumbers(s3));
        assertFalse(PStrings.containsOnlyNumbers(s1));
        assertFalse(PStrings.containsOnlyNumbers(s9));

        assertTrue(PStrings.isValidDouble(s1));
        assertTrue(PStrings.isValidDouble(s2));
        assertFalse(PStrings.isValidDouble(s3));
        assertTrue(PStrings.isValidDouble(s4));
        assertTrue(PStrings.isValidDouble(s5));
        assertTrue(PStrings.isValidDouble(s6));
        assertTrue(PStrings.isValidDouble(s7));
        assertFalse(PStrings.isValidDouble(s8));
        assertFalse(PStrings.isValidDouble(s9));

        assertFalse(PStrings.isValidInt(s1));
        assertTrue(PStrings.isValidInt(s2));
        assertFalse(PStrings.isValidInt(s3));
        assertFalse(PStrings.isValidInt(s4));
        assertFalse(PStrings.isValidInt(s5));
        assertTrue(PStrings.isValidInt(s6));
        assertTrue(PStrings.isValidInt(s7));
        assertFalse(PStrings.isValidInt(s8));
        assertFalse(PStrings.isValidInt(s9));

        assertFalse(PStrings.isValidLong(s1));
        assertTrue(PStrings.isValidLong(s2));
        assertFalse(PStrings.isValidLong(s3));
        assertTrue(PStrings.isValidLong(s4));
        assertFalse(PStrings.isValidLong(s5));
        assertTrue(PStrings.isValidLong(s6));
        assertTrue(PStrings.isValidLong(s7));
        assertFalse(PStrings.isValidLong(s8));
        assertFalse(PStrings.isValidLong(s9));
        assertFalse(PStrings.isValidLong(new StringBuilder(s10).replace(s10.length()-1, s10.length(), "9").toString()));

    }

    @Test
    void countOperations()
    {
        String str1 = "1q 2,!1 ffd!! q";
        String str2 = "";
        String str3 = " ";

        assertEquals(5, PStrings.countLetters(str1));
        assertEquals(0, PStrings.countLetters(str2));
        assertEquals(0, PStrings.countLetters(str3));
        assertEquals(3, PStrings.countDistinctLetters(str1));
        assertEquals(3, PStrings.countNumbers(str1));
        assertEquals(2, PStrings.countDistinctNumbers(str1));
        assertEquals(4, PStrings.countSymbols(str1));
        assertEquals(2, PStrings.countDistinctSymbols(str1));

        assertEquals(3, PStrings.countOccurrences(str1, '!'));
        assertEquals(2, PStrings.countOccurrences(str1, 'f'));
        assertEquals(3, PStrings.countOccurrences(str1, ' '));

        assertEquals(2, PStrings.countSymbols("e!. ddf "));
        assertEquals(3, PStrings.countDistinctSymbols("2 ! dd. D2!!.^f!^"));
        assertEquals(4, PStrings.countOccurrences("2 ! dd. D2!!.^f!^", '!'));

        assertEquals(3, PStrings.countOccurrences("2284u3ff fcn384 fbe84u3fh!3hfb3884u3ffh 398rfn34 f", "84u3f"));
        assertEquals(6, PStrings.countOccurrences("2284u3ff fcn384 fbe84u3fh!3hfb3884u3ffh 398rfn34 f", "8"));
        assertEquals(2, PStrings.countOccurrences("84u3ff f84 f84u3f 3hfb3884u3ffh 398rfn34 f", "ff"));
    }

    @Test
    void isStringLiteral()
    {
        Object ob = 2;
        assertFalse(PStrings.isStringLiteral(ob));

        ob = "str";
        assertTrue(PStrings.isStringLiteral(ob));

        ob = new String[]{"2"};
        assertFalse(PStrings.isStringLiteral(ob));
    }

    @Test
    void containingTests()
    {
        String s1 = "df3";
        String s2 = "4 3 d @";
        String s3 = "";
        String s4 = "  ";
        String s5 = "aasd";
        String s6 = "12";
        String s7 = "1!";
        String s8 = "a1";
        String s9 = "a$";
        String s10 = "1 2";
        String s11 = "a b";

        assertTrue(PStrings.containsNumbers(s1));
        assertTrue(PStrings.containsNumbers(s2));
        assertTrue(PStrings.containsNumbers(s6));
        assertTrue(PStrings.containsNumbers(s8));
        assertFalse(PStrings.containsNumbers(s3));
        assertFalse(PStrings.containsNumbers(s9));
        assertTrue(PStrings.containsOnlyNumbers(s6));
        assertFalse(PStrings.containsOnlyNumbers(s10));
        assertFalse(PStrings.containsOnlyNumbers(s2));

        assertTrue(PStrings.containsLetters(s1));
        assertTrue(PStrings.containsLetters(s2));
        assertTrue(PStrings.containsLetters(s5));
        assertTrue(PStrings.containsLetters(s8));
        assertTrue(PStrings.containsLetters(s9));
        assertFalse(PStrings.containsLetters(s3));
        assertFalse(PStrings.containsLetters(s4));
        assertFalse(PStrings.containsLetters(s7));
        assertTrue(PStrings.containsOnlyLetters(s5));
        assertFalse(PStrings.containsOnlyLetters(s4));
        assertFalse(PStrings.containsOnlyLetters(s4));
        assertFalse(PStrings.containsOnlyLetters(s11));
        assertTrue(PStrings.containsOnlyLettersAndSpaces(s11));

        assertTrue(PStrings.containsOnlyAlphanumeric(s11));
        assertTrue(PStrings.containsOnlyAlphanumeric(s8));
        assertTrue(PStrings.containsOnlyAlphanumeric(s1));
        assertTrue(PStrings.containsOnlyAlphanumeric(s10));
        assertFalse(PStrings.containsOnlyAlphanumeric(s2));

        assertTrue(PStrings.containsSymbols(s2));
        assertTrue(PStrings.containsSymbols(s7));
        assertTrue(PStrings.containsSymbols(s9));
        assertFalse(PStrings.containsSymbols(s4));
        assertFalse(PStrings.containsSymbols(s1));
        assertFalse(PStrings.containsSymbols(s8));
        assertFalse(PStrings.containsSymbols(s3));
    }

    @Test
    void emailPassValidation()
    {
        assertTrue(PStrings.isValidEmail("petpap@gmail.com"));
        assertTrue(PStrings.isValidEmail("p@g.c"));
        assertTrue(PStrings.isValidEmail("petpap@g.com"));

        assertFalse(PStrings.isValidEmail("@gmail.com"));
        assertFalse(PStrings.isValidEmail("petpap@.com"));
        assertFalse(PStrings.isValidEmail("petpap@g."));
        assertFalse(PStrings.isValidEmail("petpap@fdfg"));
        assertFalse(PStrings.isValidEmail("petpapgmail.com"));


        assertTrue(PStrings.isValidPassword("passwords",9,10,false,false));
        assertTrue(PStrings.isValidPassword("passwords",9,null,false,false));
        assertFalse(PStrings.isValidPassword("passwords",8,10,true,false));
        assertFalse(PStrings.isValidPassword("passwords",8,10,false,true));
        assertTrue(PStrings.isValidPassword("password1s",8,null,true,false));
        assertTrue(PStrings.isValidPassword("passwor@ds",8,null,false,true));
        assertTrue(PStrings.isValidPassword("passwor@d1s",8,null,true,true));
        assertFalse(PStrings.isValidPassword("passwor",8,10,false,false));
        assertFalse(PStrings.isValidPassword("passwords12221",8,10,false,false));
    }
}