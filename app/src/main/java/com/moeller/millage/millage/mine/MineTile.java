package com.moeller.millage.millage.mine;

import android.app.Activity;
import android.widget.Toast;

import com.moeller.millage.millage.R;
import com.moeller.millage.millage.StartpageActivity;
import com.moeller.millage.millage.research.ArtefactParser;

/**
 * represents a single clickable tile in the mine
 */
public class MineTile {


    // factor used to calculate rewards for clicking and new durations on breakthrough
    private double DEPTH_FACTOR;
    // initial digsLeft and base of future digsLeft calculation
    private int INITIAL_DURATION;

    // "level" of the tile
    private int depth = 1;
    // amount of clicks necessary to breakthrough
    private int digsLeft;
    // reference to the main Activity
    private StartpageActivity activity;

    /**
     * constructor
     * @param activity
     */
    public MineTile(Activity activity){
        this.activity = (StartpageActivity) activity;
        this.DEPTH_FACTOR = Double.valueOf(this.activity.getResources().getString(R.string.mine_depth_factor));
        this.INITIAL_DURATION = Integer.valueOf(this.activity.getResources().getInteger(R.integer.mine_initial_duration));
        this.digsLeft = this.INITIAL_DURATION;
    }

    /**
     * Getter Depth
     * @return
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Setter Depth
     * @return
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * onClick function
     */
    public void onClick(){
        this.digsLeft--;
        awardResouces();
        if(this.digsLeft == 0){
            onBreakthrough();
        }
    }

    /**
     * used on Breakthrough / Levelup
     */
    private void onBreakthrough(){
        this.depth++;
        this.digsLeft = (int) Math.round(INITIAL_DURATION * this.depth * DEPTH_FACTOR);
        if(doesReceiveArtefact()){
            Toast toast = Toast.makeText(activity.getApplicationContext(), "", Toast.LENGTH_LONG);
            ArtefactParser.Artefact artefact = activity.getArtefacts().getRandomAvailableArtefact();
            if(artefact == null){
                toast.setText("there are no available artefats currently");
            } else {
                toast.setText("you have received a " + artefact.getTitle());
                activity.getArtefacts().addArtefact(artefact);
            }
            toast.show();
        }

    }

    /**
     * used in onclick and awards resources based on the depth of the tile
     */
    private void awardResouces(){
        activity.getRessource("material").changeRessources((int) Math.round(depth * DEPTH_FACTOR));
    }

    private boolean doesReceiveArtefact(){
//        return (Math.random() * 100) < (this.depth * DEPTH_FACTOR);
        return true;
    }

    /**
     * String output for the TextView
     * @return
     */
    public String toString(){
        return "D: " + Integer.toString(this.depth) + " [" + Integer.toString(this.digsLeft) + "]";
    }

}
