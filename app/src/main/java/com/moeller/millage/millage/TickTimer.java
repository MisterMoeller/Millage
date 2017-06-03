package com.moeller.millage.millage;

import android.os.Handler;

import java.util.LinkedList;

/**
 * Created by Philipp on 03.06.2017.
 */

public class TickTimer implements Runnable{

    private final int TICK_DURATION = 1000;

    private Handler handler = new Handler();

    private LinkedList<Tickable> tickables = new LinkedList<>();

    protected void add(Tickable item){
        tickables.add(item);
    }

    protected void remove(Tickable item){
        tickables.remove(item);
    }

    public void run(){
        handler.postDelayed(this, TICK_DURATION);
        for (Tickable tickable : tickables){
            tickable.tick();
        }
    }


}
