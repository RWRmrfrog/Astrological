package com.Apothic0n.Astrological.mixin;

import com.Apothic0n.Astrological.core.objects.AstrologicalBlocks;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChorusPlantBlock.class)
public abstract class ChorusPlantBlockMixin extends PipeBlock {

    public ChorusPlantBlockMixin(float p_55159_, Properties p_55160_) {
        super(p_55159_, p_55160_);
    }

    /**
     * @author Apothicon
     * @reason Allows chorus plants to visually connect to chorus flowers sitting on purpurite.
     *
     * Wraps every BlockState.is(Block) call inside getStateWithConnections (originally 6 separate
     * @Expression matches, one per connection direction). Since we only act when the checked block
     * is CHORUS_FLOWER, a single unrestricted @At (no ordinal) covers all of them.
     */
    @WrapOperation(
            method = "getStateWithConnections",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
    )
    private static boolean allowChorusPlantConnectToPurpurite(BlockState instance, Block block, Operation<Boolean> original) {
        if (block == Blocks.CHORUS_FLOWER) {
            return original.call(instance, block) || instance.is(AstrologicalBlocks.PURPURITE.get());
        }
        return original.call(instance, block);
    }

    /**
     * @author Apothicon
     * @reason Allows chorus plants to keep their shape/connection state when updated on purpurite.
     */
    @WrapOperation(
            method = "updateShape",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
    )
    public boolean allowChorusPlantUpdateShapeConnectToPurpurite(BlockState instance, Block block, Operation<Boolean> original) {
        if (block == Blocks.END_STONE) {
            return original.call(instance, block) || instance.is(AstrologicalBlocks.PURPURITE);
        }
        return original.call(instance, block);
    }

    /**
     * @author Apothicon
     * @reason Allows chorus plants to survive on purpurite.
     */
    @WrapOperation(
            method = "canSurvive",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
    )
    public boolean allowChorusPlantSurviveOnPurpurite(BlockState instance, Block block, Operation<Boolean> original) {
        if (block == Blocks.END_STONE) {
            return original.call(instance, block) || instance.is(AstrologicalBlocks.PURPURITE.get());
        }
        return original.call(instance, block);
    }
}