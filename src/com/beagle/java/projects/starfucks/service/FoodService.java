package com.beagle.java.projects.starfucks.service;

import com.beagle.java.projects.starfucks.repository.FoodRepository;
import com.beagle.java.projects.starfucks.utils.Utils;

import java.io.File;
import java.util.ArrayList;


public class FoodService {

    /**
     * Method that returns all data in FoodRepository.txt as string
     * @return String menu
     */
    public String  ShowMenuList() {

        FoodRepository foodRepository = new FoodRepository();


        String beverageStr = "== beverage ==\n\n";
        String dessertStr = "== dessert ==\n\n";

        String foodStr = foodRepository.readAllFoodData();
        String[] foodArr = foodStr.split(";");
        String[] beverageArr;
        String[] dessertArr;
        for (int i = 0 ; i < 10; i++) {
            beverageArr = foodArr[i].split("/");
            for (int j = 0 ; j < beverageArr.length; j++) {
                beverageStr += beverageArr[j] + " ";
            }
            beverageStr += "\n";
        }
        beverageStr += "\n\n";

        for (int i = 10; i < foodArr.length; i++) {
            dessertArr = foodArr[i].split("/");
            for (int j = 0 ; j < dessertArr.length; j++) {
                dessertStr += dessertArr[j] + " ";
            }
            dessertStr += "\n";
        }
        dessertStr += "\n\n";


        String output = beverageStr + dessertStr;

        return output;
    }




    /**
     * Method to check if input data is a value stored in Food Repository
     * @param orderName
     * @return (boolean) success
     */
    public boolean CheckOrderName(String orderName) {


        // Import data from FoodRepository.txt and create array by subtracting only data related to food name
        FoodRepository foodRepository = new FoodRepository();
        String[] productNameArr = foodRepository.readFoodRow(1);



        // count the number of times input data and repository data match
        int count = 0;
        for (int i = 0; i < productNameArr.length; i++) {
            if(productNameArr[i].equals(orderName)) {
                count += 1;
            }
        }


        // Determine success with i, the number of times input data and repository data match.
        boolean success;
        if (count == 0) {
            success = false;
        } else if (count == 1){
            success = true;
        } else {
            success = false;
        }


        return success;

    }


}
