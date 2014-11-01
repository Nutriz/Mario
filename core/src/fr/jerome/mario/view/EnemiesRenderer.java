package fr.jerome.mario.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.jerome.mario.Assets;
import fr.jerome.mario.model.Enemies;
import fr.jerome.mario.model.World;
import fr.jerome.mario.model.enemies.Goomba;

/**
 * Renderer pour les ennemis
 * Created by jerome on 25/10/14.
 */
public class EnemiesRenderer {

    private TextureRegion[][] goombaTextures;
    private TextureRegion goombaDie;
    private Animation goombaWalk;

    private final World world;
    private final Batch batch;

    public EnemiesRenderer(World world, Batch batch) {

        this.world = world;
        this.batch = batch;
        createTextures();
        createAnimations();
    }

    public void createTextures() {

        int nbImageX = 3;
        int nbImageY = 8;
        int unit = 16;

        Texture textures = Assets.manager.get(Assets.enemiesTextures, Texture.class);

        goombaTextures = TextureRegion.split(textures, unit, unit);

//        // Flip the textureRegions for right animations
//        for (int i = 0; i < nbImageX; i++) {
//            for (int j = 0; j < nbImageY; j++) {
//                goombaTextures[i][j].flip(true, false);
//            }
//        }
    }

    private void createAnimations() {

        goombaDie   = goombaTextures[1][2];
        goombaWalk = new Animation(0.3f, goombaTextures[1][0], goombaTextures[1][1]);
    }

    public void renderer(float stateTime) {

        TextureRegion currentFrame;
        stateTime += Gdx.graphics.getDeltaTime();

        for (Goomba goomba : world.getGoombas()) {

            currentFrame = goombaWalk.getKeyFrame(stateTime, true);

            if (goomba.state == Enemies.State.DYING)
                currentFrame = goombaDie;

            batch.draw(currentFrame, goomba.pos.x, goomba.pos.y, goomba.getWidth(), goomba.getHeight());
        }
    }
}
