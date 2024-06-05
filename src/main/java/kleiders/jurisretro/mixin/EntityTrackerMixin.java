package kleiders.jurisretro.mixin;

import kleiders.jurisretro.entities.EntityChickenPigEgg;
import net.minecraft.core.entity.Entity;
import net.minecraft.server.entity.EntityTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTracker.class)
public class EntityTrackerMixin {

	@Inject(method = "trackEntity", remap = false, at = @At("HEAD"), cancellable = true)
	public void interact(Entity entity, CallbackInfo cir) {
		if (entity instanceof EntityChickenPigEgg) {
			((EntityTracker) (Object) this).trackEntity(entity, 64, 10, true);
		}
	}
}
