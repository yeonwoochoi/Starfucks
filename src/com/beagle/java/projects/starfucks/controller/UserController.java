package com.beagle.java.projects.starfucks.controller;


import com.beagle.java.projects.starfucks.service.UserService;
import com.beagle.java.projects.starfucks.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Classes that contain appropriateness of data about users and methods that are called when customers order, receive food, and leave the cafe
 */
public class UserController {

    UserService userService = new UserService();
    Utils utils = new Utils();



    /**
     * when getting order, save order data and return receipt, order number and waiting time.
     * @param foodName
     * @param foodPrice
     * @param foodCount
     * @return (String[3]) index 0 : order number / index 1 : waiting time / index 2 : receipt
     */
    public String[] getOrder (String[] foodName, String[] foodPrice, String[] foodCount) {


        // save order data related to user in CustomerRepository.txt
        String[] orderArr =  userService.createCustomer(foodName, foodPrice, foodCount);

        // variable declaration
        String orderNumberStr = orderArr[0];
        String waitingTimeStr = orderArr[1];
        int waitingTime = utils.StringToInt(waitingTimeStr);


        // make receipt
        String receipt = userService.makeReceipt(orderNumberStr, foodName, foodPrice, foodCount);

        // Completed order processing. => Vibration Bell Recovery
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("주문번호 " + orderNumberStr + " 고객님. 주문하신 음료와 디저트 나왔습니다.");
                userService.updateCustomer(orderNumberStr, waitingTimeStr);
            }
        };
        timer.schedule(timerTask, waitingTime * 1000);

        // return order data
        String[] output = new String[3];
        output[0] = orderNumberStr;
        output[1] = waitingTimeStr;
        output[2] = receipt;

        return output;
    }

    /**
     * when customer leaves Cafe, delete data from CustomerRegistory.txt
     * @param orderNumberStr
     * @return (boolean) success
     */
    public boolean leavingCafe (String orderNumberStr) {
        boolean success = userService.deleteCustomer(orderNumberStr);
        return success;
    }


    /**
     * Method that indicates whether the customer left the cafe, received the ordered drink, or is still waiting
     * @param orderNumberStr
     * @return (int) 0 : waiting foods / 1: already received foods / 2 : already left cafe
     */
    public int checkUserLocation(String orderNumberStr) {
        String[] userColumnArr = userService.readUserColumn(orderNumberStr + "/");
        String isHoldingBell = userColumnArr[2];
        if (isHoldingBell.equals("O")){
            return 0;
        } else if (isHoldingBell.equals("X")){
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Method showing a list of unprocessed orders
     * @return (String) unprocessed orders
     */
    public String showOrderList () {
        String[] orderIndex = userService.readUserRow(0);
        String[] waitingTime = userService.readUserRow(1);
        String[] holdingBell = userService.readUserRow(2);
        String output = "";

        for (int i = 0; i < orderIndex.length; i++) {
            if (holdingBell[i].equals("O")) {
                output += "주문 번호 : " + orderIndex[i] + "  대기시간 :  " + waitingTime[i] + " 진동벨 소지 여부 : " + holdingBell[i] + "\n";
            }
        }
        return output;
    }

}
