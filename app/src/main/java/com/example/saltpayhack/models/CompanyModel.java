package com.example.saltpayhack.models;

public class CompanyModel {

    private int id;

    private String companyName;
    private String address;

    private float calculatedRating;

    private float totalRatings; // total reviews left by users online
    private float locationRating;
    private float ratingsRating;
    private float socialMediaPresence;

    public CompanyModel() {}

    public CompanyModel(int id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public float getCalculatedRating() {
        return calculatedRating;
    }

    public void setCalculatedRating(float calculatedRating) {
        this.calculatedRating = calculatedRating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(float totalRatings) {
        this.totalRatings = totalRatings;
    }

    public float getLocationRating() {
        return locationRating;
    }

    public void setLocationRating(float locationRating) {
        this.locationRating = locationRating;
    }

    public float getRatingsRating() {
        return ratingsRating;
    }

    public void setRatingsRating(float ratingsRating) {
        this.ratingsRating = ratingsRating;
    }

    public float getSocialMediaPresence() {
        return socialMediaPresence;
    }

    public void setSocialMediaPresence(float socialMediaPresence) {
        this.socialMediaPresence = socialMediaPresence;
    }
}
