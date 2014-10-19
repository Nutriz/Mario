package fr.jerome.mario.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import fr.jerome.mario.Assets;
import fr.jerome.mario.model.Mario;
import fr.jerome.mario.model.World;

/**
 * Classe qui s'occupe d'afficher graphiquement World.java
 * Created by jerome on 02/10/14.
 */
public class WorldRenderer {

    private final boolean debug = false;

    // Dimensions de la caméra
    private static final float CAMERA_WIDTH = 20;
    private static final float CAMERA_HEIGHT = 15;
    private BitmapFont font;

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

    // Mario animations
    private TextureRegion marioIdleR;
    private TextureRegion marioIdleL;
    private TextureRegion marioJumpR;
    private TextureRegion marioJumpL;
    private TextureRegion marioReturnR;
    private TextureRegion marioReturnL;
    private TextureRegion marioDies;
    private Animation walkR;
    private Animation walkL;
    private float stateTime = 0f;

    public WorldRenderer(World w) {

        this.font = new BitmapFont();
        this.font.setScale(0.01f);

        this.world = w;
        this.mario = world.mario;

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

    // TODO Implémenter l'anti retour de la camera
    public void moveCamera() {

        float marioX = mario.getPos().x;
        int mapWidth  = world.getTiledMap().getProperties().get("width", Integer.class);
        // nombres d'unités d'avance pour la caméra  par rapport à mario
        int decCam = 4;
        float oldX = camera.position.x;

        // Suivre mario s'il avance et ne dépasse pas les dimensions de la map
        if (marioX > (CAMERA_WIDTH / 2f - decCam) && marioX < mapWidth - decCam - CAMERA_WIDTH / 2f)
            camera.position.set(marioX + decCam, camera.position.y, 0);

        camera.update();
    }

    public void render() {

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        tiledMapRenderer.getSpriteBatch().begin();
            renderMario();
        tiledMapRenderer.getSpriteBatch().end();

        if (debug) drawDebug() ;

        moveCamera();
    }

    private void renderMario() {

        int dir = mario.dir;
        int state = mario.state;
        TextureRegion currentFrame = null;
        stateTime += Gdx.graphics.getDeltaTime();

        if (state == Mario.JUMP) {
            if (dir == Mario.RIGHT)
                currentFrame = marioJumpR;
            else
                currentFrame = marioJumpL;
        }
        else if (state == Mario.WALK) {
            if (dir == Mario.RIGHT)
                currentFrame = walkR.getKeyFrame(stateTime, true);
            else
                currentFrame = walkL.getKeyFrame(stateTime, true);
        }
        else if (state == Mario.IDLE) {
            if (dir == Mario.RIGHT)
                currentFrame = marioIdleR;
            else
                currentFrame = marioIdleL;
        }
        // Return animation
        if (state != Mario.JUMP) {
            if (dir == Mario.RIGHT && mario.getVel().x < 0)
                currentFrame = marioReturnR;
            else if ((dir == Mario.LEFT && mario.getVel().x > 0))
                currentFrame = marioReturnL;
        }
        // FIXME mario meurt pas
        if (mario.state == Mario.DYING) {
            currentFrame = marioDies;
        }
        tiledMapRenderer.getSpriteBatch().draw(currentFrame, mario.getPos().x, mario.getPos().y, 1, 1);
    }

    private void createAnimations() {

        int nbImage = 14;

        Texture marioTexturesRight = Assets.manager.get(Assets.marioTexturesRight, Texture.class);
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

        marioIdleR = regionsRight[0];
        marioIdleL = regionsLeft[0];
        marioJumpR = regionsRight[5];
        marioJumpL = regionsLeft[5];
        marioReturnR = regionsRight[4];
        marioReturnL = regionsLeft[4];
        marioDies = regionsRight[6];

        walkR = new Animation(0.1f, regionsRight[0], regionsRight[1], regionsRight[2], regionsRight[3]);
        walkL = new Animation(0.1f,  regionsLeft[0], regionsLeft[1], regionsLeft[2], regionsLeft[3]);

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
                case 0 : debugRenderer.setColor(new Color(0, 0, 0, 1)); break; // Ciel
                case 1 : debugRenderer.setColor(new Color(1, 0.5f, 0.5f, 1)); break; // Nuages
                case 2 : debugRenderer.setColor(new Color(0, 0, 1, 1)); break; // Map
                case 3 : debugRenderer.setColor(new Color(0, 1, 1, 1)); break; // Collision
            }

            // Boucle avec test si cell existe ou pas, traçage si existante
            for (int y = 0; y <= world.getMapHeight(); y++) {
                for (int x = 0; x <= world.getMapWidth(); x++) {
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


