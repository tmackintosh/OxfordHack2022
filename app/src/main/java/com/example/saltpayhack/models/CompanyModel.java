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
}
