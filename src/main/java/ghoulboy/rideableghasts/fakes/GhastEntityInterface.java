package ghoulboy.rideableghasts.fakes;

import net.minecraft.entity.Saddleable;
import net.minecraft.entity.data.TrackedData;

public interface GhastEntityInterface extends Saddleable {
    TrackedData<Boolean> getSaddled();

    TrackedData<Integer> getBoostTime();
}
