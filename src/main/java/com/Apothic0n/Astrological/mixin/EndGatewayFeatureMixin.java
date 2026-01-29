package com.Apothic0n.Astrological.mixin;

import com.Apothic0n.Astrological.api.AstrologicalJsonReader;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.EndGatewayFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndGatewayFeature.class)
public class EndGatewayFeatureMixin {

    /**
     * @author Apothicon
     * @reason Prevents being stranded in the end after going through a gateway, by generating the gateway with a platform under it.
     */
    @Inject(
            method = "place",
            at = @At(
                    value = "RETURN",
                    ordinal = 0
            )
    )
    private void generateSquarePlatformBelowGateway(CallbackInfoReturnable<Boolean> cir, @Local(argsOnly = true) net.minecraft.world.level.levelgen.feature.FeaturePlaceContext context) {
        if (cir.getReturnValue()) {
            WorldGenLevel level = context.level();
            BlockPos pos = context.origin();
            
            makeSquare(level, pos.below(3), Blocks.AIR.defaultBlockState());
            makeSquare(level, pos.below(4), Blocks.AIR.defaultBlockState());
            
            if (AstrologicalJsonReader.endChestGeneratesBeneathGateways) {
                setBlock(level, pos.below(4), Blocks.ENDER_CHEST.defaultBlockState());
            }
            
            makeSquare(level, pos.below(5), Blocks.OBSIDIAN.defaultBlockState());
        }
    }

    @Unique
    private void makeSquare(WorldGenLevel level, BlockPos pos, BlockState blockState) {
        setBlock(level, pos, blockState);
        setBlock(level, pos.north(), blockState);
        setBlock(level, pos.east(), blockState);
        setBlock(level, pos.south(), blockState);
        setBlock(level, pos.west(), blockState);
        setBlock(level, pos.north().east(), blockState);
        setBlock(level, pos.south().east(), blockState);
        setBlock(level, pos.south().west(), blockState);
        setBlock(level, pos.north().west(), blockState);
    }

    @Unique
    private void setBlock(WorldGenLevel level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state, 3);
    }
}
