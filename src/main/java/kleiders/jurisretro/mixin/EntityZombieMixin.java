package kleiders.jurisretro.mixin;

import kleiders.jurisretro.entities.EntityIceZombie;
import net.minecraft.core.entity.monster.EntityZombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EntityZombie.class, remap = false)
public class EntityZombieMixin {

	@Redirect(method = "onLivingUpdate", remap = false, at = @At(value = "FIELD", target = "Lnet/minecraft/core/entity/monster/EntityZombie;remainingFireTicks:I"))
	public void changeFire(EntityZombie instance, int value) {
		if (instance instanceof EntityIceZombie && ((EntityIceZombie) instance).hasIceCube()) {
		} else {
			instance.remainingFireTicks = value;
		}
	}
}
