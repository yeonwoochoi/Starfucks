package com.beagle.java.projects.starfucks.domain;

public class Barista {
    private int baristaIndex;
    private int orderCount;
    private boolean working;

    public Barista(int baristaIndex, int orderCount, boolean working) {
        this.baristaIndex = baristaIndex;
        this.orderCount = orderCount;
        this.working = working;
    }

    public int getBaristaIndex() {
        return this.baristaIndex;
    }

    public int getOrderCount() {
        return this.orderCount;
    }

    public boolean isWorking() {
        return this.working;
    }

    public void setBaristaIndex(int baristaIndex) {
        this.baristaIndex = baristaIndex;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }
}
