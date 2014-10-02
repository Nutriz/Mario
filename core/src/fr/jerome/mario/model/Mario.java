package fr.jerome.mario.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Notre super héro de la saga
 * Created by jerome on 01/10/14.
 */
public class Mario {

    public enum State {
        IDLE, WALKING, JUMPING
    }

    static final float SPEED = 2f;  // unité par seconde
    static final float JUMP_VELOCITY = 1f;
    static final float SIZE = 1; // Une unité


    Vector2     position = new Vector2();
    Vector2     acceleration = new Vector2();
    Vector2     velocity = new Vector2();
    Rectangle   bounds = new Rectangle();
    State       state = State.IDLE;
    boolean     facingLeft = true;

    public Mario(Vector2 pos) {
        this.position = pos;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
        this.bounds.x = this.position.x;
        this.bounds.y = this.position.y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
