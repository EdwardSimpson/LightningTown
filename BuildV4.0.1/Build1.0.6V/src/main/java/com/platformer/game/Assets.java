package com.platformer.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.math.Vector3;


public class Assets {

    public static final AssetManager manager = new AssetManager();


    public static final String thunder = "assets/thunder.ogg";
    public static final String walking = "assets/walk.ogg";
    public static final String sprinting = "assets/sprinting.ogg";
    public static final String music = "assets/untitled2.ogg";
    public static final String music1 = "assets/venice.ogg";
    public static final String music2 = "assets/joyJaws.ogg";
    public static final String music3 = "assets/ending.ogg";
    public static final String SKIN1 = "assets/spritesheets/ss_skin1.png";
    public static final String SKIN2 = "assets/spritesheets/ss_skin2.png";
    public static final String SKIN3 = "assets/spritesheets/ss_skin3.png";
    public static final String SKIN4 = "assets/spritesheets/ss_skin4.png";
    public static final String HAIR1_BLACK = "assets/spritesheets/ss_mhair1_black.png";
    public static final String HAIR1_BLUE = "assets/spritesheets/ss_mhair1_blue.png";
    public static final String HAIR1_GOLDBROWN = "assets/spritesheets/ss_mhair1_goldbrown.png";
    public static final String HAIR1_GRAY = "assets/spritesheets/ss_mhair1_gray.png";
    public static final String HAIR1_RED = "assets/spritesheets/ss_mhair1_red.png";
    public static final String EYES_RED = "assets/spritesheets/ss_eyes_red.png";
    public static final String HUD_ACTIONBAR = "assets/GUI/HUD_ActionBar_Frame.png";
    public static final String HUD_ACTIONBAR_SLOT = "assets/GUI/HUD_ActionBar_Slot_Empty.png";
    public static final String HUD_HEALTHBAR_EMPTY = "assets/GUI/HUD_HealthBar_Empty.png";
    public static final String HUD_HEALTHBAR_FULL = "assets/GUI/HUD_HealthBar_Fill.png";

//    public static final String BLANK = null;

    public Assets(ParticleSystem psys) {
        this.load(psys);
    }


    public static void load(ParticleSystem psys) {
        manager.load(thunder, Sound.class);
        manager.load(walking, Sound.class);
        manager.load(sprinting, Sound.class);
        manager.load(music, Music.class);
        manager.load(music1, Music.class);
        manager.load(music2, Music.class);
        manager.load(music3, Music.class);
        manager.load(SKIN1, Texture.class);
        manager.load(SKIN2, Texture.class);
        manager.load(SKIN3, Texture.class);
        manager.load(SKIN4, Texture.class);
        manager.load(HAIR1_BLACK, Texture.class);
        manager.load(HAIR1_BLUE, Texture.class);
        manager.load(HAIR1_GOLDBROWN, Texture.class);
        manager.load(HAIR1_GRAY, Texture.class);
        manager.load(HAIR1_RED, Texture.class);
        manager.load(EYES_RED, Texture.class);
        manager.load(HUD_ACTIONBAR, Texture.class);
        manager.load(HUD_ACTIONBAR_SLOT, Texture.class);
        manager.load(HUD_HEALTHBAR_EMPTY, Texture.class);
        manager.load(HUD_HEALTHBAR_FULL, Texture.class);

        ParticleEffectLoader.ParticleEffectLoadParameter loadParam = new ParticleEffectLoader.ParticleEffectLoadParameter(psys.getBatches());
        ParticleEffectLoader loader = new ParticleEffectLoader(new InternalFileHandleResolver());
        manager.setLoader(ParticleEffect.class, loader);
        manager.load("assets/particles/fire.pfx", ParticleEffect.class, loadParam);

        manager.finishLoading();

       System.out.println("load_check");

    }

    public static void update() {
        manager.update();
    }

    public static void dispose(){
        manager.dispose();
    }
}
