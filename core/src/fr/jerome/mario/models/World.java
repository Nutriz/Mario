package fr.jerome.mario.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import fr.jerome.mario.models.Block;

/**
 * Représente le monde (un niveau)
 * Created by jerome on 01/10/14.
 */
public class World {

    private Array<Block> blocks = new Array<Block>();
    // Mario mario;

    public World() {
        createDemoWorld();
    }

    private void createDemoWorld() {

        int i = 0;

        blocks.add(new Block(new Vector2(0, i)));
        blocks.add(new Block(new Vector2(1, i)));
        blocks.add(new Block(new Vector2(2, i)));
        blocks.add(new Block(new Vector2(3, i)));
        blocks.add(new Block(new Vector2(4, i)));
        blocks.add(new Block(new Vector2(5, i)));
        blocks.add(new Block(new Vector2(6, i)));
        blocks.add(new Block(new Vector2(7, i)));
        blocks.add(new Block(new Vector2(8, i)));
        blocks.add(new Block(new Vector2(9, i)));
        blocks.add(new Block(new Vector2(10, i)));
        blocks.add(new Block(new Vector2(11, i)));
        blocks.add(new Block(new Vector2(12, i)));
        blocks.add(new Block(new Vector2(13, i)));
        blocks.add(new Block(new Vector2(14, i)));
        blocks.add(new Block(new Vector2(15, i)));
        blocks.add(new Block(new Vector2(16, i)));
        blocks.add(new Block(new Vector2(17, i)));
        blocks.add(new Block(new Vector2(18, i)));
        blocks.add(new Block(new Vector2(19, i)));
        blocks.add(new Block(new Vector2(20, i)));

        i = 1;

        blocks.add(new Block(new Vector2(0, i)));
        blocks.add(new Block(new Vector2(1, i)));
        blocks.add(new Block(new Vector2(2, i)));
        blocks.add(new Block(new Vector2(3, i)));
        blocks.add(new Block(new Vector2(4, i)));
        blocks.add(new Block(new Vector2(5, i)));
        blocks.add(new Block(new Vector2(6, i)));
        blocks.add(new Block(new Vector2(7, i)));
        blocks.add(new Block(new Vector2(8, i)));
        blocks.add(new Block(new Vector2(9, i)));
        blocks.add(new Block(new Vector2(10, i)));
        blocks.add(new Block(new Vector2(11, i)));
        blocks.add(new Block(new Vector2(12, i)));
        blocks.add(new Block(new Vector2(13, i)));
        blocks.add(new Block(new Vector2(14, i)));
        blocks.add(new Block(new Vector2(15, i)));
        blocks.add(new Block(new Vector2(16, i)));
        blocks.add(new Block(new Vector2(17, i)));
        blocks.add(new Block(new Vector2(18, i)));
        blocks.add(new Block(new Vector2(19, i)));
        blocks.add(new Block(new Vector2(20, i)));

        blocks.add(new Block(new Vector2(9, 2)));
        blocks.add(new Block(new Vector2(10, 2)));
        blocks.add(new Block(new Vector2(11, 2)));
        blocks.add(new Block(new Vector2(12, 2)));
        blocks.add(new Block(new Vector2(13, 2)));

        blocks.add(new Block(new Vector2(10, 3)));
        blocks.add(new Block(new Vector2(11, 3)));
        blocks.add(new Block(new Vector2(12, 3)));

        blocks.add(new Block(new Vector2(11, 4)));


    }

    public Array<Block> getBlocks() {
        return blocks;
    }

}
