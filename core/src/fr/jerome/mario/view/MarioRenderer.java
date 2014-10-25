package fr.jerome.mario.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.jerome.mario.Assets;
import fr.jerome.mario.model.Mario;

/**
 * Mario renderer
 * Created by jerome on 25/10/14.
 */
public class MarioRenderer {

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

    private Batch batch;

    public MarioRenderer(Mario mario, Batch batch) {

        this.mario = mario;
        this.batch = batch;
        createMarioAnimations();

    }

    private void createMarioAnimations() {

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

    public void renderer(float stateTime) {

        int dir = mario.dir;
        Mario.State state = mario.getState();
        TextureRegion currentFrame = null;
        stateTime += Gdx.graphics.getDeltaTime();

        if (state == Mario.State.JUMP) {
            if (dir == Mario.RIGHT)
                currentFrame = marioJumpR;
            else
                currentFrame = marioJumpL;
        }
        else if (state == Mario.State.WALK) {
            if (dir == Mario.RIGHT)
                currentFrame = walkR.getKeyFrame(stateTime, true);
            else
                currentFrame = walkL.getKeyFrame(stateTime, true);
        }
        else if (state == Mario.State.IDLE) {
            if (dir == Mario.RIGHT)
                currentFrame = marioIdleR;
            else
                currentFrame = marioIdleL;
        }
        // Return animation
        if (state != Mario.State.JUMP) {
            if (dir == Mario.RIGHT && mario.getVel().x < 0)
                currentFrame = marioReturnR;
            else if ((dir == Mario.LEFT && mario.getVel().x > 0))
                currentFrame = marioReturnL;
        }
        // FIXME mario meurt pas
        if (state == Mario.State.DYING) {
            currentFrame = marioDies;
        }
        batch.draw(currentFrame, mario.getPos().x, mario.getPos().y, 1, 1);
    }
}
