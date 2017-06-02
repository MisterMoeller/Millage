package com.moeller.millage.millage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;


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
        String[] blueprint = {"Farm", "Schule", "Universität", "4", "5", "6", "7", "", "9", "10", "11", "12", "13", "Schule", "Universität", "4", "5", "6", "7", "", "9", "10", "11", "12", "13", "Schule", "Universität", "4", "5", "6", "7", "", "9", "10", "11", "12", "13", "Schule", "Universität", "4", "5", "6", "7", "", "9", "10", "11", "12", "13"};
        BuildVillage(blueprint);

    }



    public void BuildVillage(String[] blueprint) {
        /*
           Wird mit einem Plan für das neue Spielfeld aufgerufen (ein Stringarray)
           und aktualisiert die Anzeige auf dem Bildschirm
        */
        GridView gridview = (GridView) getView().findViewById(R.id.grid_layout_village);
        gridview.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.textview_for_gridview_village, blueprint));

    }

}
