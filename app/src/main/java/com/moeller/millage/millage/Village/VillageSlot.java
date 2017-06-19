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

    // Die Kosten des Gebäudes das in diesem Slot steht. Werden beim Bau eines Gebäudes definiert.
    int StartMaterialKosten;
    int StartFoodKosten;
    int StartScienceKosten;
    int StartCultureKosten;
    double kostenFaktor;

    /*
    income_ressource wird nach einer Änderung zum Einkommen addiert und dann auf 0 gesetzt.
    income_ressource_gesamt sammelt den Bonus zum Rückgängigmachen beim abreissen
     */
    int income_material = 0;
    int income_material_gesamt = 0;
    int income_food = 0;
    int income_food_gesamt = 0;
    int income_science = 0;
    int income_science_gesamt = 0;
    int income_culture = 0;
    int income_culture_gesamt = 0;


    //Referenz auf die Ressourcen
    Ressource food;
    Map<String, Ressource> ressources;


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


    public String getBuilding() {
        return this.inhalt;
    }


    public int UpgradeKosten(String welcheRessource) {
        if (welcheRessource == "material") {
            return (int) (StartMaterialKosten * Math.pow(kostenFaktor, level));
        }
        if (welcheRessource == "food") {
            return (int) (StartFoodKosten * Math.pow(kostenFaktor, level));
        }
        if (welcheRessource == "science") {
            return (int) (StartScienceKosten * Math.pow(kostenFaktor, level));
        }
        if (welcheRessource == "culture") {
            return (int) (StartCultureKosten * Math.pow(kostenFaktor, level));
        } else
            return 0;


    }

    //true on success
    public boolean upgrade(String str) {
        if (canAfford("upgrade")) {
            context.getRessource("material").changeRessources(-UpgradeKosten("material"));
            context.getRessource("food").changeRessources(-UpgradeKosten("food"));
            context.getRessource("science").changeRessources(-UpgradeKosten("science"));
            context.getRessource("culture").changeRessources(-UpgradeKosten("culture"));
            level = level + 1;
            if (str == context.getResources().getText(R.string.building_2).toString()) {
                income_food = income_food + level;
                updateIncome();
            }

            return true;
        }
        else return false;
    }


    //true on success
    public boolean build_1() {
        if (canAfford("building_1")) {
            inhalt = context.getResources().getText(R.string.building_1).toString();
            level = 1;
            //Kosten lvl 1 = Basiswert für Skalierung
            StartMaterialKosten = context.getResources().getInteger(R.integer.building_1_material);
            StartFoodKosten = context.getResources().getInteger(R.integer.building_1_food);
            StartScienceKosten = context.getResources().getInteger(R.integer.building_1_science);
            StartCultureKosten = context.getResources().getInteger(R.integer.building_1_culture);
            kostenFaktor = ((double) context.getResources().getInteger(R.integer.building_1_kostenfaktor)) / 100;
            context.getRessource("material").changeRessources(-context.getResources().getInteger(R.integer.building_1_material));
            context.getRessource("food").changeRessources(-context.getResources().getInteger(R.integer.building_1_food));
            context.getRessource("science").changeRessources(-context.getResources().getInteger(R.integer.building_1_science));
            context.getRessource("culture").changeRessources(-context.getResources().getInteger(R.integer.building_1_culture));
            return true;
        } else return false;
    }

    //true on success
    public boolean build_2() {
        if (canAfford("building_2")) {
            inhalt = context.getResources().getText(R.string.building_2).toString();
            level = 1;
            //Kosten lvl 1 = Basiswert für Skalierung
            StartMaterialKosten = context.getResources().getInteger(R.integer.building_2_material);
            StartFoodKosten = context.getResources().getInteger(R.integer.building_2_food);
            StartScienceKosten = context.getResources().getInteger(R.integer.building_2_science);
            StartCultureKosten = context.getResources().getInteger(R.integer.building_2_culture);
            kostenFaktor = ((double) context.getResources().getInteger(R.integer.building_2_kostenfaktor)) / 100;
            context.getRessource("material").changeRessources(-context.getResources().getInteger(R.integer.building_2_material));
            context.getRessource("food").changeRessources(-context.getResources().getInteger(R.integer.building_2_food));
            context.getRessource("science").changeRessources(-context.getResources().getInteger(R.integer.building_2_science));
            context.getRessource("culture").changeRessources(-context.getResources().getInteger(R.integer.building_2_culture));
            income_food = 1;
            updateIncome();
            return true;
        } else return false;
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
    public void abreissen() {
        income_material = -income_material_gesamt;
        income_food = -income_food_gesamt;
        income_science = -income_science_gesamt;
        income_culture = -income_culture_gesamt;
        updateIncome();
        income_material_gesamt = 0;
        income_food_gesamt = 0;
        income_science_gesamt = 0;
        income_culture_gesamt = 0;
        level = 0;
        inhalt = "Wiese";
    }

    public boolean canAfford(String action) {
        if (action == "upgrade") {
            if (UpgradeKosten("material") <= context.getRessource("material").getAmount() &&
                    (UpgradeKosten("food") <= context.getRessource("food").getAmount()) &&
                    (UpgradeKosten("science") <= context.getRessource("science").getAmount()) &&
                    (UpgradeKosten("culture") <= context.getRessource("culture").getAmount())) {
                return true;
            } else
                return false;
        } else if (action == "building_1") {
            if (context.getResources().getInteger(R.integer.building_1_material) <= context.getRessource("material").getAmount() &&
                    (context.getResources().getInteger(R.integer.building_1_food) <= context.getRessource("food").getAmount()) &&
                    (context.getResources().getInteger(R.integer.building_1_science) <= context.getRessource("science").getAmount()) &&
                    (context.getResources().getInteger(R.integer.building_1_culture) <= context.getRessource("culture").getAmount())) {
                return true;
            } else
                return false;
        } else if (action == "building_2") {
            if (context.getResources().getInteger(R.integer.building_2_material) <= context.getRessource("material").getAmount() &&
                    (context.getResources().getInteger(R.integer.building_2_food) <= context.getRessource("food").getAmount()) &&
                    (context.getResources().getInteger(R.integer.building_2_science) <= context.getRessource("science").getAmount()) &&
                    (context.getResources().getInteger(R.integer.building_2_culture) <= context.getRessource("culture").getAmount())) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    public void updateIncome (){
        context.getRessource("material").changeIncome(income_material);
        income_material_gesamt = income_material_gesamt + income_material;
        income_material = 0;
        context.getRessource("food").changeIncome(income_food);
        income_food_gesamt = income_food_gesamt + income_food;
        income_food = 0;
        context.getRessource("science").changeIncome(income_science);
        income_science_gesamt = income_science_gesamt + income_science;
        income_science = 0;
        context.getRessource("culture").changeIncome(income_culture);
        income_culture_gesamt = income_culture_gesamt + income_culture;
        income_culture = 0;
    }

}
