package com.platformer.game;

import com.badlogic.gdx.Game;

public class GameMenu extends Game {
    @Override
    public void create() {
        this.setScreen(new MainMenu(this));
    }
}
