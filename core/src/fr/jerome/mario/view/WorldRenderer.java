package fr.jerome.mario.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import fr.jerome.mario.model.Block;
import fr.jerome.mario.model.World;

/**
 * Created by jerome on 02/10/14.
 */
public class WorldRenderer {

    // En nombre d'unité
    private static final float CAMERA_WIDTH = 20;
    private static final float CAMERA_HEIGHT = 15;

    private int width;
    private int height;
    private float ppuX; // pixels par unité pour X
    private float ppuY; // pixels par unité pour Y

    // Représentation du monde à afficher
    private World world;
    // Camera pour regarder dans le monde
    private OrthographicCamera camera;
    // Pour le rendu sans image
    private ShapeRenderer debugRenderer = new ShapeRenderer();
    // Pour le rendu avec image
    private TiledMapRenderer tiledMapRenderer;

    public WorldRenderer(World w) {

        world = w;
        this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        this.camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        this.camera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(world.getTiledMap());
        tiledMapRenderer.setView(camera);

    }

    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }

    public void render() {

        tiledMapRenderer.render();
    }

    public void drawDebug() {

        ShapeRenderer debugRenderer = new ShapeRenderer();
        debugRenderer.setColor(new Color(0, 1, 1, 1));
        debugRenderer.setProjectionMatrix(camera.combined);

        debugRenderer.begin(ShapeRenderer.ShapeType.Line);

            for (Block b : world.getBlocks()) {
                Rectangle rect = b.getBounds();
                float x1 = b.getPosition().x;
                float y1 = b.getPosition().y;
                debugRenderer.rect(x1, y1, rect.width, rect.height);
            }


        debugRenderer.end();
    }
}


