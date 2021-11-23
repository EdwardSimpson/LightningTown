package com.platformer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class GameScreen implements Screen {
    public static Orchestrator orch;
    public static PerspectiveCamera cam =
            new PerspectiveCamera(90, Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight());

    public GameScreen() {
        cam.position.set(0f,0f,3f);
        cam.lookAt(0f,0f,0f);
        cam.near = 0.1f;
        cam.far = 1000f;
        orch = new Orchestrator(cam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        if (!Orchestrator.wait){
            orch.render();
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
        Assets.dispose();
    }
}
