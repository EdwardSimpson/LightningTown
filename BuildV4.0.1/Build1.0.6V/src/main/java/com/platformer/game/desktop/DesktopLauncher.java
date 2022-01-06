package com.platformer.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.platformer.game.GameMenu;




public class DesktopLauncher {
	public static final int fps = 100;
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setIdleFPS(fps);
//		config.setForegroundFPS(fps); --This sets a hard FPS Limit BEWARE, GOING UNDER 60FPS WILL MAKE THE GAME SLOW-MO
		config.setTitle("Lightning Bolt Town");
		config.setWindowIcon(Files.FileType.Internal, "assets/flash.png");
		config.setWindowedMode(1600, 900);
		new Lwjgl3Application(new GameMenu(), config);
	}
}
