package com.example.saltpayhack.models;

public class CompanyModel {

    private int id;

    private String companyName;

    private float calculatedRating;

    // (?) What data does the web scraping return

    private float scrapedRating;
    // location ? -> distance from city centre
    // social media platforms ?
    // social media following?


    public CompanyModel() {}

    public CompanyModel(int id, String companyName, float scrapedRating) {
        this.id = id;
        this.companyName = companyName;
        this.scrapedRating = scrapedRating;
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

    public float getScrapedRating() {
        return scrapedRating;
    }

    public void setScrapedRating(float scrapedRating) {
        this.scrapedRating = scrapedRating;
    }
}
