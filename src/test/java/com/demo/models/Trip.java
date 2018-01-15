package com.demo.models;

public class Trip {

    private double price;
    private String hour;

    public Trip() {    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Trip { " +
                "price = " + price +
                ", hour = '" + hour + '\'' +
                '}';
    }
}
