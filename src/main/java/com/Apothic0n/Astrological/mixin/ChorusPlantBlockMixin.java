package com.Apothic0n.Astrological.mixin;

import com.Apothic0n.Astrological.core.objects.AstrologicalBlocks;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChorusPlantBlock.class)
public abstract class ChorusPlantBlockMixin {

    @WrapOperation(
            method = {
                    "getStateForPlacement",
                    "updateShape",
                    "canSurvive"
            },
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"
            )
    )
    private boolean astrological$allowPurpurite(
            BlockState state,
            Block block,
            Operation<Boolean> original
    ) {
        return original.call(state, block)
                || state.is(AstrologicalBlocks.PURPURITE.get());
    }
}

