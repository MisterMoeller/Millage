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
    public final VillageSlot[] blueprint= new VillageSlot[1];
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        blueprint[0] = new VillageSlot("Farm", 1, getActivity());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_village, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart", "ONSTART");
        //Initialer Aufbau des Spielfelds
        BuildVillage(blueprint);




    }


    public void BuildVillage(VillageSlot[] blueprint) {
        /*
           Wird mit einem Plan für das neue Spielfeld aufgerufen (ein Stringarray)
           und aktualisiert die Anzeige auf dem Bildschirm
        */
        final GridView gridview = (GridView) getView().findViewById(R.id.grid_layout_village);
        gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint));

        final VillageSlot[] blueprint2 = blueprint;



        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3){
                final VillageSlot slot =  (VillageSlot) adapter.getItemAtPosition(position);


                PopupMenu popup = new PopupMenu(getContext(), v);

                //Menü-Referenz
                Menu menu = popup.getMenu();

                //VillageSlot Referenz = slot
                //Baumaterial-Referenz
                StartpageActivity x = (StartpageActivity) getActivity().getParent();
                //Ressource material = x.getRessource(Ressource.MATERIAL);

                if (slot.isBuilding()){
                    menu.add("Upgrade");
                    MenuItem kosten = menu.add(Integer.toString(slot.UpgradeKosten()));
                    menu.add("Abreissen");
                }
                else {
                    menu.add(R.string.building_1);
                    menu.add(R.string.building_2);
                    menu.add(R.string.building_3);
                    menu.add(R.string.building_4);
                    menu.add(R.string.building_5);
                }

                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup_village, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getTitle() == "Upgrade"){
                            slot.upgrade();
                            gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
                        }
                        if (item.getTitle() == "Abreissen"){
                            slot.abreissen();
                            gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
                        }
                        if (item.getTitle() == getResources().getText(R.string.building_1)){
                            slot.build_1();
                            gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
                        }
                        if (item.getTitle() == getResources().getText(R.string.building_2)){
                            slot.build_2();
                            gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
                        }
                        if (item.getTitle() == getResources().getText(R.string.building_3)){
                            slot.build_3();
                            gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
                        }
                        if (item.getTitle() == getResources().getText(R.string.building_4)){
                            slot.build_4();
                            gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
                        }
                        if (item.getTitle() == getResources().getText(R.string.building_5)){
                            slot.build_5();
                            gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
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
