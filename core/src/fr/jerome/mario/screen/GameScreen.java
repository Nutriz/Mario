package fr.jerome.mario.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import fr.jerome.mario.Assets;
import fr.jerome.mario.MarioGame;
import fr.jerome.mario.model.Mario;
import fr.jerome.mario.model.World;
import fr.jerome.mario.view.WorldRenderer;

/**
 * Ecran de jeu
 * Created by jerome on 01/10/14.
 */
public class GameScreen implements Screen {

    private MarioGame game;
    private World world;
    private WorldRenderer worldRenderer;

    private float deltaTime = 0;

    public GameScreen(MarioGame marioGame) {
        game = marioGame;
    }

    @Override
    public void show() {

        world = new World(game);
        worldRenderer = new WorldRenderer(world);
    }

    @Override
    public void render(float delta) {

        world.update(delta);
        worldRenderer.render();

        if (world.mario.getState() == Mario.State.DYING) {
            deltaTime += delta;

            if (deltaTime > 2)
                game.setScreen(new WaitScreen(game));
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
