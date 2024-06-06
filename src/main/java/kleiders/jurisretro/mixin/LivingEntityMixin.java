package kleiders.jurisretro.mixin;

import kleiders.jurisretro.JurisRetroMod;
import kleiders.jurisretro.JurisRetroModItems;
import kleiders.jurisretro.interfaces.EntityExtensions;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.net.packet.Packet63SpawnParticleEffect;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;


@Mixin(value = EntityLiving.class, remap = false)
public class LivingEntityMixin {

	@Unique
	private float baseSpeed = 0F;

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

	//Modify non-player speed if they have iceSlowness
	@Inject(method = "moveEntityWithHeading", remap = false, at = @At(value = "HEAD"))
	public void modifySpeed(float moveStrafing, float moveForward, CallbackInfo ci) {
		EntityLiving living = ((EntityLiving) (Object) this);
		if (!(living instanceof EntityPlayer)) {
			this.baseSpeed = living.speed;
			if (((EntityExtensions) living).getExtraCustomData().getDouble("iceSlowness") > 0) {
				((EntityExtensions) living).getExtraCustomData().putDouble("iceSlowness", ((EntityExtensions) living).getExtraCustomData().getDouble("iceSlowness") - 1);
				living.speed *= 0.2;
				Random random = new Random();
				float height = living.bbHeight;
				if (!living.world.isClientSide && MinecraftServer.getInstance() != null) {
					MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(new Packet63SpawnParticleEffect("snowshovel", living.x + (random.nextFloat() * 1) - (random.nextFloat() * 1), living.y + (height / 2) + (random.nextFloat() * (height / 2)) - (random.nextFloat() * (height / 2)),
						living.z + (random.nextFloat() * 1) - (random.nextFloat() * 1), 0, -0.12, 0, 0), living.world.dimension.id);
				} else {
					living.world.spawnParticle("snowshovel", living.x + (random.nextFloat() * 1) - (random.nextFloat() * 1), living.y + (height / 2) + (random.nextFloat() * (height / 2)) - (random.nextFloat() * (height / 2)),
						living.z + (random.nextFloat() * 1) - (random.nextFloat() * 1), 0, -0.12,0, 0);
				}
				if (((EntityExtensions) living).getExtraCustomData().getDouble("iceSlowness") <= 0) {
					((EntityExtensions) living).syncExtraCustomData();
				}
			}
		}
	}

	@Inject(method = "moveEntityWithHeading", remap = false, at = @At(value = "TAIL"))
	public void revertSpeed(float moveStrafing, float moveForward, CallbackInfo ci) {
		EntityLiving living = ((EntityLiving) (Object) this);
		if (!(living instanceof EntityPlayer)) {
			living.speed = this.baseSpeed;
		}
	}
}
