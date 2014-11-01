package fr.jerome.mario.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.jerome.mario.Assets;

/**
 * Class générique pour les ennemis
 * Created by jerome on 23/10/14.
 */
public class Enemies extends DynamicGameObject {

    public static enum State {
        IDLE, WALK, DYING
    }

    public State state;
    public int dir;

    public Enemies(float x, float y, int width, int height, State state, int dir) {
        super(x, y, width, height);
        this.state = state;
        this.dir = dir;
    }
}
