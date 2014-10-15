package fr.jerome.mario.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Notre super héro de la saga
 * Created by jerome on 01/10/14.
 */
public class Mario {

    public static final int IDLE = 0;
    public static final int WALK = 2;
    public static final int JUMP = 3;
    public static final int DYING = 4;

    public static final int RIGHT = 1;
    public static final int LEFT = -1;

    private int  state = IDLE;
    private int  dir = RIGHT;

    private static final float GRAVITY = -20;
    private static final float JUMP_VEL = 14;
    private static final float WALK_ACCEL = 20;
    private static final float FRICTION = 0.90f;
    private static final float WALK_MAX = 10;

    // Position x and y
    private Vector2 pos = new Vector2();
    private Vector2 vel = new Vector2();
    private Vector2 accel = new Vector2();

    private World world;

    public Mario(Vector2 pos, World w) {
        this.pos = pos;
        this.world = w;
    }

    public void update(float deltaTime) {

        processKeys();
        accel.y = GRAVITY;
        accel.scl(deltaTime);

        vel.add(accel.x, accel.y);

        if (accel.x < 0.01f && dir == RIGHT)
            vel.x *= FRICTION;
        else if (accel.x > -0.01f && dir == LEFT)
            vel.x *= FRICTION;

        if (vel.x > WALK_MAX) {
            vel.x = WALK_MAX;
        }
        else if (vel.x < -WALK_MAX) {
            vel.x = -WALK_MAX;
        }

        // Modification de la position
        pos.mulAdd(vel, deltaTime);

        if (pos.x < 0)
            pos.x = 0;
        else if (pos.x > world.getMapWidth()-1)
            pos.x =  world.getMapWidth()-1;

        if (pos.y < 2 && state != Mario.DYING) {
            pos.y = 2;

            if (vel.x < 1f && dir == RIGHT)
                state = Mario.IDLE;
            else if (vel.x > -1f && dir == LEFT)
                state = Mario.IDLE;

            if (state != Mario.WALK)
                state = Mario.IDLE;
        }
        else if (state == Mario.DYING)
            pos.y = 2;

    }

    private void processKeys () {

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && state != JUMP) {
            state = JUMP;
            vel.y = JUMP_VEL;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (state != JUMP)
                state = WALK;

            dir = RIGHT;
            accel.x = WALK_ACCEL * dir;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (state != JUMP)
                state = WALK;

            dir = LEFT;
            accel.x = WALK_ACCEL * dir;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.K))
            state = Mario.DYING;
    }

    public int getDir() {
        return dir;
    }

    public Vector2 getPos() {
        return pos;
    }

    public int getState() {
        return state;
    }

    public Vector2 getVel() {
        return vel;
    }

    public Vector2 getAccel() {
        return accel;
    }

    public static float getGravity() {
        return GRAVITY;
    }
}
