package fr.jerome.mario.model.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.jerome.mario.model.Enemies;
import fr.jerome.mario.model.World;

/**
 * Ennemi de base dans mario
 * Created by jerome on 15/10/14.
 */
public class Goomba extends Enemies {

    private static final int WIDTH = 1;
    private static final int HEIGHT = 1;

    private static final int SPEED = 4;

    private int dir = LEFT;

    private TextureRegion currentTexture;

    private TextureRegion idleR;
    private TextureRegion idleL;

    private Animation walkR;
    private Animation walkL;

    public Goomba(float posX, float posY) {
        super(posX, posY, WIDTH, HEIGHT);
        vel.x = SPEED;
        vel.y = World.GRAVITY;

        currentTexture = getGoombaTexturesR()[1][1];
    }

    public void update(float deltaTime) {

        super.update(deltaTime);

        if (pos.y <= 2)
            pos.y = 2;

        if (pos.x > 30) {
            vel.x *= -1;
            dir = LEFT;
            currentTexture = getGoombaTexturesL()[1][1];
        }
    }

    public TextureRegion getCurrentTexture() {
        return currentTexture;
    }
}
