package com.example.webservicesmobileapps;

public class Carpark {

    private int lots;
    private String type;
    private int available;

    public Carpark(int lots, String type, int available) {
        this.lots = lots;
        this.type = type;
        this.available = available;
    }

    public int getLots() {
        return lots;
    }

    public void setLots(int lots) {
        this.lots = lots;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "CARPARK AVAILABLE"+ "\n"+ "\nArea: " + lots + "\nLot Type: " + type + "\nAvailable:" + available + "\n";
    }
}
