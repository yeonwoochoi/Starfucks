package com.beagle.java.projects.starfucks.service;



import com.beagle.java.projects.starfucks.repository.BaristaRepository;
import com.beagle.java.projects.starfucks.utils.Utils;



public class BaristaService {


    /**
     * A method that returns the barista number to execute the order received when creating a new Order.
     * @return (int) Barista number to fulfill the received order
     */
    public int giveOrderToBarista() {

        // variable declaration
        BaristaRepository baristaRepository = new BaristaRepository();
        Utils utils = new Utils();
        String output;


        // Declared with the last declared barista String array
        String[] objects = baristaRepository.readAllBaristaData();
        String lastStr = objects[objects.length-1];
        String[] lastArr = lastStr.split("/");

        int lastArrCount = utils.StringToInt(lastArr[1]);
        if (lastArrCount >= 10) {
            // When you're doing 10 things until the last barista

            output = baristaRepository.createNewBarista();
        } else {
            // When there is a barista who can still afford

            output = baristaRepository.updateBarista();
        }
        int outputInt = utils.StringToInt(output);
        return outputInt;
    }

}
