package com.moeller.millage.millage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.moeller.millage.millage.R;

import java.util.ArrayList;


public class ScienceFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_science, container, false);
        }

    @Override
    public void onStart() {
        super.onStart();
        ArrayList<String> researches = new ArrayList<>();
        researches.add("Forschung 1");
        researches.add("Forschung 2");



        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, researches);
        ListView listView = (ListView) getView().findViewById(R.id.science_listview);
        listView.setAdapter(itemsAdapter);
    }
}
