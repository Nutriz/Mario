package fr.jerome.mario;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Gestion des données
 * Created by jerome on 19/10/14.
 */
public class Assets {

    public static final AssetManager manager = new AssetManager();

    // TiledMap
    public static final String level1 = "maps/level1.tmx";

    //Textures
    public static final String marioTexturesRight = "tilesets/mario/marioLittle.png";
    public static final String enemiesTextures = "tilesets/enemies.png";

    // Audio
    public static final String jumpSFX = "audio/smb_jump-small.wav";
    public static final String pceSFX = "audio/smb_coin.wav";


    public static void load() {

        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        manager.load(level1, TiledMap.class);
        manager.load(marioTexturesRight, Texture.class);
        manager.load(enemiesTextures, Texture.class);
        manager.load(pceSFX, Sound.class);
        manager.load(jumpSFX, Sound.class);

    }

    public static void dispose() {
        manager.dispose();
    }
}
