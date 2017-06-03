package com.moeller.millage.millage.mine;

import android.app.Activity;

import com.moeller.millage.millage.StartpageActivity;

/**
 * represents a single clickable tile in the mine
 */
public class MineTile {


    // factor used to calculate rewards for clicking and new durations on breakthrough
    private final double DEPTH_FACTOR = 1.5;
    // initial duration and base of future duration calculation
    private final int INITIAL_DURATION = 10;

    // "level" of the tile
    private int depth = 1;
    // amount of clicks necessary to breakthrough
    private int duration = INITIAL_DURATION;
    // reference to the main Activity
    private StartpageActivity activity;

    /**
     * constructor
     * @param activity
     */
    public MineTile(Activity activity){
        this.activity = (StartpageActivity) activity;
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
        this.duration --;
        awardResouces();
        if(this.duration == 0){
            onBreakthrough();
        }
    }

    /**
     * used on Breakthrough / Levelup
     */
    private void onBreakthrough(){
        this.depth++;
        this.duration = (int) Math.round(INITIAL_DURATION * this.depth * DEPTH_FACTOR);
    }

    /**
     * used in onclick and awards resources based on the depth of the tile
     */
    private void awardResouces(){
        activity.getRessource("material").addRessources((int) Math.round(depth * DEPTH_FACTOR));
    }

    /**
     * String output for the TextView
     * @return
     */
    public String toString(){
        return "D: " + Integer.toString(this.depth) + " [" + Integer.toString(this.duration) + "]";
    }

}
