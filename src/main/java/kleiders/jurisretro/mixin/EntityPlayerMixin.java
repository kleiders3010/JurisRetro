package kleiders.jurisretro.mixin;

import kleiders.jurisretro.entities.EntityIceZombie;
import kleiders.jurisretro.interfaces.EntityExtensions;
import kleiders.jurisretro.packets.PacketChangeSize;
import kleiders.jurisretro.packets.PacketRideEntity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.monster.EntityZombie;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.net.packet.Packet63SpawnParticleEffect;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;


@Mixin(value = EntityPlayer.class, remap = false)
public class EntityPlayerMixin extends EntityLiving {

	@Shadow
	protected boolean isDwarf;
	@Unique
	private boolean isChicken = false;


	public EntityPlayerMixin(World world) {
		super(world);
	}

	@Inject(method = "interact", remap = false, at = @At("HEAD"), cancellable = true)
	public void interact(EntityPlayer otherplayer, CallbackInfoReturnable<Boolean> cir) {
		EntityPlayer player = ((EntityPlayer) (Object) this);
		if (((EntityExtensions) player).getExtraCustomData().getDouble("chickenTime") > 0 && ((EntityExtensions) otherplayer).getExtraCustomData().getDouble("chickenTime") <= 0) {
			otherplayer.startRiding(player);
			if (!player.world.isClientSide && MinecraftServer.getInstance() != null) {
				MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(new PacketRideEntity(otherplayer, player), player.world.dimension.id);
			}
		}
		cir.setReturnValue(true);
	}

	@Inject(method = "isInWall", remap = false, at = @At("HEAD"), cancellable = true)
	protected void causeFallDamage(CallbackInfoReturnable<Boolean> cir) {
		EntityPlayer player = ((EntityPlayer) (Object) this);
		if (((EntityExtensions) player).getExtraCustomData().getDouble("chickenTime") > 0) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "causeFallDamage", remap = false, at = @At("HEAD"), cancellable = true)
	protected void causeFallDamage(float f, CallbackInfo ci) {
		EntityPlayer player = ((EntityPlayer) (Object) this);
		if (((EntityExtensions) player).getExtraCustomData().getDouble("chickenTime") > 0) {
			ci.cancel();
		}
	}

	@Inject(method = "onLivingUpdate", remap = false, at = @At("HEAD"), cancellable = true)
	public void onLiving(CallbackInfo ci) {
		EntityPlayer player = ((EntityPlayer) (Object) this);
		if (((EntityExtensions) player).getExtraCustomData().getDouble("chickenTime") > 0) {
			if (!player.onGround && player.yd < 0.0) {
				player.yd *= 0.6;
			}
		}
	}

	@Inject(method = "hurt", at = @At("RETURN"))
	private void injected(Entity attacker, int damage, DamageType type, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValue() && attacker instanceof EntityIceZombie) {
			EntityPlayer player = ((EntityPlayer) (Object) this);
			((EntityExtensions) player).getExtraCustomData().putDouble("iceSlowness", 100);
			((EntityExtensions) player).syncExtraCustomData();
		}
	}

	//Modify Player speed based on Ice Zombie Slowness effect, also spawn Snow Particles
	@Inject(method = "onLivingUpdate", remap = false, at = @At(value = "FIELD", target = "Lnet/minecraft/core/entity/player/EntityPlayer;speed:F", shift = At.Shift.AFTER))
	public void modifySpeed(CallbackInfo ci) {
		EntityPlayer player = ((EntityPlayer) (Object) this);
		if (((EntityExtensions) player).getExtraCustomData().getDouble("iceSlowness") > 0) {
			((EntityExtensions) player).getExtraCustomData().putDouble("iceSlowness", ((EntityExtensions) player).getExtraCustomData().getDouble("iceSlowness") - 1);
			player.speed *= 0.8;
			Random random = new Random();
			if (!player.world.isClientSide && MinecraftServer.getInstance() != null) {
				MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(new Packet63SpawnParticleEffect("snowshovel", player.x + (random.nextFloat() * 1) - (random.nextFloat() * 1), player.y + 0.25 + (random.nextFloat() * 0.2) - (random.nextFloat() * 0.2),
					player.z + (random.nextFloat() * 1) - (random.nextFloat() * 1), 0, -0.12, 0, 0), world.dimension.id);
			} else {
				player.world.spawnParticle("snowshovel", player.x + (random.nextFloat() * 1) - (random.nextFloat() * 1), player.y - 0.25 + (random.nextFloat() * 0.2) - (random.nextFloat() * 0.2),
					player.z + (random.nextFloat() * 1) - (random.nextFloat() * 1), 0, -0.12,0, 0);
			}
			if (((EntityExtensions) player).getExtraCustomData().getDouble("iceSlowness") <= 0) {
				((EntityExtensions) player).syncExtraCustomData();
			}
		}
	}

	@Inject(method = "setupDwarfMode", remap = false, at = @At("HEAD"), cancellable = true)
	private void onDwarf(CallbackInfo ci) {
		EntityPlayer player = ((EntityPlayer) (Object) this);
		if (((EntityExtensions) player).getExtraCustomData().getDouble("chickenTime") > 0) {
			((EntityExtensions) player).getExtraCustomData().putDouble("chickenTime", ((EntityExtensions) player).getExtraCustomData().getDouble("chickenTime") - 1);
			if (!isChicken) {
				this.setSize(0.6F, 0.7F);
				((EntityExtensions) player).syncExtraCustomData();
				isChicken = true;
				if (!player.world.isClientSide && MinecraftServer.getInstance() != null) {
					MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(new PacketChangeSize(player, true), player.world.dimension.id);
				}
			}
		} else {
			if (isChicken) {
				this.setSize(0.6F, 1.8F);
				isChicken = false;
				isDwarf = false;
				((EntityExtensions) player).syncExtraCustomData();
				if (!player.world.isClientSide && MinecraftServer.getInstance() != null) {
					MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(new PacketChangeSize(player, false), player.world.dimension.id);
				}
			}
		}
	}
}
