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





}
