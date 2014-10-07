package fr.jerome.mario.model;

import com.badlogic.gdx.Gdx;
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

    public static final float SPEED = 2f;  // unité par seconde
    static final float JUMP_VELOCITY = 1f;
    static final float SIZE = 1; // Une unité

    // Position x and y
    Vector2     position = new Vector2();
    Vector2     jumpAcceleration = new Vector2();
    Vector2     velocity = new Vector2();
    Rectangle   rect = new Rectangle();
    State       state = State.IDLE;
    boolean     facingLeft = true;

    public Mario(Vector2 pos) {
        this.position = pos;
        this.rect.width = SIZE;
        this.rect.height = SIZE;
        this.rect.x = this.position.x;
        this.rect.y = this.position.y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setState(State newStat) {
        this.state = newStat;
    }

    public State getState() {
        return state;
    }
}
