package com.beagle.java.projects.starfucks.controller;

import com.beagle.java.projects.starfucks.service.FoodService;
import com.beagle.java.projects.starfucks.utils.Utils;


/**
 * Class consisting of methods to find out whether data about food is an appropriate value and data needed in foodRepository.txt
 */
public class FoodController {

    Utils utils = new Utils();
    FoodService foodService = new FoodService();



    /**
     * check whether input food number is between 1 and 20
     * @param foodNumberStr
     * @return (boolean) success
     */
    public boolean checkInputFoodNumber(String foodNumberStr) {
        boolean success;
        int foodNumber = utils.StringToInt(foodNumberStr);

        if (foodNumber >= 1 && foodNumber <= 20) {
            success = true;
        } else {
            success = false;
        }
        return success;
    }

    /**
     * check whether input quantity is more than 0
     * @param inputQuantityStr
     * @return (boolean) success
     */
    public boolean checkInputQuantity(String inputQuantityStr) {
        boolean success;
        int inputQuantity = utils.StringToInt(inputQuantityStr);

        if (inputQuantity > 0) {
            success = true;
        } else {
            success = false;
        }
        return success;
    }

    /**
     * A method to find food data in the foodRepository that matches the input data
     * @param foodNumberStr
     * @return (String[]) {foodNumber, foodName, foodPrice, foodConsumedTime}
     */
    public String[] findFoodData(String foodNumberStr) {
        String[] foodColumn = foodService.readFoodColumn(foodNumberStr + "/");
        return foodColumn;
    }


    /**
     * show Menu List
     * @return (String) menu list
     */
    public String showMenuList() {
        String menu = foodService.showMenuList();
        return menu;
    }



}
