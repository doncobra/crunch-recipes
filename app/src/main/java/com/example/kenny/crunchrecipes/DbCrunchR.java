package com.example.kenny.crunchrecipes;

public class DbCrunchR {


    private long idR;
    private String recipe;
    private int favo;
    private String ing_1;
    private String ing_2;
    private String ing_3;
    private String ing_4;
    private String ing_5;
    private int heal;
    private int buff;
    private int level;
    private int time;


    public DbCrunchR(long idR, String recipe, int favo, String ing_1, String ing_2, String ing_3, String ing_4, String ing_5, int heal, int buff, int level, int time) {
        this.idR = idR;
        this.recipe = recipe;
        this.favo = favo;
        this.ing_1 = ing_1;
        this.ing_2 = ing_2;
        this.ing_3 = ing_3;
        this.ing_4 = ing_4;
        this.ing_5 = ing_5;
        this.heal = heal;
        this.buff = buff;
        this.level = level;
        this.time = time;
    }
    public String getRecipe() {return recipe;   }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }


    public int getfavo() {
        return favo;
    }
    public void setfavo(int favo) {this.favo = favo; }

    public void setIng_1(String ing_1) {this.ing_1 = ing_1;  }
    public String getIng_1() {
        return ing_1;
    }

    public void setIng_2(String ing_2) {
        this.ing_2 = ing_2;
    }
    public String getIng_2() {
        return ing_2;
    }

    public void setIng_3(String ing_3) {
        this.ing_3 = ing_3;
    }
    public String getIng_3() {
        return ing_3;
    }

    public void setIng_4(String ing_4) {this.ing_4 = ing_4;  }
    public String getIng_4() {
        return ing_4;
    }

    public void setIng_5(String ing_5) {
        this.ing_5 = ing_5;
    }
    public String getIng_5() {
        return ing_5;
    }

    public int getHeal() {
        return heal;
    }
    public void setHeal(int heal) {this.heal = heal;  }

    public int getbuff() {
        return buff;
    }
    public void setbuff(int buff) {
        this.buff = buff;
    }

    public int getlevel() {
        return level;
    }
    public void setlevel(int level) {
        this.level = level;
    }

    public int gettime() {
        return time;
    }
    public void settime(int time) {
        this.time = time;
    }

    public long getId() {return idR; }
    public void setId(long id) {
        this.idR = id;
    }


    @Override
    public String toString() {
        String output = "Rezept: " + recipe + ", " + heal + " & " + buff + "B";

        return output;
    }
}