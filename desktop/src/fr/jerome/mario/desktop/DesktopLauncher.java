package fr.jerome.mario.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.jerome.mario.Mario;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Mario LibGDX";
        config.width = 800;
        config.height = 480;
        config.x = 200;
        config.y = 200;
		new LwjglApplication(new Mario(), config);
	}
}
