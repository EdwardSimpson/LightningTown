package com.platformer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.platformer.game.InputController.InputHandler;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class DeathScreen implements Screen {

    private Stage stage;
    private Texture bground = new Texture(Gdx.files.internal("assets/Death.gif"));
    private Image DeathPic = new Image(bground);
    private InputHandler inputHandler;


    public DeathScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void show() {
        DeathPic.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        DeathPic.setPosition(Gdx.graphics.getWidth() - DeathPic.getWidth(),
                Gdx.graphics.getHeight() - DeathPic.getHeight());
        stage.addActor(DeathPic);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
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

    }
}
