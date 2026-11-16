package com.vice.balancedflight.content.flightAnchor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightSafetyTest {
    @Test void onlyLivingSurvivalOrAdventurePlayersNeedAModifier() {
        assertTrue(FlightSafety.shouldGrantFlight(true, false, false));
        assertFalse(FlightSafety.shouldGrantFlight(false, false, false));
        assertFalse(FlightSafety.shouldGrantFlight(true, true, false));
        assertFalse(FlightSafety.shouldGrantFlight(true, false, true));
    }
    @Test void normalJumpingPrefersRingFlightOverElytra() {
        assertTrue(FlightSafety.blocksElytraTakeoff(true, false, true));
    }

    @Test void intentionalGlidingAndDisabledProtectionRemainAvailable() {
        assertFalse(FlightSafety.blocksElytraTakeoff(true, true, true));
        assertFalse(FlightSafety.blocksElytraTakeoff(true, false, false));
    }

    @Test void ordinaryElytraAndOtherFlightSourcesRemainUnaffected() {
        assertFalse(FlightSafety.blocksElytraTakeoff(false, false, true));
    }
    @Test void protectsActualAirborneFlightLoss() {
        assertTrue(FlightSafety.needsSlowFalling(true, false, false, true, false));
    }

    @Test void walkingOutOfRangeDoesNotGrantSlowFalling() {
        assertFalse(FlightSafety.needsSlowFalling(false, true, false, true, false));
    }

    @Test void ordinaryJumpingAndFallingDoNotGrantSlowFalling() {
        assertFalse(FlightSafety.needsSlowFalling(false, false, false, true, false));
    }

    @Test void landingOrDyingDoesNotGrantSlowFalling() {
        assertFalse(FlightSafety.needsSlowFalling(true, true, false, true, false));
        assertFalse(FlightSafety.needsSlowFalling(true, false, false, false, false));
    }

    @Test void doesNotInterfereWithGlidingOrExistingPotions() {
        assertFalse(FlightSafety.needsSlowFalling(true, false, true, true, false));
        assertFalse(FlightSafety.needsSlowFalling(true, false, false, true, true));
    }
}
