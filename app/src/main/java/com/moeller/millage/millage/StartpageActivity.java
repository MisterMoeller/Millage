package com.moeller.millage.millage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

/*
        Die Dorf-matrix wird aus einem Stringarray aufgebaut. Die Idee ist, nach einer Änderung am Dorf
        den betreffenden String im Array zu ändern und dann das Spielfeld aus dem veränderten Array
        neu zu erschaffen. Schade ist, dass man wohl nur eindimensionale Arrays nehmen kann.
        Cooler Effekt: Wir können das Spielfeld, z.b. nach einer Forschung, größer machen

        Die Ressourcen sind in einer eigenen Klasse ressources zusammengefasst. Darin steht Menge
        und Einkommen und kann nach einer Änderung verändert werden

        Für den Spielablauf habe ich die Methode cycle entworfen. Sie soll eine endlosschleife sein
        die kontinuierlich alle x Sekunden die Ressourcen um das Einkommen erhöht. User-Eingaben
        unterbrechen die Schleife jeweils kurz. (das ist noch nicht implementiert :D)

        Die verschiedenen Screens können wir mit Fragments machen. Das bedeutet sie sind alle in der
        gleichen Activity und wir müssen da keine Daten hin-und-herschieben.
*/
public class StartpageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);

        //Initialer Aufbau des Spielfelds
        String[] blueprint = {"Farm", "Schule", "Universität", "4", "5", "6", "7", "", "9", "10", "11", "12", "13", "Schule", "Universität", "4", "5", "6", "7", "", "9", "10", "11", "12", "13", "Schule", "Universität", "4", "5", "6", "7", "", "9", "10", "11", "12", "13", "Schule", "Universität", "4", "5", "6", "7", "", "9", "10", "11", "12", "13"};
        BuildVillage(blueprint);

        //Starten des ewigen Kreislaufs
        ressources res = new ressources();
        Cycle(res);
    }


    private void Cycle(ressources res) {
        //Einkommen auszahlen
        res.Income();

        //Amount-Werte aktualisieren
        TextView amount_material = (TextView) findViewById(R.id.amount_material);
        amount_material.setText(String.valueOf(res.material));

        TextView amount_food = (TextView) findViewById(R.id.amount_food);
        amount_food.setText(String.valueOf(res.food));

        TextView amount_worker = (TextView) findViewById(R.id.amount_worker);
        amount_worker.setText(String.valueOf(res.worker));

        TextView amount_science = (TextView) findViewById(R.id.amount_science);
        amount_science.setText(String.valueOf(res.science));

        TextView amount_culture = (TextView) findViewById(R.id.amount_culture);
        amount_culture.setText(String.valueOf(res.culture));

        //Income-Werte aktualisieren
        TextView income_material = (TextView) findViewById(R.id.income_material);
        income_material.setText(String.valueOf(res.income_material));

        TextView income_food = (TextView) findViewById(R.id.income_food);
        income_food.setText(String.valueOf(res.income_food));

        TextView income_worker = (TextView) findViewById(R.id.income_worker);
        income_worker.setText(String.valueOf(res.income_worker));

        TextView income_science = (TextView) findViewById(R.id.income_science);
        income_science.setText(String.valueOf(res.income_science));

        TextView income_culture = (TextView) findViewById(R.id.income_culture);
        income_culture.setText(String.valueOf(res.income_culture));


    }

    private void BuildVillage(String[] blueprint) {
        /*
           Wird mit einem Plan für das neue Spielfeld aufgerufen (ein Stringarray)
           und aktualisiert die Anzeige auf dem Bildschirm
	*/
        GridView gridview = (GridView) findViewById(R.id.grid_layout);
        gridview.setAdapter(new ArrayAdapter<String>(this, R.layout.textview_for_gridview, blueprint));

    }


}

