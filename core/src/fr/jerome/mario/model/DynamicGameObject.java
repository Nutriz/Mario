package fr.jerome.mario.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Définit les propriétés d'un object qui peu se déplacer
 * Created by jerome on 23/10/14.
 */
public class DynamicGameObject {

    // Position
    public Vector2 pos;
    // Vitesse
    public Vector2 vel;
    // Accelleration
    public Vector2 accel;
    // Pour la gestion des collisions
    public Rectangle rect;

    // Sens sur l'axe des X et Y
    public static final int RIGHT = +1;
    public static final int LEFT  = -1;
    public static final int UP    = +1;
    public static final int DOWN  = -1;

    private int width;
    private int height;

    public DynamicGameObject(float x, float y, int width, int height) {

        this.width = width;
        this.height = height;

        this.pos = new Vector2(x, y);
        this.vel = new Vector2();
        this.accel = new Vector2();
        this.rect = new Rectangle(x, y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
