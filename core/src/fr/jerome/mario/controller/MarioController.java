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

    public void leftPressed() {
        mario.setFacingLeft(true);
        mario.setState(Mario.WALK_LEFT);
        mario.getVelocity().x = -Mario.SPEED;
    }

    public void leftReleased() {
        mario.setState(Mario.IDLE);
        mario.getVelocity().x = 0;
    }

    public void rightPressed() {
        mario.setFacingLeft(false);
        mario.setState(Mario.WALK_RIGHT);
        mario.getVelocity().x = Mario.SPEED;
    }

    public void rightReleased() {
        mario.setState(Mario.IDLE);
        mario.getVelocity().x = 0;
    }

    public void jumpPressed() {

    }

    public void fire() {

    }


}
