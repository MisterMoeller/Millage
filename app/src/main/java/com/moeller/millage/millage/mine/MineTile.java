package com.moeller.millage.millage.mine;

/**
 * Created by Philipp on 03.06.2017.
 */

public class MineTile {


    private final double DEPTH_FACTOR = 1.5;
    private final int INITIAL_DURATION = 10;

    private int depth = 1;
    private int duration = INITIAL_DURATION;
    public MineTile(int depth){
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void onClick(){
        this.duration --;
        awardResouces();
        if(this.duration == 0){
            onBreakthrough();
        }
    }

    private void onBreakthrough(){
        this.depth++;
        this.duration = (int) Math.round(INITIAL_DURATION * this.depth * DEPTH_FACTOR);
    }

    private void awardResouces(){

    }

    public String toString(){
        return "D: " + Integer.toString(this.depth) + " [" + Integer.toString(this.duration) + "]";
    }

}
