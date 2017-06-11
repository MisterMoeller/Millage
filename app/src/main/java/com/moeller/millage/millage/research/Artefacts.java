package com.moeller.millage.millage.research;

import java.util.LinkedList;
import java.util.List;


/**
 * manages all Artefacts
 */
public class Artefacts {
    private List<ArtefactParser.Artefact> allArtefacts;
    private List<ArtefactParser.Artefact> availableArtefacts;
    private List<ArtefactParser.Artefact> ownedArtefacts;

    public Artefacts(List<ArtefactParser.Artefact> allArtefacts){
        this.allArtefacts = allArtefacts;
        availableArtefacts = new LinkedList<>();
        ownedArtefacts = new LinkedList<>();
        checkForNewAvailableArtefacts();
    }

    public void addArtefact(ArtefactParser.Artefact artefact){
        ownedArtefacts.add(artefact);
        availableArtefacts.remove(artefact);
        checkForNewAvailableArtefacts();
    }

    private void checkForNewAvailableArtefacts(){
        for(ArtefactParser.Artefact allA : allArtefacts){
            if(ownedArtefacts.contains(allA)){
                continue;
            }
            if(allA.getPreConditions().isEmpty()){
                availableArtefacts.add(allA);
            }
            boolean conditionsMet = true;
            for(String condition : allA.getPreConditions()){
                if(!preConditionMet(condition)){
                    conditionsMet = false;
                }
            }
            if(conditionsMet){
                availableArtefacts.add(allA);
            }
        }
    }

    private boolean preConditionMet(String condition){
        for(ArtefactParser.Artefact ownedA : ownedArtefacts){
            if(ownedA.getTitle().equals(condition)){
                return true;
            }
        }
        return false;
    }

    public ArtefactParser.Artefact getRandomAvailableArtefact(){
        if(availableArtefacts.isEmpty()){
            return null;
        }
        return availableArtefacts.get((int) Math.random() * (availableArtefacts.size() - 1));
    }


}
