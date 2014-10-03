package fr.jerome.mario.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import fr.jerome.mario.MarioGame;
import fr.jerome.mario.model.Mario;
import fr.jerome.mario.model.World;
import fr.jerome.mario.view.WorldRenderer;
import sun.rmi.runtime.Log;

/**
 * Ecran de jeu
 * Created by jerome on 01/10/14.
 */
public class GameScreen implements Screen {

    private Mario mario;
    private World world;
    private WorldRenderer worldRenderer;

    private int width;
    private int height;

    @Override
    public void show() {

        mario = new Mario(new Vector2(3, 2));
        world = new World();
        worldRenderer = new WorldRenderer(world, mario);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldRenderer.render();

    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.setSize(width, height);
        this.width = width;
        this.height = height;
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
