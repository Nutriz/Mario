package fr.jerome.mario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.jerome.mario.screens.IntroScreen;

public class MarioGame extends Game {

    private static final float CAMERA_WIDTH = 20f;
    private static final float CAMERA_HEIGHT = 15f;

    public OrthographicCamera camera;
    public SpriteBatch batch;
    public BitmapFont font;

	@Override
	public void create () {

        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
//        camera.setToOrtho(false, 800, 480);
        camera.position.set(10, 7.5f, 0);
        camera.update();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        //Use LibGDX's default Arial font.
        font = new BitmapFont();

        this.setScreen(new IntroScreen(this, camera));
    }

	@Override
	public void render () {
        super.render();
	}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
