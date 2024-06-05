package com.example.ecommerce.model;

public class Produto {

    int imgplant;
    String nomeplant;
    String descplant;

    String priceplant;

    public int getImgplant() {
        return imgplant;
    }

    public Produto(int imgplant, String descplant, String nomeplant, String priceplant) {
        this.imgplant = imgplant;
        this.nomeplant = nomeplant;
        this.descplant = descplant;
        this.priceplant = priceplant;
    }

    public void setImgplant(int imgplant) {
        this.imgplant = imgplant;
    }

    public String getDescplant() {
        return descplant;
    }

    public void setDescplant(String descplant) {
        this.descplant = descplant;
    }

    public String getNomeplant() {
        return nomeplant;
    }

    public void setNomeplant(String nomeplant) {
        this.nomeplant = nomeplant;
    }

    public String getPriceplant() {
        return priceplant;
    }

    public void setPriceplant(String priceplant){
        this.priceplant = priceplant;
    }
}

