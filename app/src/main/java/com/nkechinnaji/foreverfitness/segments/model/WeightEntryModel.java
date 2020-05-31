package com.nkechinnaji.foreverfitness.segments.model;

/**
 * Created by Nkechi Nnaji on 5/30/20.
 * Description:
 */

public class WeightEntryModel {

    private String  date;
    private String  weight;
    private String  imageUrl;

    public WeightEntryModel(String date, String weight, String imageUrl) {
        this.date = date;
        this.weight = weight;
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public String getWeight() {
        return weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
