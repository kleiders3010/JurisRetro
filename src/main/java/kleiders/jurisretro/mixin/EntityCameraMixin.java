package kleiders.jurisretro.mixin;

import kleiders.jurisretro.interfaces.EntityExtensions;
import net.minecraft.client.render.camera.EntityCamera;
import net.minecraft.core.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityCamera.class)
public class EntityCameraMixin {
	@Shadow
	public EntityLiving entity;

	@Inject(method = "getY", remap = false, at = @At("RETURN"), cancellable = true)
	protected void getFakeY(float f, CallbackInfoReturnable<Double> ci) {
		if (((EntityExtensions) entity).getExtraCustomData().getDouble("chickenTime") > 0) {
			ci.setReturnValue(ci.getReturnValue() - 1);
		}
	}
}
