package kleiders.jurisretro.mixin;

import kleiders.jurisretro.JurisRetroMod;
import kleiders.jurisretro.JurisRetroModItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;


@Mixin(value = EntityLiving.class, remap = false)
public class LivingEntityMixin {

	@Inject(method = "canClimb", at = @At(value = "RETURN"), cancellable = true)
	public void hasBoots(CallbackInfoReturnable<Boolean> cir) {
		if (((EntityLiving) (Object) this) instanceof EntityPlayer && ((EntityPlayer) (Object) this).inventory.armorInventory[0] != null && ((EntityPlayer) (Object) this).inventory.armorInventory[0].getItem() == JurisRetroModItems.spider_boots) {
		//	int i = MathHelper.floor_double(((EntityLiving) (Object) this).x);
		//	int j = MathHelper.floor_double(((EntityLiving) (Object) this).bb.minY);
		//	int k = MathHelper.floor_double(((EntityLiving) (Object) this).z);
		//	Block block = ((EntityLiving) (Object) this).world.getBlock(i, j, k);
			float expand = 0.002F;
			List<AABB> collidingBoundingBoxes = ((EntityLiving) (Object) this).world.getCubes(((EntityLiving) (Object) this), ((EntityLiving) (Object) this).bb.expand((double)expand, (double)-0.2F, (double)expand));
			cir.setReturnValue(!collidingBoundingBoxes.isEmpty() || ((EntityPlayer) (Object) this).horizontalCollision);
		}
	}
}
