package com.petrosp.assist.util;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class PData
{
    private PData() { }

    //____________________________________________ CONVERSION OPERATIONS _______________________________________________

    /**
     * Convert a byte primitive array to an Object Byte[]
     * @param bytesPrim Primitive byte[]
     * @return Object Byte[]
     */
    public static Byte[] toObjectArray(final byte[] bytesPrim)
    {
        Byte[] array = new Byte[bytesPrim.length];
        int i = 0;
        for (byte b : bytesPrim){
            array[i++] = b;
        }

        return array;
    }

    /**
     * Convert an int primitive array to an Object Integer[]
     * @param intsPrim Primitive int[]
     * @return Object Integer[]
     */
    public static Integer[] toObjectArray(final int[] intsPrim)
    {
        Integer[] array = new Integer[intsPrim.length];
        int i = 0;
        for (int b : intsPrim){
            array[i++] = b;
        }

        return array;
    }

    /**
     * Convert an long primitive array to an Object Long[]
     * @param intsPrim Primitive long[]
     * @return Object Long[]
     */
    public static Long[] toObjectArray(final long[] intsPrim)
    {
        Long[] array = new Long[intsPrim.length];
        int i = 0;
        for (long b : intsPrim){
            array[i++] = b;
        }

        return array;
    }

    /**
     * Convert an float primitive array to an Object Float[]
     * @param intsPrim Primitive float[]
     * @return Object Float[]
     */
    public static Float[] toObjectArray(final float[] intsPrim)
    {
        Float[] array = new Float[intsPrim.length];
        int i = 0;
        for (float b : intsPrim){
            array[i++] = b;
        }

        return array;
    }

    /**
     * Convert an double primitive array to an Object Double[]
     * @param intsPrim Primitive double[]
     * @return Object Double[]
     */
    public static Double[] toObjectArray(final double[] intsPrim)
    {
        Double[] array = new Double[intsPrim.length];
        int i = 0;
        for (double b : intsPrim){
            array[i++] = b;
        }

        return array;
    }

    /**
     * Convert an char primitive array to an Object Character[]
     * @param intsPrim Primitive char[]
     * @return Object Character[]
     */
    public static Character[] toObjectArray(final char[] intsPrim)
    {
        Character[] array = new Character[intsPrim.length];
        int i = 0;
        for (char b : intsPrim){
            array[i++] = b;
        }

        return array;
    }



    /**
     * Convert a Byte Object array to a primitive byte[].
     * @param bytesObj Array of Byte Objects.
     * @return Primitive byte[].
     */
    public static byte[] toPrimitiveArray(final Byte[] bytesObj)
    {
        byte[] bytes = new byte[bytesObj.length];
        for(int i = 0; i < bytesObj.length; i++){
            bytes[i] = bytesObj[i];
        }

        return bytes;
    }

    /**
     * Convert a Integer Object array to a primitive int[].
     * @param integersObj Array of Integer Objects.
     * @return Primitive int[].
     */
    public static int[] toPrimitiveArray(final Integer[] integersObj)
    {
        int[] ints = new int[integersObj.length];
        for(int i = 0; i < integersObj.length; i++){
            ints[i] = integersObj[i];
        }

        return ints;
    }

    /**
     * Convert a Long Object array to a primitive long[].
     * @param longsObj Array of Long Objects.
     * @return Primitive long[].
     */
    public static long[] toPrimitiveArray(final Long[] longsObj)
    {
        long[] longs = new long[longsObj.length];
        for(int i = 0; i < longsObj.length; i++){
            longs[i] = longsObj[i];
        }

        return longs;
    }

    /**
     * Convert a Double Object array to a primitive double[].
     * @param doublesObj Array of Double Objects.
     * @return Primitive double[].
     */
    public static double[] toPrimitiveArray(final Double[] doublesObj)
    {
        double[] ints = new double[doublesObj.length];
        for(int i = 0; i < doublesObj.length; i++){
            ints[i] = doublesObj[i];
        }

        return ints;
    }

    /**
     * Convert a Float Object array to a primitive float[].
     * @param bytesObj Array of Float Objects.
     * @return Primitive float[].
     */
    public static float[] toPrimitiveArray(final Float[] bytesObj)
    {
        float[] floats = new float[bytesObj.length];
        for(int i = 0; i < bytesObj.length; i++){
            floats[i] = bytesObj[i];
        }

        return floats;
    }

    /**
     * Convert a Character Object array to a primitive char[].
     * @param charsObj Array of Character Objects.
     * @return Primitive char[].
     */
    public static char[] toPrimitiveArray(final Character[] charsObj)
    {
        char[] chars = new char[charsObj.length];
        for(int i = 0; i < charsObj.length; i++){
            chars[i] = charsObj[i];
        }

        return chars;
    }


    /**
     * @param <T> Type of the input array (any).
     * @return A List with the items of the input array.
     */
    public static <T> List<T> toList(final T[] arr)
    {
        return Arrays.stream(arr).collect(Collectors.toList());
    }

    /**
     * @param <T> Type of the input array (any).
     * @return A converted Set with the items of the input array.
     */
    public static <T> Set<T> toSet(T[] arr)
    {
        return Arrays.stream(arr).collect(Collectors.toSet());
    }


    public static List<Byte> toByteList(final byte[] intArr)
    {
        return new ArrayList<>(Arrays.asList(toObjectArray(intArr)));
    }

    public static List<Integer> toIntegerList(final int[] intArr)
    {
        return new ArrayList<>(Arrays.asList(toObjectArray(intArr)));
    }

    /**
     * Returns a {@code List<Integer>} by converting every item of the provided {@code String[]}.
     * @param strArray {@code String[]} that needs to have numeric items.
     */
    public static List<Integer> toIntegerList(final String[] strArray) throws NumberFormatException
    {
        List<Integer> intList = new ArrayList<>(strArray.length);
        for (String i : strArray)
        {
            intList.add(Integer.parseInt(i.trim()));
        }

        return intList;
    }

    /**
     * Returns a {@code List<Integer>} by converting every item of the provided {@code List<String>}.
     * @param strList {@code List<String>} that needs to have numeric items.
     */
    public static List<Integer> toIntegerList(final List<String> strList) throws NumberFormatException
    {
        List<Integer> intList = new ArrayList<>(strList.size());
        for (String i : strList)
        {
            intList.add(Integer.parseInt(i.trim()));
        }

        return intList;
    }


    public static List<Double> toDoubleList(final double[] intArr)
    {
        return new ArrayList<>(Arrays.asList(toObjectArray(intArr)));
    }


    /**
     * Converts all the Strings from a List to lowerCase.
     * @param list The List with the Strings.
     */
    public static void makeElementsLowerCase(final List<String> list)
    {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i).toLowerCase();
            list.set(i,s);
        }
    }

    /**
     * Converts all the Strings from a List to upperCase.
     * @param list The List with the Strings.
     */
    public static void makeElementsUpperCase(final List<String> list)
    {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i).toUpperCase();
            list.set(i,s);
        }
    }


    /**
     * @param array Object array of any type.
     * @param predicate A lambda expression like {@code v -> v.startsWith("a")} to filter the array
     * @return A List with the valid items
     */
    public static <T> List<T> filterArray(final T[] array, final Predicate<? super T> predicate) {
        return Arrays.stream(array).filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @param collection Object collection of any type.
     * @param predicate A lambda expression like {@code v -> v.startsWith("a")} to filter the collection.
     * @return A List with the valid items.
     */
    public static <T> List<T> filterCollection(final Collection<T> collection, final Predicate<? super T> predicate) {
        return collection.stream().filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Creates a String by concatenating all the items of the provided array with a provided separator between them.
     * @param separator String to put between each element of the array.
     * @return The created String
     */
    public static <T> String join(final T[] array, final String separator)
    {
        return join(array, separator, 0);
    }

    /**
     * Creates a String by concatenating the items of the provided array with a provided separator between them and a limit
     * that when met, the remaining elements are replaced with 3 dots.
     * @param separator String to put between each element of the array.
     * @return The created String.
     */
    public static <T> String join(final T[] array, final String separator, int limit)
    {
        if (limit > 0 && array.length > limit) {
            return Arrays.stream(array).limit(limit).map(String::valueOf)
                    .collect(Collectors.joining(separator)) + separator + "...";
        }
        return Arrays.stream(array).map(String::valueOf)
                .collect(Collectors.joining(separator));
    }

    /**
     * Creates a String by concatenating the items of the provided collection with a provided separator between them.
     * @param separator String to put between each element of the collection.
     * @return The created String.
     */
    public static <T> String join(final Collection<T> collection, final String separator)
    {
        return join(collection, separator, 0);
    }

    /**
     * Creates a String by concatenating the items of the provided collection with a provided separator between them and a limit.
     * that when met, the remaining elements are replaced with 3 dots.
     * @param separator String to put between each element of the collection.
     * @return The created String.
     */
    public static <T> String join(final Collection<T> collection, final String separator, int limit)
    {
        if (limit > 0 && collection.size() > limit) {
            return collection.stream().limit(limit).map(String::valueOf)
                    .collect(Collectors.joining(separator)) + separator + "...";
        }
        return collection.stream().map(String::valueOf)
                .collect(Collectors.joining(separator));
    }

    /**
     * @return The key of the map that corresponds to the given index. (keep in mind that normal HashMaps are unordered)
     * @throws IllegalArgumentException if the provided index is out of the bounds of the map.
     */
    public static <K,V> K getMapKeyFromIndex(final int index, final Map<K, V> map) throws IllegalArgumentException
    {
        if(index < map.size()){
            int counter = 0;
            for (Map.Entry<K, V> entry : map.entrySet()) {
                if(counter++ == index){
                    return entry.getKey();
                }
            }
        }

        throw new IllegalArgumentException("Provided index is out of bounds");
    }

    /**
     * @return The value of the map that corresponds to the given index. (keep in mind that normal HashMaps are unordered)
     * @throws IllegalArgumentException if the provided index is out of the bounds of the map.
     */
    public static <K,V> V getMapValueFromIndex(final int index, final Map<K, V> map) throws IllegalArgumentException
    {
        if(index < map.size()){
            int counter = 0;
            for (Map.Entry<K, V> entry : map.entrySet()) {
                if(counter++ == index){
                    return entry.getValue();
                }
            }
        }

        throw new IllegalArgumentException("Provided index is out of bounds");
    }

    /**
     * Create a HashMap from a given collection of Objects, that has the objects as value and a specified way to calculate
     * the key for each object by passing a method reference or a lambda. Note that the order of items is not guaranteed
     * to be preserved in the HashMap.
     * @param keyFunc Method reference or lambda used to calculate the key.
     */
    public static <K,V,C extends Collection<V>> HashMap<K,V> toHashMap(final Function<V, K> keyFunc, final C col)
    {
        HashMap<K,V> map = new HashMap<>();
        col.forEach(v -> map.put(keyFunc.apply(v), v));
        return map;
    }

    /**
     * Create a LinkedHashMap from a given collection of Objects, that has the objects as value and a specified way to calculate
     * the key for each object by passing a method reference or a lambda. The order of the items is guaranteed to be
     * preserved.
     * @param keyFunc Method reference or lambda used to calculate the key.
     */
    public static <K,V,C extends Collection<V>> LinkedHashMap<K,V> toLinkedHashMap(final Function<V, K> keyFunc, final C col)
    {
        LinkedHashMap<K,V> map = new LinkedHashMap<>();
        col.forEach(v -> map.put(keyFunc.apply(v), v));
        return map;
    }




        //____________________________________________ COPY OPERATIONS _____________________________________________________

    /**
     * @return New Integer[][] with copied elements from the original.
     */
    public static Integer[][] deepCopy2DOf(final Integer[][] originalArr)
    {
        int oneDSize = originalArr.length;
        Integer[][] newArr = new Integer[oneDSize][originalArr[0].length];
        for (int i = 0; i < originalArr[0].length; i++) {
            for (int j = 0; j < oneDSize; j++) {
                newArr[j][i] = originalArr[j][i];
            }
        }

        return newArr;
    }

    /**
     * @return New Double[][] with copied elements from the original.
     */
    public static Double[][] deepCopy2DOf(final Double[][] originalArr)
    {
        int oneDSize = originalArr.length;
        Double[][] newArr = new Double[oneDSize][originalArr[0].length];
        for (int i = 0; i < originalArr[0].length; i++) {
            for (int j = 0; j < oneDSize; j++) {
                newArr[j][i] = originalArr[j][i];
            }
        }

        return newArr;
    }

    /**
     * @return New String[][] with copied elements from the original.
     */
    public static String[][] deepCopy2DOf(final String[][] originalArr)
    {
        int oneDSize = originalArr.length;
        String[][] newArr = new String[oneDSize][originalArr[0].length];
        for (int i = 0; i < originalArr[0].length; i++) {
            for (int j = 0; j < oneDSize; j++) {
                newArr[j][i] = originalArr[j][i];
            }
        }

        return newArr;
    }


    /**
     * @param oneDLength The size of the first List.
     * @param twoDLength The size of the second List.
     * @return 3D List with the specified type and dimensions
     */
    public static <T> List<List<List<T>>> get3DList(final int oneDLength, final int twoDLength)
    {
        List<List<List<T>>> list3D = new ArrayList<>(oneDLength);
        for (int i=0; i<oneDLength; i++){
            List<List<T>> hourList = new ArrayList<>(twoDLength);
            list3D.add(hourList);
            for (int j = 0; j < twoDLength; j++) {
                hourList.add(new ArrayList<>());
            }
        }

        return list3D;
    }

}
