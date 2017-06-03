package com.moeller.millage.millage;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.view.Menu;

import com.moeller.millage.millage.Village.VillageSlot;

import static android.R.attr.id;


public class VillageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_village, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart", "ONSTART");
        //Initialer Aufbau des Spielfelds
        VillageSlot[] blueprint = {new VillageSlot("Farm", 1), new VillageSlot("Farm", 2), new VillageSlot("Gras", 0), new VillageSlot("Farm", 1) };
        BuildVillage(blueprint);




    }


    public void BuildVillage(VillageSlot[] blueprint) {
        /*
           Wird mit einem Plan für das neue Spielfeld aufgerufen (ein Stringarray)
           und aktualisiert die Anzeige auf dem Bildschirm
        */
        GridView gridview = (GridView) getView().findViewById(R.id.grid_layout_village);
        gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3){
                VillageSlot slot =  (VillageSlot) adapter.getItemAtPosition(position);


                PopupMenu popup = new PopupMenu(getContext(), v);

                //Menü-Referenz
                Menu menu = popup.getMenu();

                //VillageSlot Referenz = slot

                if (slot.isBuilding()){
                    menu.add("Upgrade");
                    MenuItem kosten = menu.add(Integer.toString(slot.UpgradeKosten()));
                    menu.add("Abreissen");
                }
                else {
                    menu.add("Haus");
                    menu.add("Farm");
                    menu.add("Schule");
                    menu.add("AuraTower");
                    menu.add("Kirche");
                }

                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup_village, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getTitle() == "Upgrade"){

                        }

                        return true;
                    }
                    });





                        popup.show();



                TextView textView = (TextView) v;
                textView.setText(slot.toString());
            }
        });
    }
}
