package fr.jerome.mario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.jerome.mario.screens.IntroScreen;
import fr.jerome.mario.screens.MainMenuScreen;

public class Mario extends Game {

    public OrthographicCamera camera;
    public SpriteBatch batch;
    public BitmapFont font;

	@Override
	public void create () {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
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
