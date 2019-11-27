package com.beagle.java.projects.starfucks.service;

import com.beagle.java.projects.starfucks.repository.FoodRepository;
import com.beagle.java.projects.starfucks.repository.UserRepository;
import com.beagle.java.projects.starfucks.utils.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class UserService {
    FoodRepository foodRepository = new FoodRepository();
    UserRepository userRepository = new UserRepository();
    Utils utils = new Utils();


    /**
     * Method to add customer data to CustomerRepository.txt when a customer places an order
     * @return (String[]) {orderNumberStr, waitingTimeStr}
     */
    public String[] createCustomer(String[] orderName, String[] orderPrice, String[] orderCount) {

        ArrayList arrayList = new ArrayList();


        String eachName;
        String eachPrice;
        String eachCount;

        int[] orderPriceArr = utils.StringArrayToIntArray(orderPrice);
        int[] orderCountArr = utils.StringArrayToIntArray(orderCount);

        // get consumed calculateOrderTime
        int waitingTime = foodRepository.calculateOrderTime(orderName, orderCountArr);
        String  waitingTimeStr = utils.intToString(waitingTime);

        // create ArrayList orderContent
        for (int i = 0; i < orderName.length; i++) {
            eachName = orderName[i];
            eachPrice = utils.intToString(orderPriceArr[i]);
            eachCount = utils.intToString(orderCountArr[i]);

            String[] object = {eachName, eachPrice, eachCount};


            arrayList.add(object);
            arrayList.trimToSize();
        }

        // get orderNumber
        String orderNumberStr = userRepository.givingOrderNumber();

        // Store received order in CustomerRepository.txt
        String inputStr = orderNumberStr + "/" + waitingTimeStr + "/O" + ";";
        boolean success1 = userRepository.saveToCustomerRegistory(inputStr);
        boolean success2 = userRepository.updateOrderNumber();
        boolean success;
        if (success1 && success2) {
            success = true;
        } else {
            success = false;
        }
        String[] output = new String[2];
        if (success) {
            output[0] = orderNumberStr;
            output[1] = waitingTimeStr;
        }
        return output;
    }


    /**
     * Method that returns user data as an array and returns the row corresponding to the input index
     * @param index
     * @return (String[]) Return an array of data about the corresponding row
     */
    public String[] readUserRow(int index) {


        String[] userArr = userRepository.readAllUserData();

        String[] eachUserArr;
        String[] eachUserRowArr = new String[userArr.length];


        for (int i = 0; i < userArr.length; i++) {
            eachUserArr = userArr[i].split("/");
            eachUserRowArr[i] = eachUserArr[index];
        }

        return eachUserRowArr;
    }



    /**
     * Method to read data corresponding to input data through file path
     * @param content
     * @return (String[]) Return an array of user data for the corresponding column
     */
    public String[] readUserColumn(String content) {
        String filePath = "C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\CustomerRepository.txt";
        String output = "";
        String[] outputArray = new String[3];
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            String[] stringArr;

            while ((line = bufferedReader.readLine()) != null) {
                stringArr = line.split(";");
                for (int i = 0 ; i < stringArr.length; i++) {
                    if (stringArr[i].contains(content)) {
                        output += stringArr[i];
                    }
                }
            }
            bufferedReader.close();

            outputArray = output.split("/");


            return outputArray;
        } catch (FileNotFoundException e) {
            outputArray[0] = String.valueOf(e);
        } catch (IOException e) {
            outputArray[0] = String.valueOf(e);
        }
        return outputArray;
    }


    /**
     * Update CustomerRegistory.txt when customers pick up food.
     * @param orderNumberStr
     * @param waitingTimeStr
     * @return
     */
    public boolean updateCustomer(String orderNumberStr, String waitingTimeStr) {
        String oldData = orderNumberStr + "/" + waitingTimeStr + "/O;";
        String[] inputArr = oldData.split("/");
        String newData = "";
        for (int i = 0; i < inputArr.length-1; i++) {
            newData += inputArr;
        }
        newData += "X;";
        boolean success = userRepository.updateCustomerRegistory(oldData, newData);
        return success;
    }


    /**
     * delete Customer Data in CustomerRegistory.txt when customer get out
     * @return
     */
    public boolean deleteCustomer(String orderNumberStr) {
        String inputStr = orderNumberStr + "/";
        String newStr = "";
        boolean success = userRepository.updateCustomerRegistory(inputStr, newStr);
        return success;
    }




    /**
     * Method to make receipt of input order
     * @param orderNumberStr
     * @param orderName
     * @param orderPrice
     * @param orderCount
     * @return (String) Converting the receipt to a string using the toString method of the Receipt class
     */
    public String makeReceipt(String orderNumberStr, String[] orderName, String [] orderPrice, String[] orderCount) {

        // Load data stored in Order object and declare it as variable

        int[] newPriceArr = utils.StringArrayToIntArray(orderPrice);
        int[] newCountArr = utils.StringArrayToIntArray(orderCount);
        int orderNumber = utils.StringToInt(orderNumberStr);

        // variable declaration for making receipt
        String output;
        String content = "";


        // Calculate the total price
        int total = 0;
        for (int i = 0; i < newPriceArr.length; i++) {
            total += (newPriceArr[i] * newCountArr[i]);
        }

        // add receipt content
        for (int i = 0; i < orderName.length; i++) {
            content += orderName[i] + "  " + newPriceArr[i] + "  " + newCountArr[i] + "\n";
        }

        output = "order number : " + orderNumber + "\n\n" + content + "\n" + "total :  " + total + "\n\n";


        return output;

    }


}
