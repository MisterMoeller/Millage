package com.moeller.millage.millage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.moeller.millage.millage.mine.MineTile;


public class MineFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart", "ONSTART");
        //Initialer Aufbau des Spielfelds
        MineTile[] blueprint = {new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity()), new MineTile(getActivity())};
        Buildmine(blueprint);

    }



    public void Buildmine(MineTile[] blueprint) {
        /*
           Wird mit einem Plan für das neue Spielfeld aufgerufen (ein Stringarray)
           und aktualisiert die Anzeige auf dem Bildschirm
        */
        GridView gridview = (GridView) getView().findViewById(R.id.grid_layout_mine);
        gridview.setAdapter(new ArrayAdapter<MineTile>(getActivity(), R.layout.textview_for_gridview_mine, blueprint));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3){
                MineTile tile =  (MineTile) adapter.getItemAtPosition(position);
                tile.onClick();
                TextView textView = (TextView) v;
                textView.setText(tile.toString());
            }
        });

    }

}
