package com.nkechinnaji.foreverfitness.segments.onboarding;

/**
 * Created by Nkechi Nnaji on 4/19/20.
 * Description:
 */
public class Items {

    String Title,Description;
    int ScreenImg;

    public Items(String title, String description, int screenImg) {
        Title = title;
        Description = description;
        ScreenImg = screenImg;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }
}