package com.vice.balancedflight.foundation.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import static net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class BalancedFlightConfig
{
    public static ModConfigSpec ConfigSpec;

    public static ConfigValue<Boolean> disableFallDamageWhenWearingRing;
    public static ConfigValue<Boolean> disableFallDamageNearAnchor;

    public static ConfigValue<Boolean> CreativeAnchor;
    public static ConfigValue<Boolean> CreativeAscended;

    public static ConfigValue<Double> anchorDistanceMultiplier;
    public static ConfigValue<Integer> anchorStress;

    static
    {
        ConfigBuilder builder = new ConfigBuilder("Balanced Flight Settings");

        builder.Block("Flight Options", b -> {
            CreativeAscended = b.define("Ascended Ring Gives Unlimited Creative Flight (will fall back to Basic tier inside range)", true);

            CreativeAnchor = b.define("Flight Anchor Gives Creative Flight", true);
        });

        builder.Block("Balancing Config", b -> {
            anchorDistanceMultiplier = b.defineInRange("Anchor Distance Multiplier (0d -> 10d, default 1d)", 1.0d, 0.0d, 10.0d);
            anchorStress = b.defineInRange("Anchor stress impact", 256, 0, Integer.MAX_VALUE);
            disableFallDamageWhenWearingRing = b.define("Disable Fall Damage When Wearing Ascended Ring", true);
            disableFallDamageNearAnchor = b.define("Disable Fall Damage Near Flight Anchor", true);
        });

        ConfigSpec = builder.Save();
    }

    public static void init() {}
}
