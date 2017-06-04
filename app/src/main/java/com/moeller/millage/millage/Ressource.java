package com.moeller.millage.millage;

import android.widget.TextView;

/**
 * Created by MrM on 01.06.2017.
 */

/**
 * represents a ressource
 */
public class Ressource implements Tickable{

    public static String MATERIAL = "material";
    public static String FOOD = "food";
    public static String WORKER = "worker";
    public static String SCIENCE = "science";
    public static String CULTURE = "culture";
    private int amount;
    private int income;
    private TextView amount_view;
    private TextView income_view;

    Ressource(int amount, int income, TextView amount_view, TextView income_view){
        this.amount = amount;
        this.income = income;
        this.amount_view = amount_view;
        this.income_view = income_view;
        updateView();
    }

    public void tick(){
        this.awardIncome();
    }


    private void awardIncome(){
        this.amount += income;
        this.amount_view.setText(Integer.toString(this.amount));
    }
    public int getAmount(){
        return this.amount;
    }


    public void updateView(){
        this.amount_view.setText(Integer.toString(this.amount));
        this.income_view.setText(Integer.toString(this.income));
    }

    /**
     * adds amount to the ressource
     * @param amount
     * @return true on success
     */
    public boolean addRessources(int amount){
        this.amount += amount;
        updateView();
        return true;
    }

    /**
     * subtracts amount of the ressouce if enough is available
     * @param amount
     * @return true on success
     */
    public boolean subRessources(int amount){
        if(amount > this.amount){
            return false;
        }
        this.amount -= amount;
        updateView();
        return true;
    }
    public void changeIncome(int amount){
        this.income = income + amount;
        updateView();
    }
}