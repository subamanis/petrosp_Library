package com.petrosp.util;

import java.io.Console;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Helper class with various static useful functions for input, number formatting, binary data size transformations,
 * network utilities, execution information details and encryption/hashing
 *
 *
 * @author Petros Papatheodorou
 * created on 21/05/2020
 */
public final class PUtils
{
    public static final long BYTES_OF_KB = 1024;
    public static final long BYTES_OF_MB = 1048576;


    /**
     * Formats a number to the specified decimal places.
     * @param value The origin double number
     * @param decimals The specified number of decimals
     * @param roundUp If true, then a roundup is performed if the next digit after the last wanted decimal is >= 5
     * @return The new formatted double number
     */
    public static double toFixed(final double value, final int decimals, final boolean roundUp)
    {
        if(roundUp){
            BigDecimal bd = new BigDecimal(String.valueOf(value));
            bd = bd.setScale(decimals, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }else{
            String strNum = String.valueOf(value);
            int dotPos = strNum.indexOf(".");
            return Double.parseDouble(strNum.substring(0, Math.min(dotPos + decimals + 1, strNum.length())));
        }
    }


    /**
     * Returns
     * @param value Provided number
     * @param lowerBound Lowest value to be compared
     * @param upperBound Highest value to be compared
     * @return the value if it is between the provided bounds, otherwise, the closest bound.
     */
    public static int clamp(final int value, final int lowerBound, final int upperBound)
    {
        return Math.max(lowerBound, Math.min(value, upperBound));
    }

    /**
     * Returns
     * @param value Provided number
     * @param lowerBound Lowest value to be compared
     * @param upperBound Highest value to be compared
     * @return the value if it is between the provided bounds, otherwise, the closest bound.
     */
    public static long clamp(final long value, final long lowerBound, final long upperBound)
    {
        return Math.max(lowerBound, Math.min(value, upperBound));
    }

    /**
     * Returns
     * @param value Provided number
     * @param lowerBound Lowest value to be compared
     * @param upperBound Highest value to be compared
     * @return the value if it is between the provided bounds, otherwise, the closest bound.
     */
    public static float clamp(final float value, final float lowerBound, final float upperBound)
    {
        return Math.max(lowerBound, Math.min(value, upperBound));
    }

    /**
     * Returns
     * @param value Provided number
     * @param lowerBound Lowest value to be compared
     * @param upperBound Highest value to be compared
     * @return the value if it is between the provided bounds, otherwise, the closest bound.
     */
    public static double clamp(double value, final double lowerBound, final double upperBound)
    {
        return Math.max(lowerBound, Math.min(value, upperBound));
    }





    //__________________________________________ BINARY TRANSFORMATIONS ________________________________________________

    public static double bytesToKBs(final long bytes) { return (double)bytes / BYTES_OF_KB; }

    public static double bytesToMBs(final long bytes) { return (double)bytes / BYTES_OF_MB; }

    public static long KBsToBytes(final int KBs) { return KBs * BYTES_OF_KB; }

    public static long MBsToBytes(final int MBs) { return MBs * BYTES_OF_MB ; }





    //____________________________________________ ENCRYPTION - HASHING ________________________________________________

    /**
     * Calculate the hash of a String and return it as a hex String.
     * @param str String to be hashed.
     * @return Hex String representation of the hash.
     */
    public static String MD5Hash(final String str)
    {
        StringBuilder hex = null;

        try {
            //calculate md5 hash
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            //transform it to hex String
            hex = new StringBuilder();
            for (byte b : digest) {
                hex.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return (hex == null) ? null : hex.toString();
    }

    /**
     * Calculate the hash of a byte[] and return it as a hex String.
     * @param chunk byte[] to be hashed.
     * @return Hex String representation of the hash.
     */
    public static String MD5Hash(final byte[] chunk)
    {
        StringBuilder hex = null;

        try {
            //calculate md5 hash
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(chunk);
            byte[] digest = md.digest();

            //transform it to hex String
            hex = new StringBuilder();
            for (byte b : digest) {
                hex.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return (hex == null) ? null : hex.toString();
    }




    //____________________________________________ NETWORKING __________________________________________________________

    /**
     * Get the IP of the current machine automatically.
     * @return The IP of the machine, or throw {@code IllegalStateException}.
     */
    public static String getCurrentIP() throws IllegalStateException
    {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            String ip = socket.getLocalAddress().getHostAddress();
            return ip;
        }catch (SocketException | UnknownHostException e){
            System.err.println("WARNING - the current IP of the machine cannot be retrieved");
            e.printStackTrace();
            throw new IllegalStateException("No IP");
        }
    }




    //____________________________________________ INPUT PARSING _______________________________________________________

    /**
     * Ensures an int input from the user between two bounds.
     * @param promptMessage Message to be displayed.
     * @param lowerBound Inclusive lower bound.
     * @param upperBound Inclusive upper bound.
     * @return Given int from the user.
     */
    public static int getIntInputWithBounds(final String promptMessage, final int lowerBound, final int upperBound)
    {
        Scanner sc = new Scanner(System.in);
        int input = -1;
        while (true) {
            System.out.print(promptMessage);
            try {
                input = sc.nextInt();
            }catch (InputMismatchException e){
                sc.nextLine();
            }
            if(input >= lowerBound && input <= upperBound){
                break;
            }else{
                System.out.println("Incorrect input.");
            }
        }
        return input;
    }

    /**
     * Ensures an int input from the user between two bounds or stops asking when encounters an escape integer.
     * @param promptMessage Message to be displayed.
     * @param lowerBound Inclusive lower bound.
     * @param upperBound Inclusive upper bound.
     * @param escape Value that if it is given, the function returns.
     * @return -1 if the @param escape is encountered, or the int input from the user otherwise.
     */
    public static int getIntInputWithBoundWithEscapeCharacter(final String promptMessage, final int lowerBound,
                                                              final int upperBound, final int escape)
    {
        Scanner sc = new Scanner(System.in);
        int input = -1;
        while (true) {
            System.out.print(promptMessage);
            try {
                input = sc.nextInt();
            }catch (InputMismatchException e){
                sc.nextLine();
            }
            if((input >= lowerBound && input <= upperBound) || input == escape){
                break;
            }else{
                System.out.println("Incorrect input.");
            }
        }
        return input;
    }

    /**
     * Gets a string input that is hidden if it is run from CMD, else gets a normal String from Scanner.
     * Gets a seconds string in the same way and validates if they match, else the user is prompted again.
     * @param prompt Message to be displayed before the user enters a String.
     */
    public static String getPasswordInputWithCheck(final String prompt)
    {
        Scanner sc = new Scanner(System.in);
        Console console = System.console();
        String input;

        while (true){
            char[] password;
            if(console == null){
                System.out.println(prompt);
                password = sc.next().toCharArray();
            }else{
                password = console.readPassword(prompt); //WORKS ONLY FROM CMD
            }
            input = new String(password);

            System.out.println("Please type the password again:");
            if(console == null){
                password = sc.next().toCharArray();
            }else{
                password = console.readPassword(); //WORKS ONLY FROM CMD
            }
            String newInput = new String(password);

            if(input.equals(newInput)){
                break;
            }

            System.out.print("\nPasswords do not match, please try again");
        }

        return input;
    }

    /**
     * Gets a string input that is hidden if it is run from CMD, else gets a normal String from Scanner.
     * No checks are being made.
     * @param prompt Message to be displayed before the user enters a String.
     */
    public static String getPasswordInputNoCheck(final String prompt)
    {
        Scanner sc = new Scanner(System.in);
        Console console = System.console();

        char[] password;
        if (console == null) {
            System.out.println(prompt);
            password = sc.next().toCharArray();
        } else {
            password = console.readPassword(prompt); //WORKS ONLY FROM CMD
        }

        return new String(password);
    }




    //____________________________________________ EXECUTION UTILITIES _________________________________________________

    /**
     * Prints the time in the specified time unit, between 2 time instances of the program.
     */
    public static void printExecutionTime(final long startingTime, final long endTime, final PTimeUnit timeUnit)
    {
        System.out.println("Total Execution Time : "+
                (PTimeUnit.Milliseconds.convert(endTime-startingTime, timeUnit)) + " " + timeUnit.unitName);
    }

    /**
     * Prints the time in ms between 2 time instances of the program.
     */
    public static void printExecutionTimeMs(final long startingTime, final long endTime)
    {
        printExecutionTime(startingTime, endTime, PTimeUnit.Milliseconds);
    }

    /**
     * Prints the memory that is currently used.
     */
    public static void printMemoryUsage()
    {
        Runtime runtimeInstant = Runtime.getRuntime();
        System.out.println("Memory in use: "+( (runtimeInstant.totalMemory() - runtimeInstant.freeMemory())/BYTES_OF_MB)+" MBs.");
    }

    /**
     * Function to kill the program and show error information.
     */
    public static void die(final String errorMsg)
    {
        System.out.println(errorMsg);
        System.exit(1);
    }
}