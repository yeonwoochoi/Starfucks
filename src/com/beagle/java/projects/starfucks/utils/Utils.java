package com.beagle.java.projects.starfucks.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * class with methods used throughout the project
 */
public class Utils {

    /**
     * Method to remove null value among data in input string array
     * @param inputArr
     * @return string array with null values ​​removed
     */
    public static String[] removeNullValue(String[] inputArr) {

        List<String> list = new ArrayList<String>();

        for(String s : inputArr) {
            if(s != null && s.length() > 0) {
                list.add(s);
            }
        }

        String[] outputArr = list.toArray(new String[list.size()]);
        return outputArr;
    }


    /**
     * Convert a String type variable to an int type
     * @param input
     * @return Value converted to String type
     */
    public static String intToString(int input)  {
        String output = Integer.toString(input);
        return output;
    }



    /**
     * Convert variable of int type to String type
     * @param input
     * @return Value converted to int type
     */
    public static int StringToInt(String input) {
        int output = Integer.parseInt(input);
        return output;
    }


    /**
     * Method to convert String array to int array
     * @param input
     * @return (int) array
     */
    public static int[] StringArrayToIntArray (String[] input) {
        Utils utils = new Utils();
        int[] newObjects = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            newObjects[i] = utils.StringToInt(input[i]);
        }
        return newObjects;
    }


    /**
     * Method to convert int array to string array
     * @param input
     * @return (String) array
     */
    public static String[] IntArrayToStringArray (int[] input) {
        Utils utils = new Utils();
        String[] newObjects = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            newObjects[i] = utils.intToString(i);
        }
        return newObjects;
    }


}
