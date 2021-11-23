package com.platformer.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GUI extends ApplicationAdapter {

    private Stage stage;
    private Array<Image> img = new Array<Image>();

    private float stg;

    public GUI() {
        initilize();
    }

    public void initilize() {
        stage = new Stage(new ScreenViewport());

        float centerW = Gdx.graphics.getWidth()/2f;
        float tmpcw, tmpch;

        System.out.println("Width" + Gdx.graphics.getWidth());
        System.out.println("Height" + Gdx.graphics.getHeight());

        //action slot frame
        img.add(new Image(SPR.HUD_ACTIONBAR.tex()));
        img.get(0).setSize(img.get(0).getWidth(), img.get(0).getHeight()+1);
        tmpcw = img.get(0).getWidth()/2;
        tmpch = img.get(0).getHeight()/2;
        img.get(0).setPosition(800-tmpcw,100-tmpch);
        stage.addActor(img.get(0));

        //action slots
        img.add(new Image(SPR.HUD_ACTIONBAR_SLOT.tex()));
        img.add(new Image(SPR.HUD_ACTIONBAR_SLOT.tex()));
        img.add(new Image(SPR.HUD_ACTIONBAR_SLOT.tex()));
        img.add(new Image(SPR.HUD_ACTIONBAR_SLOT.tex()));
        img.add(new Image(SPR.HUD_ACTIONBAR_SLOT.tex()));
        img.add(new Image(SPR.HUD_ACTIONBAR_SLOT.tex()));

        tmpch = img.get(1).getHeight()/2;

        for(int i = 0; i < 6; i++) {
            img.get(i+1).setSize(img.get(i+1).getWidth() + 1, img.get(i+1).getHeight()+1);
            img.get(i+1).setPosition(519 + i*71 + i*27,98-tmpch);
            stage.addActor(img.get(i+1));
        }

        //health bar
        img.add(new Image(SPR.HUD_HEALTHBAR_EMPTY.tex()));
        img.add(new Image(SPR.HUD_HEALTHBAR_FULL.tex()));
        img.get(7).setSize(img.get(7).getWidth()/2,img.get(7).getHeight()/2);
        img.get(8).setSize(img.get(8).getWidth()/2,img.get(8).getHeight()/2);
        tmpch = img.get(7).getHeight()/2;
        tmpcw = img.get(7).getWidth()/2;

        img.get(7).setPosition(centerW - tmpcw,175-tmpch);
        img.get(8).setPosition(centerW - tmpcw,175-tmpch);
        img.get(7).setSize(img.get(7).getWidth()+5,img.get(7).getHeight()+5);
        img.get(8).setSize(img.get(8).getWidth()+5,img.get(8).getHeight()+5);
        stg = img.get(8).getWidth()+5;

        stage.addActor(img.get(7));
        stage.addActor(img.get(8));

    }

    public void render(float delta){
        img.get(8).setSize(stg * ((float)Orchestrator.hp/100f),img.get(8).getHeight());
        stage.act();
        stage.draw();
    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
    }
}
