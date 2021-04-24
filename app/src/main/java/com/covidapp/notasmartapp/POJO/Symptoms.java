package com.covidapp.notasmartapp.POJO;

public class Symptoms {

    private String typeName;
    private String percentage;

    public Symptoms(String typeName, String percentage) {
        this.typeName = typeName;
        this.percentage = percentage;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
