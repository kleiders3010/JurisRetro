package kleiders.jurisretro.mixin;

import com.mojang.nbt.CompoundTag;
import kleiders.jurisretro.JurisRetroMod;
import kleiders.jurisretro.interfaces.KleidersEntityExtensions;
import kleiders.jurisretro.packets.PacketChangeData;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.world.World;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class KleidersEntityMixin implements KleidersEntityExtensions {
	@Shadow
	public World world;

	@Unique
	private CompoundTag juris$extraCustomData;


	@Unique
	private boolean markDataChanged = false;

	@Inject(method = "tick", remap = false, at = @At("HEAD"))
	public void juris$tick(CallbackInfo ci) {
		if (this.markDataChanged) {
			syncExtraCustomData();
		}
	}

	@Unique
	@Override
	public void syncExtraCustomData() {
		if (!this.world.isClientSide && MinecraftServer.getInstance() != null) {
			MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(new PacketChangeData(((Entity) (Object) this), juris$extraCustomData), this.world.dimension.id);
		}
	}


	@Inject(method = "saveWithoutId", remap = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/Entity;addAdditionalSaveData(Lcom/mojang/nbt/CompoundTag;)V"))
	public void juris$beforeWriteCustomData(CompoundTag tag, CallbackInfo cir) {
		if (juris$extraCustomData != null && !juris$extraCustomData.getValues().isEmpty()) {
			tag.put("extra_data", juris$extraCustomData);
		}
	}

	@Inject(method = "load", remap = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/Entity;readAdditionalSaveData(Lcom/mojang/nbt/CompoundTag;)V"))
	public void port_lib$beforeReadCustomData(CompoundTag tag, CallbackInfo ci) {
		if (tag.containsKey("extra_data")) {
			juris$extraCustomData = tag.getCompound("extra_data");
		}
	}

	@Unique
	@Override
	public CompoundTag getExtraCustomData() {
		if (juris$extraCustomData == null) {
			juris$extraCustomData = new CompoundTag();
		}
		//Auto update of variable, currently set to false
		//this.markDataChanged = true;
		return juris$extraCustomData;
	}

	@Unique
	@Override
	public void setExtraCustomData(CompoundTag tag) {
		if (tag == null) {
			juris$extraCustomData = new CompoundTag();
		} else {
			juris$extraCustomData = tag;
		}
	}
}
