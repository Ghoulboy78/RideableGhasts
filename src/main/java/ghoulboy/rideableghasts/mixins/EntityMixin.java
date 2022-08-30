package ghoulboy.rideableghasts.mixins;

import carpet.fakes.EntityInterface;
import ghoulboy.rideableghasts.fakes.GhastEntityInterface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    public Entity entity(){
        return (Entity) (EntityInterface) this;
    }

    @Inject(
            method = "getPrimaryPassenger",
            at = @At("RETURN"),
            cancellable = true)
    public void rideGhast(CallbackInfoReturnable<Entity> cir){
        Entity thisEntity = entity();
        Entity entity = thisEntity.getFirstPassenger();
        cir.setReturnValue(entity != null && this.canBeControlledByRider(entity) ? entity : null);
    }

    private boolean canBeControlledByRider(Entity entity) {
        return (entity() instanceof GhastEntityInterface sad) && sad.isSaddled() &&
        entity instanceof PlayerEntity playerEntity &&
        (playerEntity.getMainHandStack().isOf(Items.GHAST_TEAR) || playerEntity.getOffHandStack().isOf(Items.GHAST_TEAR));
    }
}
