package com.Apothic0n.Astrological.core.sounds;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class AstrologicalSoundTypes {
    // Remove the constructor - this should NOT be instantiated
    private AstrologicalSoundTypes() {}

    public static final SoundType SELENITE = new SoundType(
            0.66F,
            1.33F,
            SoundEvents.GLASS_BREAK,
            SoundEvents.GLASS_STEP,
            SoundEvents.GLASS_PLACE,
            SoundEvents.GLASS_HIT,
            SoundEvents.GLASS_FALL
    );

    public static final SoundType TRIPHYLITE = new SoundType(
            1.0F,
            0.33F,
            SoundEvents.AMETHYST_BLOCK_BREAK,
            SoundEvents.AMETHYST_BLOCK_STEP,
            SoundEvents.AMETHYST_BLOCK_PLACE,
            SoundEvents.AMETHYST_BLOCK_HIT,
            SoundEvents.AMETHYST_BLOCK_FALL
    );

    public static final SoundType JADE = new SoundType(
            2.0F,
            2.04F,
            SoundEvents.AMETHYST_BLOCK_BREAK,
            SoundEvents.AMETHYST_BLOCK_STEP,
            SoundEvents.AMETHYST_BLOCK_PLACE,
            SoundEvents.AMETHYST_BLOCK_HIT,
            SoundEvents.AMETHYST_BLOCK_FALL
    );
}
