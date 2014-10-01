package fr.jerome.mario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import fr.jerome.mario.MarioGame;

/**
 * Created by jerome on 01/10/14.
 */
public class IntroScreen implements Screen {

    final MarioGame game;
    OrthographicCamera camera;

    Texture splashImage;

    public IntroScreen(final MarioGame gam, OrthographicCamera cam) {

        game = gam;
        camera = cam;
        splashImage = new Texture(Gdx.files.internal("Images/splash.jpg"));
    }

    @Override
    public void render(float delta) {

        game.batch.begin();
        game.batch.draw(splashImage, 0, 0);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenuScreen(game, camera));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
