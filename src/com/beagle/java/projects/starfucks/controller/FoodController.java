package com.beagle.java.projects.starfucks.controller;

import com.beagle.java.projects.starfucks.repository.FoodRepository;
import com.beagle.java.projects.starfucks.utils.Utils;

import java.util.ArrayList;

public class FoodController {


    /**
     * Method to make receipt of input order
     * @param orderNumber
     * @param orderContent
     * @return (String) Converting the receipt to a string using the toString method of the Receipt class
     */
    public String makeReceipt(int orderNumber, ArrayList orderContent) {

        // Load data stored in Order object and declare it as variable
        Utils utils = new Utils();
        orderContent.trimToSize();

        // Declare String array to store ordered food name and price
        String[] nameArr = new String[orderContent.size()];
        String[] priceArr = new String[orderContent.size()];
        String[] countArr = new String[orderContent.size()];

        // Save the ordered food name and price
        for (int i = 0; i < orderContent.size(); i++) {
            String[] eachStrArr = (String[]) orderContent.get(i);
            nameArr[i] = eachStrArr[0];
        }

        for (int i = 0; i < orderContent.size(); i++) {
            String[] eachStrArr = (String[]) orderContent.get(i);
            priceArr[i] = eachStrArr[1];
        }

        for (int i = 0; i < orderContent.size(); i++) {
            String[] eachStrArr = (String[]) orderContent.get(i);
            countArr[i] = eachStrArr[2];
        }

        int[] newPriceArr = utils.StringArrayToIntArray(priceArr);
        int[] newCountArr = utils.StringArrayToIntArray(countArr);


        // variable declaration for making receipt
        String output;
        String content = "";


        // Calculate the total price
        int total = 0;
        for (int i = 0; i < newPriceArr.length; i++) {
            total += (newPriceArr[i] * newCountArr[i]);
        }

        // add receipt content
        for (int i = 0; i < nameArr.length; i++) {
            content += nameArr[i] + "  " + newPriceArr[i] + "  " + newCountArr[i] + "\n";
        }

        output = "order number : " + orderNumber + "\n\n" + content + "\n" + "total :  " + total + "\n\n";


        return output;

    }


}
