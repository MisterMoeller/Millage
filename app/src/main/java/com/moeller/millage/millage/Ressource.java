package com.moeller.millage.millage;

import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by MrM on 01.06.2017.
 */

/**
 * represents a ressource
 */
public class Ressource {
    private int amount;
    private int income;
    private TextView amount_view;
    private TextView income_view;

    Ressource(int amount, int income, TextView amount_view, TextView income_view){
        this.amount = amount;
        this.income = income;
        this.amount_view = amount_view;
        this.income_view = income_view;
    }


    public void awardIncome(){
        this.amount += income;
        this.amount_view.setText(this.amount);
    }
}