package fr.jerome.mario.controller;

import fr.jerome.mario.model.Mario;
import fr.jerome.mario.model.World;

/**
 * Created by jerome on 06/10/14.
 */
public class MarioController {

    enum Keys {
        LEFT, RIGHT, JUMP, FIRE
    }

    private World world;
    private Mario mario;


    public MarioController(World w, Mario m) {

        this.world = w;
        this.mario = m;
    }

    public void left() {

        mario.setState(Mario.State.WALKING);
        mario.getVelocity().x = -Mario.SPEED;

    }

    public void right() {

        mario.setState(Mario.State.WALKING);
        mario.getVelocity().x = Mario.SPEED;
    }

    public void jump() {

    }

    public void fire() {

    }


}
