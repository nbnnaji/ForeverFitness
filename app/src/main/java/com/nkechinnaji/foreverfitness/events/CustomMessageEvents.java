package com.nkechinnaji.foreverfitness.events;

/**
 * Created by Nkechi Nnaji on 3/30/20.
 * Description:
 */
public class CustomMessageEvents {
    String weight;
    String date;

    public CustomMessageEvents(String weight, String date) {
        this.weight = weight;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
