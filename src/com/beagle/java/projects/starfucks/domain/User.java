package com.beagle.java.projects.starfucks.domain;

public class User {

    private int orderNumber;
    private boolean holdingBell;


    public User (int orderIndex, boolean holdingBell) {
        this.orderNumber = orderIndex;
        this.holdingBell = holdingBell;;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public boolean isHoldingBell() {
        return this.holdingBell;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setHoldingBell(boolean holdingBell) {
        this.holdingBell = holdingBell;
    }
}
