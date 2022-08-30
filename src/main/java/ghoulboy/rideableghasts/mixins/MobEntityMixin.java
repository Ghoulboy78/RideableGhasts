package ghoulboy.rideableghasts.mixins;

import carpet.fakes.MobEntityInterface;
import ghoulboy.rideableghasts.fakes.GhastEntityInterface;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    public MobEntity mob(){
        return (MobEntity) (MobEntityInterface) this;
    }

    @Inject(
        method = "interactMob",
        at = @At("RETURN"),
        cancellable = true
    )
    public void rideGhast(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        MobEntity mob = mob();
        if (mob instanceof GhastEntityInterface ghast && ghast.isSaddled() && mob instanceof GhastEntity && !mob.hasPassengers() && !player.shouldCancelInteraction()) {
            if (!mob.world.isClient) {
                player.startRiding(mob);
            }
            cir.setReturnValue(ActionResult.success(mob.world.isClient));
        }
    }
}
