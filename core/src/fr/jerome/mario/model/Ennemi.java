package fr.jerome.mario.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by jerome on 23/10/14.
 */
public class Ennemi extends DynamicGameObject {

    private int speed;

    private Animation walkAnimation;

    public Ennemi(float x, float y, float width, float height) {

        super(x, y, width, height);
    }
}
