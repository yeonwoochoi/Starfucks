package com.beagle.java.projects.starfucks.controller;

import com.beagle.java.projects.starfucks.service.BaristaService;
import com.beagle.java.projects.starfucks.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Class consisting of methods that are called when barista receives an order and processes the order
 */
public class BaristaController {

    BaristaService baristaService = new BaristaService();
    Utils utils = new Utils();


    /**
     * when barista gets order, give work to some barista and update BaristaRepository.txt
     * @return (String) indexStr of barista who received work.
     */
    public String getOrder(String waitingTimeStr) {
        int waitingTime = utils.StringToInt(waitingTimeStr);
        int baristaIndex = baristaService.giveOrderToBarista();
        String baristaIndexStr = utils.intToString(baristaIndex);

        // Completed order processing. => Vibration Bell Recovery
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                callGuestToPickFood(baristaIndexStr);
            }
        };
        timer.schedule(timerTask, waitingTime * 1000);

        return baristaIndexStr;
    }


    /**
     * Method to call the customer and withdraw the vibration bell when the food is ready
     * @param baristaIndexStr
     * @return (boolean) success
     */
    public boolean callGuestToPickFood (String baristaIndexStr) {
        boolean success = baristaService.reduceOrderCount(baristaIndexStr);
        return success;
    }


}
