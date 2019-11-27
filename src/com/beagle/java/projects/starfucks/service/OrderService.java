package com.beagle.java.projects.starfucks.service;

import com.beagle.java.projects.starfucks.repository.FoodRepository;
import com.beagle.java.projects.starfucks.repository.OrderRepository;
import com.beagle.java.projects.starfucks.repository.UserRepository;
import com.beagle.java.projects.starfucks.utils.Utils;

import java.util.ArrayList;

public class OrderService {

    public boolean createOrder(String[] orderName, int[] orderPrice, int[] orderCount) {
        ArrayList arrayList = new ArrayList();
        FoodRepository foodRepository = new FoodRepository();
        OrderRepository orderRepository = new OrderRepository();
        BaristaService baristaService = new BaristaService();
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
        int orderNumber = utils.StringToInt(orderNumberStr);

        // get baristaIndex
        int baristaNumber = baristaService.giveOrderToBarista();


        // Store received order in CustomerRepository.txt
        String inputStr = orderNumberStr + "/" + totalTimeStr + "/O" + ";";
        boolean success = userRepository.saveToCustomerRegistory(inputStr);

        return success;
    }
}
