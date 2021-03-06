package com.moeller.millage.millage.Village;

import android.content.Context;

import com.moeller.millage.millage.R;
import com.moeller.millage.millage.Ressource;
import com.moeller.millage.millage.StartpageActivity;

import java.util.Map;

public class VillageSlot {
    String inhalt;
    int level;
    StartpageActivity context;

    //Referenz auf die Ressourcen
     Ressource food;
    Map <String, Ressource> ressources;


    public VillageSlot(String str, int lvl, Context context) {
        inhalt = str;
        level = lvl;
        this.context = (StartpageActivity) context;
        ressources = this.context.getAllRessources();
    }


    public String toString() {
        if (level == 0) {
            return inhalt;
        } else {
            String anzeigen = inhalt + "\n" + "lvl: " + level;
            return anzeigen;
        }
    }

    public boolean isBuilding() {
        if (level == 0) {
            return false;
        } else {
            return true;
        }
    }

    public int UpgradeKosten() {
        return level * 2;
    }

    public void upgrade(String str) {
        level = level + 1;
        if (str == context.getResources().getText(R.string.building_2).toString()){
            ressources.get(Ressource.FOOD).changeIncome(level);
        }
    }
    public String getBuilding (){
        return this.inhalt;
    }

    public void abreissen() {
        level = 0;
        inhalt = "Wiese";
    }

    public void build_1() {
        inhalt = context.getResources().getText(R.string.building_1).toString();
        level = 1;
    }

    public void build_2() {
        inhalt = context.getResources().getText(R.string.building_2).toString();
        level = 1;
        ressources.get(Ressource.FOOD).changeIncome(1);
    }

    public void build_3() {
        inhalt = context.getResources().getText(R.string.building_3).toString();
        level = 1;
    }

    public void build_4() {
        inhalt = context.getResources().getText(R.string.building_4).toString();
        level = 1;
    }

    public void build_5() {
        inhalt = context.getResources().getText(R.string.building_5).toString();
        level = 1;
    }

}
