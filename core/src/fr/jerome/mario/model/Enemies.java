package fr.jerome.mario.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.jerome.mario.Assets;

/**
 * Class générique pour les ennemis
 * Created by jerome on 23/10/14.
 */
public class Enemies extends DynamicGameObject {

//    private static final Texture textures = Assets.manager.get(Assets.enemiesTextures, Texture.class);

    private TextureRegion[][] goombaTexturesL;
    private TextureRegion[][] goombaTexturesR;

    public Enemies(float x, float y, int width, int height) {
        super(x, y, width, height);
        createTextures();
    }

    public void createTextures() {

        int nbImageX = 6;
        int nbImageY = 8;
        int unit = 16;

        Texture textures = Assets.manager.get(Assets.enemiesTextures, Texture.class);

        goombaTexturesL = TextureRegion.split(textures, unit, unit);
        goombaTexturesR = TextureRegion.split(textures, unit, unit);

        // Flip the textureRegions for right animations
        for (int i = 0; i < nbImageX; i++) {
            for (int j = 0; j < nbImageY; j++) {
                goombaTexturesR[i][j].flip(true, false);
            }
        }
    }

    public TextureRegion[][] getGoombaTexturesL() {
        return goombaTexturesL;
    }

    public TextureRegion[][] getGoombaTexturesR() {
        return goombaTexturesR;
    }
}
