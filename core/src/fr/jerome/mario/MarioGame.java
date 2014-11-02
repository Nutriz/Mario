package fr.jerome.mario;

import com.badlogic.gdx.Game;

import fr.jerome.mario.screen.GameScreen;
import fr.jerome.mario.screen.IntroScreen;

public class MarioGame extends Game {

    public static final float SOUND_VOLUME = 0.2f;

    public int score = 0;
    public int life = 3;
    public int nbPieces = 0;

	@Override
	public void create () {

        this.setScreen(new IntroScreen(this));
    }

	@Override
	public void render () {
        super.render();
	}

    @Override
    public void dispose() {

        Assets.dispose();
    }


}
