package fr.jerome.mario.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Représente une pièce d'or
 * Created by jerome on 15/10/14.
 */
public class Pieces {

    private Rectangle rect;

    public Pieces(int x, int y) {
        this.rect = new Rectangle(x, y, 16, 16);
    }
}
