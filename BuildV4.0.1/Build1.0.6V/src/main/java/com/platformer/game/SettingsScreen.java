package com.platformer.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen implements Screen {
    private final Stage stage;
    private final Game g;
    private final MainMenu menu;
    private final Texture texture = new Texture(Gdx.files.internal("assets/craftacular/bkimg.jpg"));
    private Image bk = new Image(texture);
    private Orchestrator orch;
    public Skin skin = new Skin(Gdx.files.internal("assets/craftacular/craftacular/skin/craftacular-ui.json"));
    public Slider adjustVolume = new Slider(0f, 1f, 0.1f, false, skin);

    public SettingsScreen(Game g) {
        this.g = g;
        menu = new MainMenu(g);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        adjustVolume.setValue(0.5f);
    }

    @Override
    public void show() {
        bk.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        bk.setPosition(Gdx.graphics.getWidth() - bk.getWidth(),
                Gdx.graphics.getHeight() - bk.getHeight());
        stage.addActor(bk);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

//        Skin skin = new Skin(Gdx.files.internal("skin/craftacular/skin/craftacular-ui.json"));

        TextButton volumeButton = new TextButton("Volume", skin);
        TextButton backButton = new TextButton("Back", skin);

//        Slider adjustVolume = new Slider(0f, 1f, 0.1f, false, skin);

        table.add(adjustVolume);
        table.row().pad(10,0,10,0);
//        table.add(volumeButton);
//        table.row().pad(10,0,10,0);
        table.add(backButton);
        table.row().pad(5,0,10,0);

        volumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                orch.initSounds(adjustVolume.getValue());
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                g.setScreen(new MainMenu(g));
            }
        });

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        g.dispose();
    }
}