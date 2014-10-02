package fr.jerome.mario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.jerome.mario.screen.GameScreen;

public class MarioGame extends Game {

	@Override
	public void create () {

        this.setScreen(new GameScreen());
    }

	@Override
	public void render () {
        super.render();
	}

    @Override
    public void dispose() {

    }
}
