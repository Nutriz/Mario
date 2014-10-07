package fr.jerome.mario.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import fr.jerome.mario.model.Mario;
import fr.jerome.mario.model.World;

/**
 * Classe qui s'occupe d'afficher graphiquement World.java
 * Created by jerome on 02/10/14.
 */
public class WorldRenderer {

    // Dimensions de la caméra
    private static final float CAMERA_WIDTH = 20;
    private static final float CAMERA_HEIGHT = 15;

    // Représentation du monde à afficher
    private World world;
    // Camera pour regarder dans le monde
    private OrthographicCamera camera;
    // Rendu traçage debug
    private ShapeRenderer debugRenderer;
    // Pour le rendu avec image
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    // Notre héro
    private Mario mario;
    private SpriteBatch batch;

    private Texture marioImage;

    public WorldRenderer(World w, Mario m) {

        this.world = w;
        this.mario = m;
        this.batch = new SpriteBatch();
        // a 16*16 pixel texture representating mario
        this.marioImage = new Texture(Gdx.files.internal("Tilesets/mario.png"));

        this.debugRenderer = new ShapeRenderer();

        // Load the map
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(world.getTiledMap(), 1/16f);

        // caméra orthographic de 20x15 unités
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        this.camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        this.camera.update();

        Gdx.app.log("Résolution X : ", String.valueOf(Gdx.graphics.getWidth()));
        Gdx.app.log("Résolution Y : ", String.valueOf(Gdx.graphics.getHeight()));

        Gdx.app.log("Caméra X : ", String.valueOf(camera.viewportWidth));
        Gdx.app.log("Caméra X : ", String.valueOf(camera.viewportHeight));
    }

    public void render() {

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(marioImage, mario.getPosition().x, mario.getPosition().y, 1, 1);
        batch.end();

        drawDebug();
    }

    public void drawDebug() {

        // Configuration du rendu avec ShapeRenderer
        debugRenderer.setProjectionMatrix(camera.combined);

        // Récupération de la map et définir le nb de layer
        TiledMap map = world.getTiledMap();
        int nbLayer = map.getLayers().getCount();

        debugRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Boucle pour passer dans chaque layer
        for (int i = 0; i < nbLayer; i++) {

            // Récupère layer par layer
            TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(i);

            // Couleur différente pour chaque Layer
            switch (i) {
                case 0 : debugRenderer.setColor(new Color(0, 1, 1, 1)); break;
                case 1 : debugRenderer.setColor(new Color(1, 1, 1, 1)); break;
                case 2 : debugRenderer.setColor(new Color(1, 0, 1, 1)); break;
                case 3 : debugRenderer.setColor(new Color(0, 1, 1, 1)); break;
                case 4 : debugRenderer.setColor(new Color(1, 0, 0, 1)); break;
                case 5 : debugRenderer.setColor(new Color(0, 0, 0.5f, 1)); break;
                case 6 : debugRenderer.setColor(new Color(0.5f, 0.5f, 0.5f, 1)); break;
            }

            // Boucle avec test si cell existe ou pas, traçage si existante
            for (int y = 0; y <= 15; y++) {
                for (int x = 0; x <= 20; x++) {
                    TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                    if (cell != null) {
                        Rectangle rect = new Rectangle();
                        rect.set(x, y, 1, 1);
                        debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
                    }
                }
            }
        }

        debugRenderer.end();
    }
}


