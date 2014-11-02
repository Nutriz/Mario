package fr.jerome.mario.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import fr.jerome.mario.Assets;
import fr.jerome.mario.MarioGame;
import fr.jerome.mario.model.enemies.Goomba;
import fr.jerome.mario.model.enemies.Koopa;
import fr.jerome.mario.screen.GameScreen;

/**
 * Représente le monde (un niveau)
 * Created by jerome on 01/10/14.
 */
public class World {

    private MarioGame game;

    private TiledMap tiledMap;
    private TiledMapTileLayer mapLayer;
    private TiledMapTileLayer collisionLayer;

    private Array<Rectangle> collisions = new Array<Rectangle>();

    private int mapWidth;
    private int mapHeight;
    private Array<Rectangle> pieces = new Array<Rectangle>();
    private Array<Goomba> goombas = new Array<Goomba>();
    private Array<Koopa> koopas = new Array<Koopa>();

    public  Mario mario;

    public static final float GRAVITY = -14;


    public World(MarioGame mg) {

        game = mg;
        loadMap();
        mario = new Mario(new Vector2(40, 6), this);
    }

    public void loadMap() {

        // FIXME reload map si pces récoltées
        // Chargement de la tiledMap
        Assets.manager.finishLoading();
        this.tiledMap = Assets.manager.get(Assets.level1);

        // Récupère la dimension de la map
        mapWidth = this.getTiledMap().getProperties().get("width", Integer.class);
        mapHeight = this.getTiledMap().getProperties().get("height", Integer.class);

        // Récupère les layers
        mapLayer = (TiledMapTileLayer)tiledMap.getLayers().get("map");
        collisionLayer = new TiledMapTileLayer(mapWidth, mapHeight, 16, 16);

        // Récupère les pièces dans une liste de Rectangle
        for (int y = 0; y <= getMapHeight(); y++) {
            for (int x = 0; x <= getMapWidth(); x++) {
                TiledMapTileLayer.Cell cell = mapLayer.getCell(x, y);
                if (cell != null && cell.getTile().getProperties().containsKey("piece"))
                    pieces.add(new Rectangle(x, y, 1, 1));
                else if (cell != null) {
                    collisions.add(new Rectangle(x, y, 1, 1));
                    collisionLayer.setCell(x, y, new TiledMapTileLayer.Cell());
                }
            }
        }
        generateEnnemis();
    }

    public void update(float deltaTime) {

        mario.update(deltaTime);
        for (Goomba goomba : goombas)
            goomba.update(collisions, deltaTime);
    }

    public void generateEnnemis() {

        int unit = 16;
        MapObjects objects = tiledMap.getLayers().get("enemies").getObjects();

        for (MapObject object : objects) {
            RectangleMapObject rectObj = (RectangleMapObject) object;
            if (rectObj.getName().equals("goomba"))
                goombas.add(new Goomba(rectObj.getRectangle().x/unit, rectObj.getRectangle().y/unit));
            if (rectObj.getName().equals("koopa"))
                koopas.add(new Koopa(rectObj.getRectangle().x/unit, rectObj.getRectangle().y/unit));

        }
        // Supression du layer représentant les ennemis
//        tiledMap.getLayers().remove(tiledMap.getLayers().get("enemies"));
    }

    public void recoltePiece(int index) {

        mapLayer.getCell((int) pieces.get(index).x, (int) pieces.get(index).y).setTile(null);
        pieces.removeIndex(index);
        game.nbPieces++;
        game.score += 10;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public Array<Rectangle> getCollisions() {
        return collisions;
    }

    public TiledMapTileLayer getMapLayer() {
        return mapLayer;
    }

    public Array<Goomba> getGoombas() {
        return goombas;
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
