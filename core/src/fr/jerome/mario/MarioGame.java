package fr.jerome.mario;

import com.badlogic.gdx.Game;

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
