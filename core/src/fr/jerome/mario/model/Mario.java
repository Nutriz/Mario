package fr.jerome.mario.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.jerome.mario.Assets;

/**
 * Notre super héro de la saga
 * Created by jerome on 01/10/14.
 */
public class Mario extends DynamicGameObject {

    public static enum State {
        IDLE, WALK, JUMP, DYING
    }

    private State state = State.IDLE;
    public int dir = RIGHT;

    private final float JUMP_VEL = 14;
    private final float WALK_ACCEL = 20;
    private final float FRICTION = 0.90f;
    private final float WALK_MAX = 10;

    private World world;

    public Mario(Vector2 pos, World w) {
        super(pos.x, pos.y, 1, 1);
        this.world = w;
    }

    public void update(float deltaTime) {

        // FIXME state WALK n'est jamais appliqué

        processKeys();
        accel.y = World.GRAVITY;
        accel.scl(deltaTime);

        vel.add(accel.x, accel.y);

        // Modification de la position
        move(deltaTime);

        // Limit la vitesse de mario
        if (vel.x > WALK_MAX) {
            vel.x = WALK_MAX;
        }
        else if (vel.x < -WALK_MAX) {
            vel.x = -WALK_MAX;
        }

        // Mario states for animations
        if (pos.y <= 2 && state != State.DYING) {
            pos.y = 2;

            if (vel.x < 1f && dir == RIGHT)
                state = State.IDLE;
            else if (vel.x > -1f && dir == LEFT)
                state = State.IDLE;

            if (state != State.IDLE)
                state = State.IDLE;
        }

        pickPiece();

        Gdx.app.log("state", ""+state);

    }

    private void move(float deltaTime) {

        // Inertie de Mario
        if (accel.x < 0.01f && dir == RIGHT)
            vel.x *= FRICTION;
        else if (accel.x > - 0.01f && dir == LEFT)
            vel.x *= FRICTION;

//        Gdx.app.log("---", "");

//        if (isCollisionY() && vel.y < 0) {
//            vel.y = 0;
//        }
//        else if (isCollisionY() && vel.y > 0) {
//            vel.y = 0;
//        }

        if (isCollisionX())
            vel.x = 0;


//        Gdx.app.log("vel y", ""+vel.y);
//        Gdx.app.log("pos y", ""+pos.y);
        pos.mulAdd(vel, deltaTime);
        rect.setPosition(pos);

        // Limit mario à la map
        if (pos.x < 0)
            pos.x = 0;
        else if (pos.x > world.getMapWidth()-1)
            pos.x =  world.getMapWidth()-1;

    }

    private boolean isCollisionY() {

        boolean collisionY = false;

        collisionY = world.getCollisionsLayer().getCell((int)pos.x, (int)pos.y) != null;

        return collisionY;
    }

    private boolean isCollisionX() {

        boolean collisionX = false;

        collisionX= world.getCollisionsLayer().getCell((int)pos.x + 1, (int)pos.y) != null;
        if (!collisionX)
            collisionX= world.getCollisionsLayer().getCell((int)pos.x, (int)pos.y) != null;

        return collisionX;
    }

    private void pickPiece() {

        int index = 0;
        for (Rectangle p : world.getPieces()) {
            if (rect.overlaps(p)) {
                world.recoltePiece(index);
                Assets.manager.get(Assets.pceSFX, Sound.class).play();
            }
            index++;
        }
    }

    private void processKeys () {

        // Jump
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && state != State.JUMP) {
            state = State.JUMP;
            vel.y = JUMP_VEL;
            Assets.manager.get(Assets.jumpSFX, Sound.class).play();
        }

        // Walk Right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            dir = RIGHT;
            accel.x = WALK_ACCEL * dir;

            if (state != State.JUMP)
                state = State.WALK;
        }

        // Walk Left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            dir = LEFT;
            accel.x = WALK_ACCEL * dir;

            if (state != State.JUMP)
                state = State.WALK;
        }

    }

    public State getState() {
        return state;
    }

    public Vector2 getPos() {
        return pos;
    }

    public Vector2 getVel() {
        return vel;
    }
}
