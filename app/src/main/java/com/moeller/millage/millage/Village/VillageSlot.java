package com.moeller.millage.millage.Village;

import android.view.View;

import com.moeller.millage.millage.R;

import static android.R.id.button1;

public class VillageSlot {
    String inhalt;
    int level;


    public VillageSlot (String str, int lvl){
        inhalt = str;
        level = lvl;
    }


    public String toString(){
        if (level == 0){
            return inhalt;
        }
        else {
            String anzeigen = inhalt+"\n" + "lvl: "+level;
            return anzeigen;
        }
    }

    public boolean isBuilding() {
        if (level ==0){
            return false;
        }
        else {
            return true;
        }
        }

        public int UpgradeKosten(){
            return level*2;
        }
}
