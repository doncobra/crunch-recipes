package com.example.kenny.crunchrecipes;

public class DbMemo {

    private String recipe;
    private int hearts;
    private int bonus;
    private long id;


    public DbMemo(String recipe, int hearts, int bonus, long id) {
        this.recipe = recipe;
        this.hearts = hearts;
        this.bonus = bonus;
        this.id = id;
    }


    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }


    public int getHearts() {
        return hearts;
    }

    public void setHearts(String location) {
        this.hearts = hearts;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        String output = "Rezept: " + recipe + ", " + hearts + " & " + bonus + "B";

        return output;
    }
}