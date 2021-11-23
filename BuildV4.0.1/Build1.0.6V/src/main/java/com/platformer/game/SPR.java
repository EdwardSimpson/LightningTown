package com.platformer.game;

import com.badlogic.gdx.graphics.Texture;

public enum SPR {
    BLANK(null),
//    SKIN1(new Texture("assets/spritesheets/ss_skin1.png")),
    SKIN1(new Texture(Assets.SKIN1)),
    SKIN2(new Texture(Assets.SKIN2)),
    SKIN3(new Texture(Assets.SKIN3)),
    SKIN4(new Texture(Assets.SKIN4)),
    HAIR1_BLACK(new Texture(Assets.HAIR1_BLACK)),
    HAIR1_BLUE(new Texture(Assets.HAIR1_BLUE)),
    HAIR1_GOLDBROWN(new Texture(Assets.HAIR1_GOLDBROWN)),
    HAIR1_GRAY(new Texture(Assets.HAIR1_GRAY)),
    HAIR1_RED(new Texture(Assets.HAIR1_RED)),
    EYES_RED(new Texture(Assets.EYES_RED)),
    HUD_ACTIONBAR(new Texture(Assets.HUD_ACTIONBAR)),
    HUD_ACTIONBAR_SLOT(new Texture(Assets.HUD_ACTIONBAR_SLOT)),
    HUD_HEALTHBAR_EMPTY(new Texture(Assets.HUD_HEALTHBAR_EMPTY)),
    HUD_HEALTHBAR_FULL(new Texture(Assets.HUD_HEALTHBAR_FULL));

    private Texture tex;

    SPR(Texture texture) {
        tex = texture;
    }

    public Texture tex() {return tex;}
}
