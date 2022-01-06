package com.platformer.game;

public enum AID {
    IDLE(0),WALK_RIGHT(1),WALK_LEFT(2),WALK_AWAY(3),WALK_TOWARDS(4),DEAD(5),FDEAD(6);

    private final int value;

    AID(final int newValue) {
        value = newValue;
    }

    public int val() { return value; }
}
