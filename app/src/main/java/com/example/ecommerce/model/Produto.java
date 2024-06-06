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

    public String getDescplant() {
        return descplant;
    }


    public String getNomeplant() {
        return nomeplant;
    }


    public String getPriceplant() {
        return priceplant;
    }

}

