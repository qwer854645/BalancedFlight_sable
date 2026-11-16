package com.vice.balancedflight.content.flightAnchor;

/** Decisions shared by flight permission changes and their regression tests. */
public final class FlightSafety {
    private FlightSafety() {}

    public static boolean shouldGrantFlight(boolean alive, boolean creative, boolean spectator) {
        return alive && !creative && !spectator;
    }

    public static boolean blocksElytraTakeoff(boolean ringFlight, boolean sneaking, boolean enabled) {
        return enabled && ringFlight && !sneaking;
    }

    public static boolean needsSlowFalling(boolean wasFlying, boolean onGround,
            boolean gliding, boolean alive, boolean alreadyProtected) {
        return wasFlying && !onGround && !gliding && alive && !alreadyProtected;
    }
}
