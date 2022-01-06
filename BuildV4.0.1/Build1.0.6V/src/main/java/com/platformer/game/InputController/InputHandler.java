package com.platformer.game.InputController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {
    public boolean forward;
    public boolean backwards;
    public boolean left;
    public boolean right;
    public boolean swing;
    public boolean space;
    public boolean sprinting;
    @Override
    public boolean keyDown(int keycode) {


       if(Gdx.input.isKeyJustPressed(Input.Keys.F))
            swing = true;

        if(keycode == Input.Keys.W)
            forward = true;

        if(keycode == Input.Keys.S)
            backwards = true;

        if(keycode == Input.Keys.A)
            left = true;

        if(keycode == Input.Keys.D)
            right = true;

        if(keycode == Input.Keys.SPACE)
            space = true;

        if(keycode == Input.Keys.SHIFT_LEFT){
            sprinting = true;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        if(keycode == Input.Keys.F)
            swing = false;

        if(keycode == Input.Keys.W)
            forward = false;

        if(keycode == Input.Keys.S)
            backwards = false;

        if(keycode == Input.Keys.A)
            left = false;

        if(keycode == Input.Keys.SPACE){
            space = false;
        }

        if(keycode == Input.Keys.D)
            right = false;

        if(keycode == Input.Keys.SHIFT_LEFT){
            sprinting = false;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
