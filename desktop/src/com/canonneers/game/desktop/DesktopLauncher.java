package com.canonneers.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.canonneers.game.FlappyPig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyPig.WIDTH;
		config.height = FlappyPig.HEIGHT;
		config.title = FlappyPig.TITLE;
		new LwjglApplication(new FlappyPig(), config);
	}
}
