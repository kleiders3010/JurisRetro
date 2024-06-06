package kleiders.jurisretro.mixin;

import kleiders.jurisretro.entities.EntityChickenPigEgg;
import kleiders.jurisretro.entities.EntityIceSpell;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.net.packet.Packet;
import net.minecraft.core.net.packet.Packet23VehicleSpawn;
import net.minecraft.server.entity.EntityTrackerEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityTrackerEntry.class)
public class EntityTrackerEntryMixin {
	@Shadow
	public Entity trackedEntity;

	@Inject(method = "getSpawnPacket", remap = false, at = @At("HEAD"), cancellable = true)
	public void interact(CallbackInfoReturnable<Packet> cir) {
		if (this.trackedEntity instanceof EntityChickenPigEgg) {
			EntityLiving entityliving;
			entityliving = ((EntityChickenPigEgg)this.trackedEntity).owner;
			cir.setReturnValue(new Packet23VehicleSpawn(this.trackedEntity, 102, entityliving == null ? this.trackedEntity.id : entityliving.id));
		}
		if (this.trackedEntity instanceof EntityIceSpell) {
			EntityLiving entityliving;
			entityliving = ((EntityIceSpell)this.trackedEntity).owner;
			cir.setReturnValue(new Packet23VehicleSpawn(this.trackedEntity, 103, entityliving == null ? this.trackedEntity.id : entityliving.id));
		}
	}
}
