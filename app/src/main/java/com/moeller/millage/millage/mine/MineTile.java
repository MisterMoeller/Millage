package com.moeller.millage.millage.mine;

/**
 * Created by Philipp on 03.06.2017.
 */

public class MineTile {

    private int depth = 1;
    private int duration = 5;
    private final double DEPTH_FACTOR = 1.5;

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

    }

    public String toString(){
        return Integer.toString(depth);
    }

}
