package com.tiodev.vegtummy.Model;

public class ResModel  {
    private String id, img, tittle, des, ing, category;

    public ResModel() {
    }

    public ResModel(String id, String img, String tittle, String des,  String ing, String category) {
        this.id = id;
        this.img = img;
        this.tittle = tittle;
        this.des = des;
        this.ing = ing;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getIng() {
        return ing;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
