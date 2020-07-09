package com.petrosp.assist.util;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PDataTest
{

    @Test
    void toObjectArrayOverloads()
    {
        int[] arr = {1,2,3,4};
        Integer[] Iarr = PData.toObjectArray(arr);
        assertEquals(4, Iarr.length);
        assertEquals(4, Iarr[3]);

        byte[] arr1 = {1,2,3,4};
        Byte[] Barr = PData.toObjectArray(arr1);
        assertEquals(4, Barr.length);
        assertEquals((byte)4, Barr[3]);

        long[] arr2 = {1,2,3,4};
        Long[] Larr = PData.toObjectArray(arr2);
        assertEquals(4, Larr.length);
        assertEquals(4L, Larr[3]);

        double[] arr3 = {1.032,0.23,3.14,4.0001};
        Double[] Darr = PData.toObjectArray(arr3);
        assertEquals(4, Darr.length);
        assertEquals(1.032, Darr[0]);
    }

    @Test
    void toPrimitiveArrayOverloads()
    {
        Integer[] arr = {1,2,3,4};
        int[] Iarr = PData.toPrimitiveArray(arr);
        assertEquals(4, Iarr.length);
        assertEquals(4, Iarr[3]);

        Byte[] arr1 = {1,2,3,4};
        byte[] Barr = PData.toPrimitiveArray(arr1);
        assertEquals(4, Barr.length);
        assertEquals((byte)4, Barr[3]);

        Long[] arr2 = {1L,2L,3L,4L};
        long[] Larr = PData.toPrimitiveArray(arr2);
        assertEquals(4, Larr.length);
        assertEquals(4L, Larr[3]);

        Double[] arr3 = {1.032,0.23,3.14,4.0001};
        double[] Darr = PData.toPrimitiveArray(arr3);
        assertEquals(4, Darr.length);
        assertEquals(1.032, Darr[0]);
    }

    @Test
    void toList()
    {
        Double[] ba = {1.23,2d,3.14,4.0,5.1};

        List<Double> bl = PData.toList(ba);
        assertEquals(5, bl.size());
        assertEquals(1.23, bl.get(0));
    }

    @Test
    void toSet()
    {
        Double[] ba = {1.23,2d,3.14,2d,1.23,3.141,2d};

        Set<Double> bl = PData.toSet(ba);
        assertEquals(4, bl.size());
        assertTrue(bl.contains(1.23));
    }

    @Test
    void toIntegerList()
    {
        int[] iarr = {2,1,3,4};
        List<Integer> il = PData.toIntegerList(iarr);
        assertEquals(il.get(0).getClass(), Integer.class);

        String[] sarr = {"23","3","7777778"};
        List<Integer> sl = PData.toIntegerList(sarr);
        assertEquals(il.get(0).getClass(), Integer.class);

        List<String> sl1 = Arrays.asList("23","3","7777778");
        List<Integer> sl2 = PData.toIntegerList(sl1);
        assertEquals(sl2.get(0).getClass(), Integer.class);
    }

    @Test
    void caseChange()
    {
        List<String> sl = Arrays.asList("aaa","sdSs","A AsC");
        PData.makeElementsUpperCase(sl);

        for(String s : sl){
            for (int i = 0; i < s.length(); i++) {
                assertTrue(Character.isUpperCase(s.charAt(i)) || s.charAt(i) == ' ');
            }
        }

        PData.makeElementsLowerCase(sl);
        for(String s : sl){
            for (int i = 0; i < s.length(); i++) {
                assertTrue(Character.isLowerCase(s.charAt(i)) || s.charAt(i) == ' ');
            }
        }
    }

    @Test
    void filter()
    {
        List<Integer> li = Arrays.asList(1,2,3,4,5,6);
        assertEquals(3, PData.filterCollection(li, v -> v < 4).size());
        assertEquals(1, PData.filterCollection(li, v -> v+2 < 4).size());

        Integer[] ai = {1,2,3,4,5,6};
        assertEquals(3, PData.filterArray(ai, v -> v <= 3).size());
    }

    @Test
    void join()
    {
        String[] sarr = {"str1", "str2", "emptyOne"};
        assertEquals(22, PData.join(sarr, " , ").length());
        assertEquals(17, PData.join(sarr, " , ", 2).length());

        List<String> sli = Arrays.asList("str1", "str2", "emptyOne");
        assertEquals(22, PData.join(sli, " , ").length());
        assertEquals(17, PData.join(sli, " , ", 2).length());
    }

    @Test
    void getFromMapByIndex()
    {
        Map<Integer, Integer> doubleNumbers = new LinkedHashMap<>();
        doubleNumbers.put(1,2);
        doubleNumbers.put(2,4);
        doubleNumbers.put(3,6);
        doubleNumbers.put(4,8);

        assertEquals(2, PData.getMapKeyFromIndex(1, doubleNumbers));
        assertEquals(4, PData.getMapKeyFromIndex(3, doubleNumbers));

        assertEquals(4, PData.getMapValueFromIndex(1, doubleNumbers));
        assertEquals(8, PData.getMapValueFromIndex(3, doubleNumbers));
        assertThrows(IllegalArgumentException.class, () -> PData.getMapKeyFromIndex(-1, doubleNumbers));
        assertThrows(IllegalArgumentException.class, () -> PData.getMapKeyFromIndex(doubleNumbers.size(), doubleNumbers));
    }

    @Test
    void toMap()
    {
        class All
        {
            public String getSmth()
            {
                return "ee";
            }
        }

        class Her extends All
        {
            String s1, s2;

            public Her(String s1, String s2)
            {
                this.s1 = s1;
                this.s2 = s2;
            }

            public String getS1() { return s1; }
        }

        List<Her> list = new ArrayList<>();
        list.add(new Her("32","dd"));
        list.add(new Her("33","dd1"));
        list.add(new Her("34","dd2"));
        Set<Her> set = new HashSet<>(list);

        Map<String, Her> map = PData.toHashMap(Her::getS1, list);
        assertEquals("32", map.get("32").getS1());

        map = PData.toHashMap(Her::getS1, set);
        assertEquals(list.size(), map.size());

        map = PData.toHashMap(Her::getSmth, set);
        assertEquals(1, map.size());

        map = PData.toHashMap(Object::toString, new ArrayList<>());
        assertEquals(0, map.size());
    }

    @Test
    void deepCopy2DOf()
    {
        Double[][] ia = {{2d,1d},{4d,3d}};
        Double[][] iac = PData.deepCopy2DOf(ia);
        ia[0][0] = 12d;
        assertEquals(2d, iac[0][0]);
        assertEquals(12d, ia[0][0]);
    }

    @Test
    void get3DList()
    {
        List<List<List<Integer>>> tdList = PData.get3DList(2,1);
        tdList.get(1).get(0).add(2);
    }

}