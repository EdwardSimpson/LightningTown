package com.platformer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.platformer.game.InputController.InputHandler;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import org.lwjgl.system.CallbackI;

public class WinScreen implements Screen {

    private Stage stage;
    private Texture bk;
    private InputHandler inputHandler;
    private Texture winTex = new Texture(Gdx.files.internal("assets/vicotry.jpg"));
    private Image winPic = new Image(winTex);

    public WinScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(inputHandler);
    }


    @Override
    public void show() {
        winPic.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        winPic.setPosition(Gdx.graphics.getWidth()- winPic.getWidth(),
                Gdx.graphics.getHeight() - winPic.getHeight());
        stage.addActor(winPic);
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
