package fr.jerome.mario.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Notre super héro de la saga
 * Created by jerome on 01/10/14.
 */
public class Mario {

    public static final int IDLE = 0;
    public static final int WALK_LEFT = 1;
    public static final int WALK_RIGHT = 2;
    public static final int RUN = 3;
    public static final int JUMP = 4;

    public static final float SPEED = 8f;  // unité par seconde
    static final float JUMP_VELOCITY = 4f;

    // Position x and y
    private Vector2     position = new Vector2();
    private Vector2     velocity = new Vector2();
    private int         state = IDLE;
    private boolean     facingLeft = false;

    public Mario(Vector2 pos) {
        this.position = pos;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setState(int newStat) {
        this.state = newStat;
    }

    public int getState() {
        return state;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }
}
