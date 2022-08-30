package ghoulboy.rideableghasts.mixins;

import net.minecraft.block.SpongeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SpongeBlock.class)
public abstract class AbsorbWaterMixin {

    @ModifyConstant(
        method = "absorbWater",
        constant = @Constant(intValue = 64)
    )
    private int onCheckBlockLimit(int blockLimit) {
        return 5;
    }

    
    @ModifyConstant(
        method = "absorbWater",
        constant = @Constant(intValue = 6)
    )
    private int onCheckOffsetLimit(int offsetLimit) {
        return 6;
    }
}
