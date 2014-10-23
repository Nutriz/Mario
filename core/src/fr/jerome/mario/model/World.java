package fr.jerome.mario.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;

import fr.jerome.mario.Assets;
import fr.jerome.mario.model.ennemis.Goomba;
import fr.jerome.mario.model.ennemis.Koopa;
import fr.jerome.mario.screen.GameScreen;

/**
 * Représente le monde (un niveau)
 * Created by jerome on 01/10/14.
 */
public class World {

    private GameScreen game;

    private TiledMap tiledMap;
    private TiledMapTileLayer mapLayer;
    private TiledMapTileLayer collisionsLayer;
    private int mapWidth;
    private int mapHeight;
    private Array<Rectangle> pieces = new Array<Rectangle>();
    private Array<Goomba> goombas = new Array<Goomba>();
    private Array<Koopa> koopas = new Array<Koopa>();

    public  Mario mario;

    public World(GameScreen gs) {

        game = gs;
        loadMap((TiledMap)Assets.manager.get(Assets.level1));
        mario = new Mario(new Vector2(3, 6), this);
    }

    public void loadMap(TiledMap tm) {

        // Chargement de la tiledMap
        tiledMap = tm;
        // Masquer le layer de collision
        tiledMap.getLayers().get("collisions").setVisible(false);

        // Récupère la dimension de la map
        mapWidth = this.getTiledMap().getProperties().get("width", Integer.class);
        mapHeight = this.getTiledMap().getProperties().get("height", Integer.class);

        // Récupère les layers
        mapLayer = (TiledMapTileLayer)tiledMap.getLayers().get("map");
        collisionsLayer = (TiledMapTileLayer)tiledMap.getLayers().get("collisions");

        // Récupère les pièces dans une liste de Rectangle
        for (int y = 0; y <= getMapHeight(); y++) {
            for (int x = 0; x <= getMapWidth(); x++) {
                TiledMapTileLayer.Cell cell = mapLayer.getCell(x, y);
                if (cell != null && cell.getTile().getProperties().containsKey("piece"))
                    pieces.add(new Rectangle(x, y, 1, 1));
            }
        }

        generateEnnemis();

        // Récupère les collisions dans une liste de Rectangle
//        for (int y = 0; y <= getMapHeight(); y++) {
//            for (int x = 0; x <= getMapWidth(); x++) {
//                TiledMapTileLayer.Cell cell = collisionsLayer.getCell(x, y);
//                if (cell != null && cell.getTile().getProperties().containsKey("collision"))
//                    collision.add(new Rectangle(x, y, 1, 1));
//            }
//        }
    }

    public void generateEnnemis() {

        for (int y = 0; y <= getMapHeight(); y++) {
            for (int x = 0; x <= getMapWidth(); x++) {
                TiledMapTileLayer.Cell cell = mapLayer.getCell(x, y);
                if (cell != null && cell.getTile().getProperties().containsKey("ennemi")) {

                    // Gets the Goombas
                    if (cell.getTile().getProperties().get("ennemi", String.class).equals("goomba"))
                        goombas.add(new Goomba(x, y));
                    // Gets the Koopas
                    if (cell.getTile().getProperties().get("ennemi", String.class).equals("koopa"))
                        koopas.add(new Koopa(x, y));

                    // Delete the tile, ennemis must be rendered other way
                    cell.setTile(null);
                }
            }
        }
    }

    public void recoltePiece(int index) {

        mapLayer.getCell((int) pieces.get(index).x, (int) pieces.get(index).y).setTile(null);
        pieces.removeIndex(index);
        game.nbPieces++;
        game.score += 10;
    }

    public Array<Rectangle> getPieces() {
        return pieces;
    }

    public TiledMapTileLayer getCollisionsLayer() {
        return collisionsLayer;
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
