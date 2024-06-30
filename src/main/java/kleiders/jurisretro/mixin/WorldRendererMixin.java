package kleiders.jurisretro.mixin;

import kleiders.jurisretro.interfaces.EntityExtensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.core.util.phys.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = WorldRenderer.class, remap = false)
public class WorldRendererMixin {
	@Shadow
	public Minecraft mc;

	@ModifyVariable(method = "getMouseOver", remap = false, at = @At("STORE"), ordinal = 0)
	protected Vec3d getFakeY(Vec3d pos1) {
		if (((EntityExtensions) this.mc.thePlayer).getExtraCustomData().getDouble("chickenTime") > 0) {
			return Vec3d.createVector(pos1.xCoord, pos1.yCoord - 1, pos1.zCoord);
		}
		return pos1;
	}
}
