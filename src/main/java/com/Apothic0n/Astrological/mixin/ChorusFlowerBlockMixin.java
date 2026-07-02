package com.Apothic0n.Astrological.mixin;

import com.Apothic0n.Astrological.core.objects.AstrologicalBlocks;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChorusFlowerBlock.class)
public abstract class ChorusFlowerBlockMixin {

    /**
     * @author Apothicon
     * @reason Allows chorus flowers to survive on purpurite.
     *
     * Wraps every BlockState.is(Block) call inside canSurvive (no ordinal/ Expression matching needed).
     * Only the call that's actually checking against END_STONE gets the extra purpurite check;
     * every other .is() call in the method passes straight through to the original.
     */
    @WrapOperation(
            method = "canSurvive",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
    )
    public boolean allowChorusFlowerOnPurpurite(BlockState instance, Block block, Operation<Boolean> original) {
        if (block == Blocks.END_STONE) {
            return original.call(instance, block) || instance.is(AstrologicalBlocks.PURPURITE.get());
        }
        return original.call(instance, block);
    }

    /**
     * @author Apothicon
     * @reason Allows chorus flowers to keep growing while sitting on purpurite.
     */
    @WrapOperation(
            method = "randomTick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
    )
    public boolean allowChorusFlowerConnectToPurpurite(BlockState instance, Block block, Operation<Boolean> original) {
        if (block == Blocks.END_STONE) {
            return original.call(instance, block) || instance.is(AstrologicalBlocks.PURPURITE.get());
        }
        return original.call(instance, block);
    }
}