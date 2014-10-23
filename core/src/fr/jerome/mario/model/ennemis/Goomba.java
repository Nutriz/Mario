package fr.jerome.mario.model.ennemis;

import com.badlogic.gdx.math.Vector2;

import fr.jerome.mario.model.DynamicGameObject;
import fr.jerome.mario.model.Ennemi;

/**
 * Ennemi de base dans mario
 * Created by jerome on 15/10/14.
 */
public class Goomba extends DynamicGameObject {

    public Goomba(float x, float y) {
        super(x, y, 1, 1);
    }
}
