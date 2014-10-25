package fr.jerome.mario;

import com.badlogic.gdx.Game;

import fr.jerome.mario.screen.GameScreen;

public class MarioGame extends Game {

    public static final float SOUND_VOLUME = 0.2f;

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
