package com.petrosp.assist.util;

import java.util.HashSet;
import java.util.Set;

public final class PStrings
{
    private PStrings() { }

    /**
     * From a given String-path, returns the fileName if it exists. e.g.:
     *
     * fold1/childF/test.txt -> text.txt
     * fold1//childF//test.txt -> text.txt
     * fold1\childF\test.txt -> text.txt
     * fold1/childF/ -> empty String
     * fold1/childF -> empty String
     * fold1 -> empty String
     * test.txt -> test.txt
     *
     * @return The fileName or an empty String if it doesn't exist.
     */
    public static String extractFileName(final String path)
    {
        boolean containsDot = false;
        String fn = "";
        for (int i = path.length()-1; i > 0; i--) {
            char currentChar = path.charAt(i);

            if(currentChar == '.'){
                char leftChar = path.charAt(i-1);
                if(leftChar == '/' || leftChar == '\\' || i == path.length()-1) {
                    fn = "";
                    break;
                }

                fn = path;
                containsDot = true;
            }
            else if(currentChar == '/' || currentChar == '\\'){
                if(containsDot){
                    return path.substring(i+1);
                }
                else{
                    return "";
                }
            }
        }

        return fn;
    }

    /**
     * From a given String-path extract only the directory path if it is valid. e.g.:
     *
     * fold1/childF/test.txt -> fold1/childF/
     * fold1//childF//test.txt -> fold1//childF//
     * fold1\childF\test.txt -> fold1\childF\
     * a.b -> empty String
     * t.txt -> empty String
     *
     * @return The directory path or an empty String if it isn't valid.
     */
    public static String extractPath(final String path)
    {
        String ssb = path;
        boolean containsDot = false;
        boolean containsSlash = false;
        for (int i = path.length()-1; i > 0; i--) {
            char currentChar = path.charAt(i);
            if(currentChar == '.'){
                containsDot = true;
                continue;
            }

            if(currentChar == '/' || currentChar == '\\'){
                containsSlash = true;
                if(containsDot){
                    ssb = path.substring(0,i+1);
                }
                break;
            }
        }

        if(containsDot && !containsSlash) return "";
        return isValidPath(ssb) ? ssb : "";
    }

    /**
     * @return {@code true} Only if the provided String contains any of the characters present in the provided char[]
     */
    public static boolean containsAny(final String str, final char[] chars)
    {
        for (int i = 0; i < str.length(); i++) {
            char currChar = str.charAt(i);

            if(Character.isLetterOrDigit(currChar))
                continue;

            for (char c : chars){
                if(c == currChar){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean containsNumbers(final String str)
    {
        return str.matches(".*\\d.*");
    }

    public static boolean containsOnlyNumbers(final String str)
    {
        char[] chars = str.toCharArray();

        for (char c : chars) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsLetters(final String str)
    {
        return str.matches(".*[a-zA-Z].*");
    }

    public static boolean containsOnlyLetters(final String str)
    {
        char[] chars = str.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsOnlyLettersAndSpaces(final String str)
    {
        char[] chars = str.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }

        return true;
    }

    public static boolean containsOnlyAlphanumeric(final String str)
    {
        char[] chars = str.toCharArray();

        for (char c : chars) {
            if(!Character.isLetterOrDigit(c) && c != ' ') {
                return false;
            }
        }

        return true;
    }

    /**
     * @return {@code true} Only if the provided String contains any character that is not alphanumeric nor whitespace
     */
    public static boolean containsSymbols(final String str)
    {
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isLetterOrDigit(str.charAt(i)) && str.charAt(i) != ' '){
                return true;
            }
        }
        return false;
    }

    /**
     * @return Only the number of letter characters in the String.
     */
    public static int countLetters(final String s)
    {
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if(Character.isLetter(currChar)){
                counter++;
            }
        }

        return counter;
    }

    /**
     * @return Only the number of distinct letter characters in the String.
     */
    public static int countDistinctLetters(final String s)
    {
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if(Character.isLetter(currChar)){
                charSet.add(currChar);
            }
        }

        return charSet.size();
    }

    /**
     * @return Only the number of digit characters in the String.
     */
    public static int countNumbers(final String s)
    {
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if(Character.isDigit(currChar)){
                counter++;
            }
        }

        return counter;
    }

    /**
     * @return Only the number of distinct digit characters in the String.
     */
    public static int countDistinctNumbers(final String s)
    {
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if(Character.isDigit(currChar)){
                charSet.add(currChar);
            }
        }

        return charSet.size();
    }

    /**
     * @return Only the number of characters that are not alphanumeric not whitespace in the String.
     */
    public static int countSymbols(final String s)
    {
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if(!Character.isLetterOrDigit(currChar) && currChar != ' '){
                counter++;
            }
        }

        return counter;
    }

    /**
     * @return Only the number of distinct characters that are not alphanumeric not whitespace in the String.
     */
    public static int countDistinctSymbols(final String s)
    {
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if(!Character.isLetterOrDigit(currChar) && currChar != ' '){
                charSet.add(currChar);
            }
        }

        return charSet.size();
    }

    public static int countOccurrences(final String s, final char c)
    {
        return (int) s.chars().filter(v -> v == c).count();
    }

    public static int countOccurrences(final String s, final String sub)
    {
        if(sub.length() == 0) return 0;

        String replaced = s.replace(sub, "");
        return (s.length() - replaced.length()) / sub.length();
    }


    /**
     * Checks whether an Object can be casted to a String.
     * @param ob Object to be checked.
     * @return {@code false} if an exception occurs while converting.
     */
    public static boolean isStringLiteral(final Object ob)
    {
        try{
            String s = (String)ob;
            return true;
        }catch (ClassCastException e){
            return false;
        }
    }

    /**
     * @param strNum String to be checked
     * @return {@code true} if the string can be converted to a double and is within the bounds.
     */
    public static boolean isValidDouble(final String strNum)
    {
        if (strNum == null) {
            return false;
        }

        String maxValidNum = String.valueOf(Double.MAX_VALUE);
        if(strNum.length() < maxValidNum.length()){
            try{
                double d = Double.parseDouble(strNum);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        else if(strNum.length() > maxValidNum.length()){
            return false;
        }
        else{
            if(maxValidNum.equals(strNum))
                return true;

            try{
                double d = Double.parseDouble(strNum);
                return d != Double.MAX_VALUE;
            }catch (Exception e){
                return false;
            }
        }
    }

    /**
     * @param strNum String to be checked
     * @return {@code true} if the string can be converted to a long and is within the bounds.
     */
    public static boolean isValidLong(final String strNum)
    {
        if (strNum == null) {
            return false;
        }

        String maxValidNum = String.valueOf(Long.MAX_VALUE);
        if(strNum.length() < maxValidNum.length()){
            try{
                long d = Long.parseLong(strNum);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        else if(strNum.length() > maxValidNum.length()){
            return false;
        }
        else{
            if(maxValidNum.equals(strNum))
                return true;

            try{
                double d = Long.parseLong(strNum);
                return d != Long.MAX_VALUE;
            }catch (Exception e){
                return false;
            }
        }
    }

    /**
     * @param strNum String to be checked
     * @return {@code true} if the string can be converted to a long and is within the bounds.
     */
    public static boolean isValidInt(final String strNum)
    {
        if (strNum == null) {
            return false;
        }

        String maxValidNum = String.valueOf(Integer.MAX_VALUE);
        if(strNum.length() < maxValidNum.length()){
            try{
                long d = Integer.parseInt(strNum);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        else if(strNum.length() > maxValidNum.length()){
            return false;
        }
        else{
            if(maxValidNum.equals(strNum))
                return true;

            try{
                double d = Integer.parseInt(strNum);
                return d != Integer.MAX_VALUE;
            }catch (Exception e){
                return false;
            }
        }
    }

    /**
     * @return {@code true} Only if the provided String has the form of "a@b.c
     */
    public static boolean isValidEmail(final String email)
    {
        int index = email.indexOf("@");
        if(index == -1 || index == 0)
            return false;
        if(!email.substring(index).contains(".") || email.charAt(email.length()-1) == '.' || email.charAt(index+1) == '.')
            return false;

        return true;
    }

    /**
     * @param pass
     * @param minChars Inclusive number of minimum characters the String needs to contain.
     * @param maxChars Inclusive number of maximum characters the String needs to contain, or null for no restriction.
     * @return {@code true} Only if all the flags passed are met.
     */
    public static boolean isValidPassword(final String pass,
                                          final int minChars,
                                          final Integer maxChars,
                                          final boolean needsNumbers,
                                          final boolean needsSymbols)
    {
        if(pass.length() < minChars)
            return false;

        if(maxChars != null && pass.length() > maxChars)
            return false;

        if(needsNumbers && !PStrings.containsNumbers(pass))
            return false;

        if(needsSymbols && !PStrings.containsSymbols(pass))
            return false;

        return true;
    }



    private static boolean isValidPath(final String sb)
    {
        char[] invalidFolderNameChars = {'>', '>', '*', '?', '|'};

        if(sb.isEmpty()){
            return false;
        }
        if(sb.length() == 1 && !Character.isLetterOrDigit(sb.charAt(0))){
            return false;
        }

        return !containsAny(sb, invalidFolderNameChars);
    }
}
