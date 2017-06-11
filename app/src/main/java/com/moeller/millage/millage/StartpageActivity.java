package com.moeller.millage.millage;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.moeller.millage.millage.research.ArtefactParser;
import com.moeller.millage.millage.research.Artefacts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class StartpageActivity extends AppCompatActivity implements Tickable{

    private HashMap<String, Ressource> ressources = new HashMap<>();
    private Artefacts artefacts;

    private ArtefactParser artefactParser = new ArtefactParser();
    private TickTimer tickTimer = new TickTimer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("StartpageActivity", "Start ONCREATE");

        setContentView(R.layout.startpage);
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // initialize Ressources
        initRessouces();

        // parse Artefacts
        parseArtefacts();

        // Tickables dem Prozess übergeben
        tickTimer.add(this);

        // Starten des ewigen Kreislaufs
        tickTimer.run();
    }

    private void initRessouces(){
        ressources.put(Ressource.MATERIAL, new Ressource(10, 1, (TextView) findViewById(R.id.amount_material), (TextView) findViewById(R.id.income_material)));
        ressources.put(Ressource.FOOD, new Ressource(5, 0, (TextView) findViewById(R.id.amount_food), (TextView) findViewById(R.id.income_food)));
        ressources.put(Ressource.WORKER, new Ressource(0, 0, (TextView) findViewById(R.id.amount_worker), (TextView) findViewById(R.id.income_worker)));
        ressources.put(Ressource.SCIENCE, new Ressource(0, 0, (TextView) findViewById(R.id.amount_science), (TextView) findViewById(R.id.income_science)));
        ressources.put(Ressource.CULTURE, new Ressource(0, 0, (TextView) findViewById(R.id.amount_culture), (TextView) findViewById(R.id.income_culture)));
    }

    private void parseArtefacts(){
        try {
            artefacts = new Artefacts(artefactParser.parse(this.getResources().getXml(R.xml.artefacts)));
        } catch (Exception e){
            Log.d("ArtefactParsing", "error parsing artefacts");
            e.printStackTrace();
        }
    }

    public Map <String, Ressource> getAllRessources() {
        return this.ressources;
    }

    public void tick (){
        Log.i("Startpage_Activity", "Tick");

        //Einkommen auszahlen
        for(Ressource res : ressources.values()){
            res.tick();
        }
    }

    /**
     * returns teh ressource or null if not available
     * @param name
     * @return
     */
    public Ressource getRessource(String name){
        return ressources.get(name) != null?ressources.get(name): null;
    }

    public Artefacts getArtefacts(){
        return this.artefacts;
    }
}



