package com.Apothic0n.Astrological.mixin;

import com.Apothic0n.Astrological.core.objects.AstrologicalBlocks;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ChorusPlantBlock.class, priority = 69420)
public abstract class ChorusPlantBlockMixin {

    /**
     * @author Apothicon
     * @reason Allows chorus plants to connect to purpurite - targets only END_STONE checks.
     */
    @Definition(id = "is", method = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
    @Definition(id = "END_STONE", field = "Lnet/minecraft/world/level/block/Blocks;END_STONE:Lnet/minecraft/world/level/block/Block;")
    @Expression("?.is(END_STONE)")
    @WrapOperation(
            method = {
                    "getStateForPlacement(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;",
                    "updateShape",
                    "canSurvive"
            },
            at = @At(value = "MIXINEXTRAS:EXPRESSION")
    )
    private boolean astrological$allowPurpurite(BlockState instance, Block block, Operation<Boolean> original) {
        return original.call(instance, block) || instance.is(AstrologicalBlocks.PURPURITE.get());
    }
}

