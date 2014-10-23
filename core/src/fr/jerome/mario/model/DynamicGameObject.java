package fr.jerome.mario.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jerome on 23/10/14.
 */
public class DynamicGameObject {

    public Vector2 pos;
    public Vector2 vel;
    public Vector2 accel;
    public Rectangle rect;

    public DynamicGameObject(float x, float y, float width, float height) {

        this.pos = new Vector2(x, y);
        this.vel = new Vector2();
        this.accel = new Vector2();
        this.rect = new Rectangle(x, y, width, height);
    }
}
