package fr.jerome.mario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.jerome.mario.models.Block;
import fr.jerome.mario.MarioGame;
import fr.jerome.mario.models.Mario;
import fr.jerome.mario.models.World;

/**
 * Ecran de jeu
 * Created by jerome on 01/10/14.
 */
public class GameScreen implements Screen {

    final MarioGame game;
    private Mario mario;
    private World world;
    private OrthographicCamera camera;

    public GameScreen(final MarioGame gam, OrthographicCamera cam) {

        game = gam;
        world = new World();
        mario = new Mario(new Vector2(3, 2));
        camera = cam;

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ShapeRenderer debugRenderer = new ShapeRenderer();
        debugRenderer.setColor(new Color(0, 1, 1, 1));
        debugRenderer.setProjectionMatrix(camera.combined);

        debugRenderer.begin(ShapeRenderer.ShapeType.Line);

            for (Block b : world.getBlocks()) {
                Rectangle rect = b.getBounds();
                float x1 = b.getPosition().x;
                float y1 = b.getPosition().y;
                debugRenderer.rect(x1, y1, rect.width, rect.height);
            }

        debugRenderer.setColor(new Color(1, 0, 1, 1));
        Rectangle rect = mario.getBounds();
        float x1 = mario.getPosition().x;
        float y1 = mario.getPosition().y;
        debugRenderer.rect(x1, y1, rect.width, rect.height);

        debugRenderer.end();

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
