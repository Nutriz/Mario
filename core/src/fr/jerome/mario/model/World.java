package fr.jerome.mario.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Représente le monde (un niveau)
 * Created by jerome on 01/10/14.
 */
public class World {

    private Array<Rectangle> tiles = new Array<Rectangle>();
    private TiledMap tiledMap;

    public World() {

        tiledMap = new TmxMapLoader().load("Maps/map_test.tmx");
        convertTiles();
    }

    private void convertTiles () {
        TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get("obstacles");
        tiles.clear();
        for (int y = 0; y <= 10; y++) {
            for (int x = 0; x <= 10; x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = new Rectangle();
                    rect.set(x, y, 1, 1);
                    tiles.add(rect);
                }
            }
        }
    }

    public Array<Rectangle> getTiles() {
        return tiles;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

}
