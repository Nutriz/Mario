package fr.jerome.mario.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.jerome.mario.Assets;
import fr.jerome.mario.MarioGame;
import fr.jerome.mario.screen.GameScreen;

/**
 * Notre super héro de la saga
 * Created by jerome on 01/10/14.
 */
public class Mario extends DynamicGameObject {

    public static enum State {
        IDLE, WALK, JUMP, DYING
    }

    private final float JUMP_VEL = 10;
    private final float WALK_ACCEL = 20;
    private final float FRICTION = 0.92f;
    private final float WALK_MAX = 10;

    private State state = State.IDLE;
    private boolean bigMario = false;
    private boolean grounded = false;
    private int dir = RIGHT;

    private World world;

    public Mario(Vector2 pos, World w) {
        super(pos.x, pos.y, 1, 1);
        this.world = w;
    }

    private void processKeys () {

        // si aucune touche appuyée, accel.x = 0
        if (!Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            accel.x = 0;

        // Jump
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && grounded) {
            state = State.JUMP;
            grounded = false;
            vel.y = JUMP_VEL;
            Assets.manager.get(Assets.jumpSFX, Sound.class).play(MarioGame.SOUND_VOLUME);
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

    private void move(float deltaTime) {

        float oldX = pos.x;
        float oldY = pos.y;

//        Gdx.app.log("----", "");
//        Gdx.app.log("old X", ""+oldX);
//        Gdx.app.log("old Y", ""+oldY);

        // Modification de la position
        pos.mulAdd(vel, deltaTime);
        rect.setPosition(pos);

//        Gdx.app.log("new pos tmp", pos.toString());

        // Check collisions X
        if (collisionX()) {
            pos.x = oldX;
            vel.x = 0;
        }

        // Check collisions Y
        if (collisionY()) {
            vel.y = 0;
            pos.y = (int)oldY;
//            Gdx.app.log("coll", "ground");
        }

        // Inertie de Mario
        if (accel.x == 0)
            vel.x *= FRICTION;

//        Gdx.app.log("new pos", pos.toString());

        // Limit mario à la map
        if (pos.x < 0)
            pos.x = 0;
        else if (pos.x > world.getMapWidth()-1)
            pos.x =  world.getMapWidth()-1;

    }


    public void update(float deltaTime) {

        if (state == State.DYING) {
            dying(deltaTime);
        }
        else
            processKeys();

        accel.y = World.GRAVITY;
        accel.scl(deltaTime);

        // Incrémente la vitesse
        vel.add(accel);

        // Limit la vitesse de mario
        if (Math.abs(vel.x) > WALK_MAX)
            vel.x = Math.signum(vel.x) * WALK_MAX;

        // Mario states for animations
        if (pos.y < 0 && state != State.DYING) {
            state = State.DYING;
            Assets.manager.get(Assets.dieSFX, Sound.class).play(MarioGame.SOUND_VOLUME);
            vel.y = JUMP_VEL*1.4f;
        }
        else if (grounded && state != State.DYING) {

            if (vel.x < 1f && dir == RIGHT)
                state = State.IDLE;
            else if (vel.x > -1f && dir == LEFT)
                state = State.IDLE;
            else if (state == State.JUMP)
                state = State.IDLE;
        }

        // Modification de la position
//        if (state != State.DYING)
            move(deltaTime);
//        else
//            dying(deltaTime);

        pickPiece();
    }

    private boolean collisionY() {

        boolean collY;
        if (vel.y < 0) {

            collY = world.getCollisionLayer().getCell((int) pos.x, (int) pos.y) != null;
            if (!collY)
                collY = world.getCollisionLayer().getCell((int) pos.x + 1, (int) pos.y) != null;

            if (collY)
                grounded = true;
        }
        else {
            collY = world.getCollisionLayer().getCell((int) pos.x, (int) pos.y + 1) != null;
            if (!collY) {
                collY = world.getCollisionLayer().getCell((int) pos.x + 1, (int) pos.y + 1) != null;
            }
        }
        return collY;
    }

    private boolean collisionX() {

        boolean collX;
        if (vel.x > 0) {
            collX = world.getCollisionLayer().getCell((int) pos.x + 1, (int) pos.y + 1) != null;
        }
        else {
            collX = world.getCollisionLayer().getCell((int) pos.x, (int) pos.y + 1) != null;
        }

        return collX;
    }

    private void pickPiece() {

        int index = 0;
        for (Rectangle p : world.getPieces()) {
            if (rect.overlaps(p)) {
                world.recoltePiece(index);
                Assets.manager.get(Assets.pceSFX, Sound.class).play(MarioGame.SOUND_VOLUME);
            }
            index++;
        }
    }

    private void dying(float deltaTime) {

        accel.y = World.GRAVITY;
        accel.scl(deltaTime);

        // Incrémente la vitesse
        vel.add(accel);
        pos.mulAdd(vel, deltaTime);
    }

    public int getDir() {
        return dir;
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
