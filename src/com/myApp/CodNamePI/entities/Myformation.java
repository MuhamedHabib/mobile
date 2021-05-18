package com.myApp.CodNamePI.entities;

public class Myformation {
    private int id;
    private String libelle,description,type,image,date;




    public Myformation(int id, String libelle, String description, String type, String image) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.type = type;
        this.image = image;
    }

    public Myformation() {


    }

    public Myformation(String libelle, String description, String type, String image) {
        this.libelle = libelle;
        this.description = description;
        this.type = type;
        this.image = image;
    }

    public Myformation(String toString, String libelle, String description, String type, String format) {


    }

    public Myformation(String description) {
        this.description=description;
    }


    @Override
    public String toString() {
        return "Myformation{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", discription='" + description + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDiscription() {
        return description;
    }

    public void setDiscription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
