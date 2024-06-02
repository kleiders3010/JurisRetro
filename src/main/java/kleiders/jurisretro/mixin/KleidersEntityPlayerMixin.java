package kleiders.jurisretro.mixin;

import kleiders.jurisretro.interfaces.KleidersEntityExtensions;
import kleiders.jurisretro.packets.PacketChangeData;
import kleiders.jurisretro.packets.PacketChangeSize;
import kleiders.jurisretro.packets.PacketRideEntity;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.player.inventory.ContainerPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.ChunkCoordinates;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(value = EntityPlayer.class, remap = false)
public class KleidersEntityPlayerMixin extends EntityLiving {

	@Shadow
	protected boolean isDwarf;
	@Unique
	private boolean isChicken = false;


	public KleidersEntityPlayerMixin(World world) {
		super(world);
	}

	@Inject(method = "interact", remap = false, at = @At("HEAD"), cancellable = true)
	public void interact(EntityPlayer otherplayer, CallbackInfoReturnable<Boolean> cir) {
		EntityPlayer player = ((EntityPlayer) (Object) this);
		if (((KleidersEntityExtensions) player).getExtraCustomData().getDouble("chickenTime") > 0 && ((KleidersEntityExtensions) otherplayer).getExtraCustomData().getDouble("chickenTime") <= 0) {
			otherplayer.startRiding(player);
			if (!player.world.isClientSide && MinecraftServer.getInstance() != null) {
				MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(new PacketRideEntity(otherplayer, player), player.world.dimension.id);
			}
		}
		cir.setReturnValue(true);
	}



	@Inject(method = "setupDwarfMode", remap = false, at = @At("HEAD"), cancellable = true)
	private void onDwarf(CallbackInfo ci) {
		EntityPlayer player = ((EntityPlayer) (Object) this);
		if (((KleidersEntityExtensions) player).getExtraCustomData().getDouble("chickenTime") > 0) {
			((KleidersEntityExtensions) player).getExtraCustomData().putDouble("chickenTime", ((KleidersEntityExtensions) player).getExtraCustomData().getDouble("chickenTime") - 1);
			if (!isChicken) {
				this.setSize(0.6F, 0.8F);
				((KleidersEntityExtensions) player).syncExtraCustomData();
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
				((KleidersEntityExtensions) player).syncExtraCustomData();
				if (!player.world.isClientSide && MinecraftServer.getInstance() != null) {
					MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(new PacketChangeSize(player, false), player.world.dimension.id);
				}
			}
		}
	}
}
