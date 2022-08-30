package ghoulboy.rideableghasts.mixins;

import ghoulboy.rideableghasts.fakes.GhastEntityInterface;
import net.minecraft.entity.SaddledComponent;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GhastEntity.class)
public class GhastEntityMixin implements GhastEntityInterface {

    private static final TrackedData<Integer> BOOST_TIME;
    private static final TrackedData<Boolean> SADDLED;

    static {
        BOOST_TIME = DataTracker.registerData(GhastEntity.class, TrackedDataHandlerRegistry.INTEGER);
        SADDLED = DataTracker.registerData(GhastEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    public SaddledComponent saddledComponent = new SaddledComponent(ghast().getDataTracker(), BOOST_TIME, SADDLED);

    public TrackedData<Boolean> getSaddled() {
        return SADDLED;
    }

    public TrackedData<Integer> getBoostTime() {
        return BOOST_TIME;
    }

    private GhastEntity ghast() {
        return (GhastEntity) (GhastEntityInterface) this;
    }

    @Override
    public boolean canBeSaddled() {
        return ghast().isAlive();
    }

    @Override
    public void saddle(@Nullable SoundCategory sound) {
        this.saddledComponent.setSaddled(true);
        if (sound != null) {
            ghast().world.playSoundFromEntity(null, ghast(), SoundEvents.ENTITY_GHAST_HURT, sound, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled() {
        return saddledComponent.isSaddled();
    }

    @Inject(
            method = "initDataTracker",
            at = @At("TAIL")
    )
    private void trackSaddlingData(CallbackInfo ci) {
        ghast().getDataTracker().startTracking(BOOST_TIME, 0);
        ghast().getDataTracker().startTracking(SADDLED, false);
    }
}
