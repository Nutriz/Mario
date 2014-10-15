package fr.jerome.mario.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import fr.jerome.mario.model.ennemis.Goomba;
import fr.jerome.mario.model.ennemis.Koopa;

/**
 * Représente le monde (un niveau)
 * Created by jerome on 01/10/14.
 */
public class World {

    public  Mario mario;
    private TiledMap tiledMap;
    private TiledMapTileLayer obstaclesLayer;
    private TiledMapTileLayer piecesLayer;
    private TiledMapTileLayer caissesLayer;
    private int mapWidth;
    private int mapHeight;

    private Array<Pieces> piecesList;
    private Array<Goomba> goombaList;
    private Array<Koopa> koopaList;

    public World() {

        loadMap("level1");
        obstaclesLayer = (TiledMapTileLayer)tiledMap.getLayers().get("obstacles");
        piecesLayer = (TiledMapTileLayer)tiledMap.getLayers().get("pieces");
        caissesLayer = (TiledMapTileLayer)tiledMap.getLayers().get("caisses");
        mapWidth = this.getTiledMap().getProperties().get("width", Integer.class);
        mapHeight = this.getTiledMap().getProperties().get("height", Integer.class);
        mario = new Mario(new Vector2(3, 2), this);
    }

    public void loadMap(String map) {
        tiledMap = new TmxMapLoader().load("Maps/"+map+".tmx");
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public TiledMapTileLayer getObstaclesLayer() {
        return obstaclesLayer;
    }

    public TiledMapTileLayer getPiecesLayer() {
        return piecesLayer;
    }

    public TiledMapTileLayer getCaissesLayer() {
        return caissesLayer;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
