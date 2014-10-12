package fr.jerome.mario.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.jerome.mario.model.Mario;
import fr.jerome.mario.model.World;

/**
 * Classe qui s'occupe d'afficher graphiquement World.java
 * Created by jerome on 02/10/14.
 */
public class WorldRenderer {

    private boolean debug = false;

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

    // Mario animations
    private TextureRegion marioIdleRight;
    private TextureRegion marioIdleLeft;
    private TextureRegion marioJumpRight;
    private TextureRegion marioJumpLeft;
    private TextureRegion marioReturnRight;
    private TextureRegion marioReturnLeft;
    private TextureRegion marioDies;
    private Animation walkRight;
    private Animation walkLeft;
    private float stateTime = 0f;

    public WorldRenderer(World w) {

        this.world = w;
        this.mario = world.mario;
        this.batch = new SpriteBatch();

        this.debugRenderer = new ShapeRenderer();

        // Load the map
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(world.getTiledMap(), 1/16f);

        // caméra orthographic de 20x15 unités
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        this.camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        this.camera.update();

        createAnimations();
    }

    public void moveCamera() {

        camera.position.set(mario.getPos().x, CAMERA_HEIGHT / 2f, 0);
        camera.update();

    }

    public void render() {

        tiledMapRenderer.setView(camera);
        batch.setProjectionMatrix(camera.combined);

        tiledMapRenderer.render();
        batch.begin();
            renderMario();
        batch.end();

        if (debug) drawDebug() ;

        moveCamera();
    }

    private void renderMario() {

        TextureRegion currentFrame = null;
        stateTime += Gdx.graphics.getDeltaTime();

        if (mario.getState() == Mario.JUMP) {
            if (mario.getDir() == Mario.RIGHT)
                currentFrame = marioJumpRight;
            else
                currentFrame = marioJumpLeft;
        }
        else if (mario.getState() == Mario.WALK) {
            if (mario.getDir() == Mario.RIGHT)
                currentFrame = walkRight.getKeyFrame(stateTime, true);
            else
                currentFrame = walkLeft.getKeyFrame(stateTime, true);
        }
        else if (mario.getState() == Mario.IDLE) {
            if (mario.getDir() == Mario.RIGHT)
                currentFrame = marioIdleRight;
            else
                currentFrame = marioIdleLeft;
        }
        // Return animation
        if (mario.getDir() == Mario.RIGHT && mario.getVel().x < 0)
            currentFrame = marioReturnRight;
        else if ((mario.getDir() == Mario.LEFT && mario.getVel().x > 0))
            currentFrame = marioReturnLeft;


        batch.draw(currentFrame, mario.getPos().x, mario.getPos().y, 1, 1);
    }

    private void createAnimations() {

        int nbImage = 14;

        Texture marioTexturesRight = new Texture(Gdx.files.internal("Tilesets/Mario/marioLittle.png"));
        TextureRegion[] regionsRight = new TextureRegion[nbImage];
        TextureRegion[] regionsLeft = new TextureRegion[nbImage];

        TextureRegion[][] tmp = TextureRegion.split(marioTexturesRight,
                marioTexturesRight.getWidth()/nbImage,
                marioTexturesRight.getHeight());

        for (int i = 0; i < nbImage; i++) {
            regionsRight[i] = tmp[0][i];
            regionsLeft[i] = new TextureRegion(regionsRight[i]);
            regionsLeft[i].flip(true, false);
        }

        marioIdleRight = regionsRight[0];
        marioIdleLeft = regionsLeft[0];
        marioJumpRight = regionsRight[5];
        marioJumpLeft = regionsLeft[5];
        marioReturnRight = regionsRight[4];
        marioReturnLeft = regionsLeft[4];
        marioDies = regionsRight[6];

        walkRight = new Animation(0.1f, regionsRight[0], regionsRight[1], regionsRight[2], regionsRight[3]);
        walkLeft = new Animation(0.1f,  regionsLeft[0], regionsLeft[1], regionsLeft[2], regionsLeft[3]);

    }

    public void drawDebug() {

        // Configuration du rendu avec ShapeRenderer
        debugRenderer.setProjectionMatrix(camera.combined);

        // Récupération de la map et définir le nb de layer
        TiledMap map = world.getTiledMap();
        int nbLayer = map.getLayers().getCount();

        debugRenderer.begin(ShapeRenderer.ShapeType.Filled);

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

        // Mario
        debugRenderer.setColor(new Color(0.5f, 0.5f, 0.5f, 1));
        debugRenderer.rect(mario.getPos().x, mario.getPos().y, 1, 1);
        debugRenderer.end();

        // TODO debugMode avec représentation des vecteur Pos, Vel et Accel en temps réel
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(new Color(1, 1, 1, 1));

//        // Vecteur pos
//        debugRenderer.line(0, 0, mario.getPos().x, mario.getPos().y);
//        // Vecteur vel
//        Vector2 vecVel = new Vector2(mario.getVel());
//        vecVel.scl(Gdx.graphics.getDeltaTime());
//        debugRenderer.line(mario.getPos().x, mario.getPos().y, mario.getPos().x+vecVel.x, mario.getPos().y+vecVel.y);
//        // Vecteur accel
////        debugRenderer.line(0, 0, mario.getPos().x, mario.getPos().y);

        debugRenderer.end();
    }
}


