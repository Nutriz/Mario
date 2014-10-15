package fr.jerome.mario.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Représente un block du monde composé d'une suite de block
 * Created by jerome on 01/10/14.
 */
public class Block {

    static final float SIZE = 1f;

    Vector2 position = new Vector2();
    Rectangle rect = new Rectangle();

    public Block(Vector2 pos) {
        this.position = pos;
        this.rect.x = position.x;
        this.rect.y = position.y;
        this.rect.width = SIZE;
        this.rect.height = SIZE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRect() {
        return rect;
    }
}
