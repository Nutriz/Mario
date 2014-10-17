package fr.jerome.mario.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import fr.jerome.mario.screen.GameScreen;

/**
 * Représente le monde (un niveau)
 * Created by jerome on 01/10/14.
 */
public class World {

    private TiledMap tiledMap;
    private TiledMapTileLayer layerMap;
    private int mapWidth;
    private int mapHeight;
    private Array<Rectangle> pieces = new Array<Rectangle>();

    public  Mario mario;

    private int score = 0;
    private int life = 3;
    private int nbPieces = 0;

    public World() {

        loadMap("level1");
        mario = new Mario(new Vector2(3, 2), this);
    }

    public void loadMap(String mapName) {

        tiledMap = new TmxMapLoader().load("Maps/"+mapName+".tmx");
        layerMap = (TiledMapTileLayer)tiledMap.getLayers().get("map");
        mapWidth = this.getTiledMap().getProperties().get("width", Integer.class);
        mapHeight = this.getTiledMap().getProperties().get("height", Integer.class);


        // Récupère les pièces dans une liste de Rectangle
        for (int y = 0; y <= getMapHeight(); y++) {
            for (int x = 0; x <= getMapWidth(); x++) {
                TiledMapTileLayer.Cell cell = layerMap.getCell(x, y);
                if (cell != null && cell.getTile().getProperties().containsKey("piece"))
                    pieces.add(new Rectangle(x, y, 1, 1));
            }
        }
    }

    public void recoltePiece(int index) {

        layerMap.getCell((int)pieces.get(index).x, (int)pieces.get(index).y).setTile(null);
        pieces.removeIndex(index);
        nbPieces++;
        score += 10;
        Gdx.app.log("nbPieces", ""+nbPieces);
    }

    public Array<Rectangle> getPieces() {
        return pieces;
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
