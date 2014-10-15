package fr.jerome.mario.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Représente le monde (un niveau)
 * Created by jerome on 01/10/14.
 */
public class World {

    private TiledMap tiledMap;
    private int mapWidth;
    private int mapHeight;
    private TiledMapTileLayer collisionLayer;
    private Array<Pieces> piecesList;

    public  Mario mario;

    public World() {

        loadMap("level1");
        mario = new Mario(new Vector2(3, 2), this);
    }

    public void loadMap(String map) {
        tiledMap = new TmxMapLoader().load("Maps/"+map+".tmx");
        mapWidth = this.getTiledMap().getProperties().get("width", Integer.class);
        mapHeight = this.getTiledMap().getProperties().get("height", Integer.class);
        collisionLayer = (TiledMapTileLayer)tiledMap.getLayers().get("collisions");

//        for (int i = 0; i < collisionLayer.)
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
