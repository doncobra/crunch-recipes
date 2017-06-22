package com.example.kenny.crunchrecipes;

public class DbCrunchI {


    private long idI;
    private String item;
    private String location;


    public DbCrunchI(long idI, String item, String location) {
        this.idI = idI;
        this.location = location;
        this.item = item;
    }

    public String getItem() {
        return item;
    }
    public void setItem(String item) {this.item = item; }

    public String getLocation() {
        return location;
    }
    public void setLocation(String Location) {this.location = location; }

    public String getIdI() {
        return item;
    }
    public void setIdI(long IdI) {this.idI = idI; }



    @Override
    public String toString() {
        String output = "Material: " + item + "Wo: " + location;

        return output;
    }
}