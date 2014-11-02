package fr.jerome.mario.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.jerome.mario.MarioGame;

/**
 * Created by jerome on 01/11/14.
 */
public class WaitScreen implements Screen {

    private  MarioGame game;
    private SpriteBatch batch;
    private float time = 0;


    public WaitScreen(MarioGame mg) {

        game = mg;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        time += delta;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (time > 3) {
            game.setScreen(new GameScreen(game));
        }

    }

    @Override
    public void resize(int width, int height) {

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
