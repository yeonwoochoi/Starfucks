package com.beagle.java.projects.starfucks.controller;

import com.beagle.java.projects.starfucks.utils.Utils;

public class UserController {

    /**
     * Method to check if input quantity is natural number greater than 0 and less than 10
     * @param inputQuantity
     * @return (boolean) success
     */
    public boolean checkOrderQuantity(int inputQuantity) {
        boolean adequacy;

        if (inputQuantity > 0 && inputQuantity < 10) {
            adequacy = true;
        } else {
            adequacy = false;
        }

        return adequacy;
    }

}
