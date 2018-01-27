package com.moeller.millage.millage.Village;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.moeller.millage.millage.R;
import com.moeller.millage.millage.StartpageActivity;
import com.moeller.millage.millage.Village.VillageSlot;


public class VillageFragment extends Fragment {
    public final VillageSlot[] blueprint = new VillageSlot[2];

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        blueprint[0] = new VillageSlot("Wiese", 0, getActivity());
        blueprint[1] = new VillageSlot("Wiese", 0, getActivity());

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
           Wird mit einem Plan für das neue Spielfeld aufgerufen (ein VillageSlotarray)
           und aktualisiert die Anzeige auf dem Bildschirm
        */
        final GridView gridview = (GridView) getView().findViewById(R.id.grid_layout_village);
        gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint));

        final VillageSlot[] blueprint2 = blueprint;


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {


                final VillageSlot slot = (VillageSlot) adapter.getItemAtPosition(position);

                final PopupWindow popupwindow_obj = new PopupWindow(getContext());
                StartpageActivity x = (StartpageActivity) getActivity().getParent();
                final Toast toast = Toast.makeText(getActivity(), "Nicht genug Ressourcen!", Toast.LENGTH_SHORT);


                // inflate your layout or dynamically add view
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.menu, null);
                popupwindow_obj.setContentView(view);

                popupwindow_obj.setFocusable(true); // Popup verschwindet wenn man woanders hintippt.
                popupwindow_obj.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
                popupwindow_obj.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);


                //getting all the references
                //menu
                View menu = popupwindow_obj.getContentView();

                //button upgrade
                View layout_upgrade = menu.findViewById(R.id.layout_upgrade);
                final TextView upgrade_kosten_material = (TextView) menu.findViewById(R.id.upgrade_kosten_material);
                final TextView upgrade_kosten_food = (TextView) menu.findViewById(R.id.upgrade_kosten_food);
                final TextView upgrade_kosten_science = (TextView) menu.findViewById(R.id.upgrade_kosten_science);
                final TextView upgrade_kosten_culture = (TextView) menu.findViewById(R.id.upgrade_kosten_culture);

                //building_1
                View layout_building_1 = menu.findViewById(R.id.layout_building_1);
                TextView building_1_kosten_material = (TextView) menu.findViewById(R.id.building_1_kosten_material);
                TextView building_1_kosten_food = (TextView) menu.findViewById(R.id.building_1_kosten_food);
                TextView building_1_kosten_science = (TextView) menu.findViewById(R.id.building_1_kosten_science);
                TextView building_1_kosten_culture = (TextView) menu.findViewById(R.id.building_1_kosten_culture);

                //building_2
                View layout_building_2 = menu.findViewById(R.id.layout_building_2);
                TextView building_2_kosten_material = (TextView) menu.findViewById(R.id.building_2_kosten_material);
                TextView building_2_kosten_food = (TextView) menu.findViewById(R.id.building_2_kosten_food);
                TextView building_2_kosten_science = (TextView) menu.findViewById(R.id.building_2_kosten_science);
                TextView building_2_kosten_culture = (TextView) menu.findViewById(R.id.building_2_kosten_culture);

                //raze
                View layout_raze = menu.findViewById(R.id.layout_raze);


                //welche Einträge sind sichtbar?
                if (slot.isBuilding()) {
                    //Has Building -> only Upgrade and raze buttons
                    layout_upgrade.setVisibility(View.VISIBLE);
                    layout_building_1.setVisibility(View.GONE);
                    layout_building_2.setVisibility(View.GONE);
                    layout_raze.setVisibility(View.VISIBLE);

                    upgrade_kosten_material.setText(String.valueOf(slot.UpgradeKosten("material")));
                    upgrade_kosten_food.setText(String.valueOf(slot.UpgradeKosten("food")));
                    upgrade_kosten_science.setText(String.valueOf(slot.UpgradeKosten("science")));
                    upgrade_kosten_culture.setText(String.valueOf(slot.UpgradeKosten("culture")));

                } else {
                    //Not a Building -> Button for each buildable building
                    layout_upgrade.setVisibility(View.GONE);

                    layout_building_1.setVisibility(View.VISIBLE);
                    building_1_kosten_material.setText(String.valueOf(getResources().getInteger(R.integer.building_1_material)));
                    building_1_kosten_food.setText(String.valueOf(getResources().getInteger(R.integer.building_1_food)));
                    building_1_kosten_science.setText(String.valueOf(getResources().getInteger(R.integer.building_1_science)));
                    building_1_kosten_culture.setText(String.valueOf(getResources().getInteger(R.integer.building_1_culture)));

                    layout_building_2.setVisibility(View.VISIBLE);
                    building_2_kosten_material.setText(String.valueOf(getResources().getInteger(R.integer.building_2_material)));
                    building_2_kosten_food.setText(String.valueOf(getResources().getInteger(R.integer.building_2_food)));
                    building_2_kosten_science.setText(String.valueOf(getResources().getInteger(R.integer.building_2_science)));
                    building_2_kosten_culture.setText(String.valueOf(getResources().getInteger(R.integer.building_2_culture)));

                    layout_raze.setVisibility(View.GONE);
                }

                //Einträge Klickbar machen
                //Click auf upgrade
                View.OnClickListener upgrade_listener = new View.OnClickListener() {
                    public void onClick(View v) {
                        if (slot.upgrade(slot.getBuilding())) {
                            popupwindow_obj.dismiss();
                            gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
                        } else {
                            toast.show();
                        }

                    }
                };
                layout_upgrade.setOnClickListener(upgrade_listener);


                //Click auf Building_1
                View.OnClickListener building_1_listener = new View.OnClickListener() {
                    public void onClick(View v) {
                        if (slot.build_1()) {
                            popupwindow_obj.dismiss();
                            gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
                        } else toast.show();

                    }
                };
                layout_building_1.setOnClickListener(building_1_listener);

                //Click auf Building_2
                View.OnClickListener building_2_listener = new View.OnClickListener() {
                    public void onClick(View v) {
                        if (slot.build_2()) {
                            popupwindow_obj.dismiss();
                            gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
                        } else toast.show();

                    }
                };
                layout_building_2.setOnClickListener(building_2_listener);

                //Click auf raze
                View.OnClickListener raze_listener = new View.OnClickListener() {
                    public void onClick(View v) {
                        slot.abreissen();
                        popupwindow_obj.dismiss();
                        gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
                    }
                };
                layout_raze.setOnClickListener(raze_listener);


                // where u want show on view click event popupwindow.showAsDropDown(view, x, y);
                popupwindow_obj.showAsDropDown(v);


                /**
                 PopupMenu popup = new PopupMenu(getContext(), v);

                 //Menü-Referenz
                 Menu menu = popup.getMenu();

                 //VillageSlot Referenz = slot
                 //Baumaterial-Referenz
                 //Ressource material = x.getRessource(Ressource.MATERIAL);

                 if (slot.isBuilding()) {
                 menu.add("Upgrade");
                 if (slot.UpgradeKosten("material") != 0) {
                 menu.add("      " + slot.UpgradeKosten("material") + " x " + getResources().getText(R.string.material));
                 }
                 if (slot.UpgradeKosten("food") != 0) {
                 menu.add("      " + slot.UpgradeKosten("food") + " x " + getResources().getText(R.string.food));
                 }
                 if (slot.UpgradeKosten("science") != 0) {
                 menu.add("      " + slot.UpgradeKosten("science") + " x " + getResources().getText(R.string.science));
                 }
                 if (slot.UpgradeKosten("culture") != 0) {
                 menu.add("      " + slot.UpgradeKosten("culture") + " x " + getResources().getText(R.string.culture));
                 }
                 menu.add("Abreissen");
                 } else {
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

                 if (item.getTitle() == "Upgrade") {
                 slot.upgrade(slot.getBuilding());
                 }
                 if (item.getTitle() == "Abreissen") {
                 slot.abreissen();
                 }
                 if (item.getTitle() == getResources().getText(R.string.building_1)) {
                 slot.build_1();
                 }
                 if (item.getTitle() == getResources().getText(R.string.building_2)) {
                 slot.build_2();
                 }
                 if (item.getTitle() == getResources().getText(R.string.building_3)) {
                 slot.build_3();
                 }
                 if (item.getTitle() == getResources().getText(R.string.building_4)) {
                 slot.build_4();
                 }
                 if (item.getTitle() == getResources().getText(R.string.building_5)) {
                 slot.build_5();
                 }
                 gridview.setAdapter(new ArrayAdapter<VillageSlot>(getActivity(), R.layout.textview_for_gridview_village, blueprint2));
                 return true;
                 }
                 });

                 popup.show();

                 TextView textView = (TextView) v;
                 textView.setText(slot.toString());
                 */
            }
        });
    }
}
