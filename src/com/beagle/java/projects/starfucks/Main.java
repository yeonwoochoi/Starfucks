package com.beagle.java.projects.starfucks;

import com.beagle.java.projects.starfucks.controller.FoodController;
import com.beagle.java.projects.starfucks.controller.UserController;
import com.beagle.java.projects.starfucks.repository.BaristaRepository;
import com.beagle.java.projects.starfucks.repository.FoodRepository;
import com.beagle.java.projects.starfucks.utils.Utils;


public class Main {
    public static void main(String[] args) {
        FoodRepository foodRepository = new FoodRepository();
        Utils utils = new Utils();
        String[] arr = new String[100];
        System.out.println(arr.length);
        arr = foodRepository.readAllFoodData().split(";");
        System.out.println(arr.length);

        BaristaRepository baristaRepository = new BaristaRepository();
        System.out.println(baristaRepository.readAllBaristaData()[0]);

        FoodController foodController = new FoodController();
        System.out.println(foodController.CheckOrderName("Iced Coffee"));

        UserController userController = new UserController();
        System.out.println(userController.checkOrderQuantity(10));


    }
}
