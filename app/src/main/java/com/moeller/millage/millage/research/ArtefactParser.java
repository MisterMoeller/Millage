package com.moeller.millage.millage.research;

import android.util.Log;
import android.util.Xml;

import com.moeller.millage.millage.Ressource;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * parses artefacts from xml
 */
public class ArtefactParser {
    private static String ARTEFACT = "artefact";
    private static String ARTEFACTS = "artefacts";
    private static String NAMESPACE = null;

    public static class Artefact {

        private String title;
        private List<String> preConditions;
        private Map<String, Integer> costs;
        private String effect;

        private Artefact(String title, List<String> preConditions, Map<String, Integer> costs, String effect){
            this.title = title;
            this.preConditions = preConditions;
            this. costs = costs;
            this.effect = effect;
        }

        public String getTitle() {
            return title;
        }

        public List<String> getPreConditions() {
            return preConditions;
        }

        public Map<String, Integer> getCosts() {
            return costs;
        }

        public String getEffect() {
            return effect;
        }
    }


    public List parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.next();
        parser.next();
        return readArtefacts(parser);
    }

    private List readArtefacts(XmlPullParser parser) throws XmlPullParserException, IOException {
        List artefacts = new ArrayList();

        parser.require(XmlPullParser.START_TAG, NAMESPACE, ARTEFACTS);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals(ARTEFACT)) {
                artefacts.add(readArtefact(parser));
                Log.d("ArtefactParser", "artefact added");
            } else {
                skip(parser);
            }
        }
        return artefacts;
    }

    private Artefact readArtefact(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, NAMESPACE, ARTEFACT);

        String title = null;
        List<String> preConditions = null;
        Map<String, Integer> costs = null;
        String effect = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            switch(parser.getName()){
                case "title":
                    title = readString(parser, title);
                    break;
                case "preConditions":
                    preConditions = readPreConditions(parser);
                    break;
                case "costs":
                    costs = readCosts(parser);
                    break;
                case "effect":
                    effect = readString(parser, "effect");
                    break;
                default:
                    skip(parser);
            }
        }
        return new Artefact(title, preConditions, costs, effect);
    }

    private Map<String, Integer>readCosts(XmlPullParser parser) throws IOException, XmlPullParserException{
        HashMap<String, Integer> map = new HashMap<>();
        parser.require(XmlPullParser.START_TAG, NAMESPACE, "costs");
        while (parser.next() != XmlPullParser.END_TAG){
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if(name.equals(Ressource.CULTURE)){
                map.put(Ressource.CULTURE, readInteger(parser));
            } else if(name.equals(Ressource.FOOD)) {
                map.put(Ressource.FOOD, readInteger(parser));
            } else if(name.equals(Ressource.SCIENCE)) {
                map.put(Ressource.SCIENCE, readInteger(parser));
            } else if(name.equals(Ressource.MATERIAL)) {
                map.put(Ressource.MATERIAL, readInteger(parser));
            } else if(name.equals(Ressource.WORKER)) {
                map.put(Ressource.WORKER, readInteger(parser));
            } else {
                skip(parser);
            }
        }
        return map;
    }

    private List readPreConditions(XmlPullParser parser) throws IOException, XmlPullParserException{
        List<String> list = new LinkedList<>();
        parser.require(XmlPullParser.START_TAG, NAMESPACE, "preConditions");
        while(parser.next() != XmlPullParser.END_TAG){
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("preCondition")){
                list.add(readString(parser, "preCondition"));
            } else {
                skip(parser);
            }
        }
        return list;
    }

    private String readString(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, NAMESPACE, tag);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, NAMESPACE, tag);
        return title;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private int readInteger(XmlPullParser parser) throws IOException, XmlPullParserException {
        int result = 0;
        if (parser.next() == XmlPullParser.TEXT) {
            result = Integer.valueOf(parser.getText());
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
