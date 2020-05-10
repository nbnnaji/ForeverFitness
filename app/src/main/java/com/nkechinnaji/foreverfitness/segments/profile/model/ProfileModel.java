package com.nkechinnaji.foreverfitness.segments.profile.model;

/**
 * Created by Nkechi Nnaji on 4/19/20.
 * Description:
 */
public class ProfileModel {



    String username;
    String dateOfBirth;
    String gender;
    String currentWeight;
    String targetGoalWeight;
    String height;
    String email;



    public ProfileModel(){}

    public ProfileModel( String username, String dateOfBirth, String gender, String currentWeight, String targetGoalWeight, String height, String email) {

        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.currentWeight = currentWeight;
        this.targetGoalWeight = targetGoalWeight;
        this.height = height;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(String currentWeight) {
        this.currentWeight = currentWeight;
    }

    public String getTargetGoalWeight() {
        return targetGoalWeight;
    }

    public void setTargetGoalWeight(String targetGoalWeight) {
        this.targetGoalWeight = targetGoalWeight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
