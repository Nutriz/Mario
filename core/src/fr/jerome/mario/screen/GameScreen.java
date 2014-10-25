package fr.jerome.mario.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import fr.jerome.mario.Assets;
import fr.jerome.mario.model.World;
import fr.jerome.mario.view.WorldRenderer;

/**
 * Ecran de jeu
 * Created by jerome on 01/10/14.
 */
public class GameScreen implements Screen {

    private World world;
    private WorldRenderer worldRenderer;

    public int score = 0;
    public int life = 3;
    public int nbPieces = 0;


    @Override
    public void show() {

        Assets.load();
        Assets.manager.finishLoading();
        world = new World(this);
        worldRenderer = new WorldRenderer(world);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.update(delta);
        worldRenderer.render();
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

        Assets.dispose();

    }
}
