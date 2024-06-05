package kleiders.jurisretro.mixin;

import kleiders.jurisretro.interfaces.EntityExtensions;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayerSP.class)
public class EntityPlayerSPMixin {
	@Inject(method = "checkInTile", remap = false, at = @At("HEAD"), cancellable = true)
	public void shouldBeFree(CallbackInfoReturnable<Boolean> cir) {
		EntityPlayer player = ((EntityPlayerSP) (Object) this);
		if (((EntityExtensions) player).getExtraCustomData().getDouble("chickenTime") > 0) {
			cir.setReturnValue(false);
		}
	}
}
