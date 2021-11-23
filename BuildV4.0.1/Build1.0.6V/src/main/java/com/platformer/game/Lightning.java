package com.platformer.game;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

public class Lightning {
    private float d;
    private int counter, time;

    public Lightning(){
        time = 0;
        counter = 0;
    }

    public int getTime(){
        return (10 - time);
    }

    public void step(float deltaTime) {
        d += deltaTime;
        if(d > 1f) {
            d = 0;
            time++;
        }
    }

    public void strike(Environment e, PerspectiveCamera cam) {
        //lighting things
        float x = cam.position.x;
        float y = cam.position.y;
        time = 0;

    }
}