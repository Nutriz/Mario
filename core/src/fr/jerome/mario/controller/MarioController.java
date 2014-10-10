package fr.jerome.mario.controller;

import fr.jerome.mario.model.Mario;
import fr.jerome.mario.model.World;

/**
 * Classe permetant de controller Mario
 * Created by jerome on 06/10/14.
 */
public class MarioController {

    private World world;
    private Mario mario;

    public MarioController(World w) {

        this.world = w;
        this.mario = world.mario;
    }

    public void rightPressed() {
        mario.setState(Mario.WALK);
        mario.setDir(Mario.RIGHT);
        mario.getVelocity().x = Mario.SPEED;
    }

    public void rightReleased() {
        mario.setState(Mario.IDLE);
        mario.getVelocity().x = 0;
    }

    public void leftPressed() {
        mario.setState(Mario.WALK);
        mario.setDir(Mario.LEFT);
        mario.getVelocity().x = -Mario.SPEED;
    }

    public void leftReleased() {
        mario.setState(Mario.IDLE);
        mario.getVelocity().x = 0;
    }

    public void jumpPressed() {
        mario.setState(Mario.JUMP);
    }

    public void fire() {
    }


}
