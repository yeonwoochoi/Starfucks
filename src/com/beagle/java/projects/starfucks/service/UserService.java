package com.beagle.java.projects.starfucks.service;

import com.beagle.java.projects.starfucks.controller.BaristaController;
import com.beagle.java.projects.starfucks.repository.FoodRepository;
import com.beagle.java.projects.starfucks.repository.OrderRepository;
import com.beagle.java.projects.starfucks.repository.UserRepository;
import com.beagle.java.projects.starfucks.utils.Utils;

import java.io.File;
import java.util.ArrayList;

public class UserService {


    /**
     * Method to add customer data to CustomerRepository.txt when a customer places an order
     * @return (boolean) success
     */
    public boolean createCustomer(String[] orderName, int[] orderPrice, int[] orderCount) {
        ArrayList arrayList = new ArrayList();
        FoodRepository foodRepository = new FoodRepository();
        OrderRepository orderRepository = new OrderRepository();
        UserRepository userRepository = new UserRepository();
        Utils utils = new Utils();

        String eachName;
        String eachPrice;
        String eachCount;

        // get consumed calculateOrderTime
        int totalTime = foodRepository.calculateOrderTime(orderName, orderCount);
        String  totalTimeStr = utils.intToString(totalTime);

        // create ArrayList orderContent
        for (int i = 0; i < orderName.length; i++) {
            eachName = orderName[i];
            eachPrice = utils.intToString(orderPrice[i]);
            eachCount = utils.intToString(orderCount[i]);

            String[] object = {eachName, eachPrice, eachCount};


            arrayList.add(object);
            arrayList.trimToSize();
        }

        // get orderNumber
        String orderNumberStr = orderRepository.givingOrderNumber();

        // Store received order in CustomerRepository.txt
        String inputStr = orderNumberStr + "/" + totalTimeStr + "/O" + ";";
        boolean success1 = userRepository.saveToCustomerRegistory(inputStr);
        boolean success2 = orderRepository.updateOrderNumber();
        boolean success;
        if (success1 && success2) {
            success = true;
        } else {
            success = false;
        }
        return success;
    }




    public boolean pickUpFood(int orderNumber) {
        UserRepository userRepository = new UserRepository();
        String inputData = orderNumber + "/" + ;
        userRepository.updateCustomerRegistory();
    }





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
