package fr.jerome.mario.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fr.jerome.mario.MarioGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "MarioGame LibGDX";
        config.x = 200;
        config.y = 200;
        config.width = 800;
        config.height = 600;
		new LwjglApplication(new MarioGame(), config);
	}
}
