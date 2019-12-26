package com.example.wowebackand.models;

public class AppoitementForm extends Appoitement
{
    private long today;

    private String uyikozeName;

    public String getUyikozeName() {
        return uyikozeName;
    }

    public void setUyikozeName(String uyikozeName) {
        this.uyikozeName = uyikozeName;
    }

    public long getToday() {
        return today;
    }

    public void setToday(long today) {
        this.today = today;
    }
}
