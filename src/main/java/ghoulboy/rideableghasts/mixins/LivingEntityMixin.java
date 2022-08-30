package ghoulboy.rideableghasts.mixins;

import carpet.fakes.EntityInterface;
import carpet.fakes.LivingEntityInterface;
import ghoulboy.rideableghasts.fakes.GhastEntityInterface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    public LivingEntity entity() {
        return (LivingEntity) (LivingEntityInterface) this;
    }

    @Inject(
        method = "onTrackedDataSet",
        at = @At("TAIL")
    )
    public void mixin(TrackedData<?> data, CallbackInfo ci) {
        LivingEntity le = entity();
        if(le instanceof GhastEntityInterface sad && sad.getSaddled().equals(data)){
            le.getFallSounds();
        }
    }
}
