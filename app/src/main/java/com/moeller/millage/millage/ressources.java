package com.moeller.millage.millage;

/**
 * Created by MrM on 01.06.2017.
 */

class ressources {

    int material = 10;
    int income_material = 1;

    int food = 5;
    int income_food = 0;

    int worker = 0;
    int income_worker = 0;

    int science = 0;
    int income_science = 0;

    int culture = 0;
    int income_culture = 0;


    public void Income() {
        material = material + income_material;
        food = food + income_food;
        worker = worker + income_worker;
        science = science + income_science;
        culture = culture + income_culture;
    }
}
