package fr.jerome.mario.model.enemies;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import fr.jerome.mario.model.Enemies;
import fr.jerome.mario.model.World;

/**
 * Ennemi de base dans mario
 * Created by jerome on 15/10/14.
 */
public class Goomba extends Enemies {

    private static final int WIDTH = 1;
    private static final int HEIGHT = 1;

    private static final float SPEED = 2.5f;

    private Vector2 tmp;

    public Goomba(float posX, float posY) {
        super(posX, posY, WIDTH, HEIGHT, State.WALK, RIGHT);
        vel.x = dir * SPEED;
        vel.y = World.GRAVITY;
    }

    public void update(Array<Rectangle> collisions, float deltaTime) {

        // Vitesses avant collision
        vel.x = dir * SPEED;
        vel.y = World.GRAVITY/4;

        // Prochaine position si pas de collision
        tmp = new Vector2(pos);
        tmp.mulAdd(vel, deltaTime);

//        Gdx.app.log("tmp", tmp.toString());

        if (rect.y <= 2) {
            vel.y = 0;
            pos.y = 2;
        }

        // Collision test
        if (dir == RIGHT) {

            rect.setPosition(tmp.x, tmp.y);

            for (Rectangle rectColl : collisions) {
                if (rect.overlaps(rectColl) && rect.x == rectColl.x) {
                    dir = LEFT;
                    vel.x = 0;
//                    Gdx.app.log("coll", "right");
                }
            }
        }
        else if (dir == LEFT) {

            rect.setPosition(tmp.x, tmp.y);

            for (Rectangle rectColl : collisions) {
                if (rect.overlaps(rectColl)) {
                    dir = RIGHT;
                    vel.x = 0;
//                    Gdx.app.log("coll", "left");
                }
            }
        }
//        else if (!grounded) {
//
//            rect.setPosition(tmp.x, tmp.y);
//
//            for (Rectangle rectColl : collisions) {
//                if (rect.overlaps(rectColl)) {
//                    grounded = true;
//                    vel.y = 0;
//                    Gdx.app.log("coll", "ground");
//                }
//            }
//        }


        // Ajoute la velocité suivant les collisions
        pos.mulAdd(vel, deltaTime);

//        Gdx.app.log("pos goomba", "" +  Math.round(pos.x*100.0f)/100.0f + " " + Math.round(pos.y*100.0f)/100.0f);

    }
}