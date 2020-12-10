package com.covidapp.notasmartapp.Data.Models;

public class DiseaseSample {

    private String DiseaseName;
    private String DiseaseDesc;
    private String Precaution1;
    private String Precaution2;
    private String Precaution3;
    private String Precaution4;


    public DiseaseSample(String diseaseName, String diseaseDesc, String precaution1, String precaution2, String precaution3, String precaution4) {
        DiseaseName = diseaseName;
        DiseaseDesc = diseaseDesc;
        Precaution1 = precaution1;
        Precaution2 = precaution2;
        Precaution3 = precaution3;
        Precaution4 = precaution4;
    }

    public String getDiseaseName() {
        return DiseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        DiseaseName = diseaseName;
    }

    public String getDiseaseDesc() {
        return DiseaseDesc;
    }

    public void setDiseaseDesc(String diseaseDesc) {
        DiseaseDesc = diseaseDesc;
    }

    public String getPrecaution1() {
        return Precaution1;
    }

    public void setPrecaution1(String precaution1) {
        Precaution1 = precaution1;
    }

    public String getPrecaution2() {
        return Precaution2;
    }

    public void setPrecaution2(String precaution2) {
        Precaution2 = precaution2;
    }

    public String getPrecaution3() {
        return Precaution3;
    }

    public void setPrecaution3(String precaution3) {
        Precaution3 = precaution3;
    }

    public String getPrecaution4() {
        return Precaution4;
    }

    public void setPrecaution4(String precaution4) {
        Precaution4 = precaution4;
    }
}
