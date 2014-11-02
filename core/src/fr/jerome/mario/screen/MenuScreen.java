package fr.jerome.mario.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.jerome.mario.MarioGame;

/**
 * Created by jerome on 02/11/14.
 */
public class MenuScreen implements Screen {

    private MarioGame game;
    private Texture menu;
    private Texture cursor;
    private SpriteBatch batch;

    private boolean credit;

    public MenuScreen(MarioGame marioGame) {

        game = marioGame;
    }

    @Override
    public void show() {

        menu = new Texture(Gdx.files.internal("images/splashMenu.png"));
        cursor = new Texture(Gdx.files.internal("images/menuSelector.png"));

        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, 514, 448);
    }

    @Override
    public void render(float delta) {

        int cursorY = 162;

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.justTouched())
            credit = true;

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.justTouched()) {
            credit = false;
        }

        if (credit)
            cursorY = 130;

        batch.begin();
            batch.draw(menu, 0, 0);
            batch.draw(cursor, 150, cursorY);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && !credit) {
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
