package fr.jerome.mario.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fr.jerome.mario.MarioGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "MarioGame LibGDX";
        config.width = 400;
        config.height = 300;
        config.x = 200;
        config.y = 200;
		new LwjglApplication(new MarioGame(), config);
	}
}
