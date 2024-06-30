package kleiders.jurisretro.mixin;

import kleiders.jurisretro.entities.EntityChickenPigEgg;
import kleiders.jurisretro.entities.EntityIceSpell;
import kleiders.jurisretro.interfaces.EntityExtensions;
import kleiders.jurisretro.interfaces.NetHandlerPacketsExtensions;
import kleiders.jurisretro.packets.PacketChangeData;
import kleiders.jurisretro.packets.PacketChangeSize;
import kleiders.jurisretro.packets.PacketRideEntity;
import net.minecraft.client.net.handler.NetClientHandler;
import net.minecraft.client.world.WorldClient;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.net.packet.Packet23VehicleSpawn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = NetClientHandler.class, remap = false)
public abstract class NetClientHandlerMixin implements NetHandlerPacketsExtensions {
	@Shadow
	public abstract Entity getEntityByID(int i);
	@Shadow
	public WorldClient worldClient;

	@Inject(method = "handleVehicleSpawn", remap = false, at = @At("HEAD"), cancellable = true)
	public void interact(Packet23VehicleSpawn packet23vehiclespawn, CallbackInfo cir) {
		double xPosition = (double)packet23vehiclespawn.xPosition / 32.0;
		double yPosition = (double)packet23vehiclespawn.yPosition / 32.0;
		double zPosition = (double)packet23vehiclespawn.zPosition / 32.0;
		Entity newEntity = null;
		if (packet23vehiclespawn.type == 102) {
			newEntity = new EntityChickenPigEgg(this.worldClient, xPosition, yPosition, zPosition);
			if (newEntity != null) {
				((Entity)newEntity).serverPosX = packet23vehiclespawn.xPosition;
				((Entity)newEntity).serverPosY = packet23vehiclespawn.yPosition;
				((Entity)newEntity).serverPosZ = packet23vehiclespawn.zPosition;
				((Entity)newEntity).yRot = packet23vehiclespawn.yaw;
				((Entity)newEntity).xRot = packet23vehiclespawn.pitch;
				((Entity)newEntity).id = packet23vehiclespawn.entityId;
				this.worldClient.addEntityToWorld(packet23vehiclespawn.entityId, (Entity)newEntity);
				if (packet23vehiclespawn.ownerId > 0) {
					Entity entity;
					((Entity)newEntity).lerpMotion((double)packet23vehiclespawn.xVelocity / 8000.0, (double)packet23vehiclespawn.yVelocity / 8000.0, (double)packet23vehiclespawn.zVelocity / 8000.0);
				}
			}
			cir.cancel();
		}
		if (packet23vehiclespawn.type == 103) {
			newEntity = new EntityIceSpell(this.worldClient, xPosition, yPosition, zPosition);
			if (newEntity != null) {
				((Entity)newEntity).serverPosX = packet23vehiclespawn.xPosition;
				((Entity)newEntity).serverPosY = packet23vehiclespawn.yPosition;
				((Entity)newEntity).serverPosZ = packet23vehiclespawn.zPosition;
				((Entity)newEntity).yRot = packet23vehiclespawn.yaw;
				((Entity)newEntity).xRot = packet23vehiclespawn.pitch;
				((Entity)newEntity).id = packet23vehiclespawn.entityId;
				this.worldClient.addEntityToWorld(packet23vehiclespawn.entityId, (Entity)newEntity);
				if (packet23vehiclespawn.ownerId > 0) {
					Entity entity;
					((Entity)newEntity).lerpMotion((double)packet23vehiclespawn.xVelocity / 8000.0, (double)packet23vehiclespawn.yVelocity / 8000.0, (double)packet23vehiclespawn.zVelocity / 8000.0);
				}
			}
			cir.cancel();
		}
	}

	@Unique
	@Override
	public void handleChangeData(PacketChangeData packetChangeData) {
		Entity entity = this.getEntityByID(packetChangeData.entityId);
		if (entity != null) {
			((EntityExtensions)entity).setExtraCustomData(packetChangeData.tag);
		}
	}

	@Unique
	@Override
	public void handleRideEntity(PacketRideEntity packetRideEntity) {
		Entity passenger = this.getEntityByID(packetRideEntity.passengerId);
		Entity vehicle = this.getEntityByID(packetRideEntity.vehicleId);
		if (passenger != null) {
			passenger.startRiding(vehicle);
		}
	}

	@Unique
	@Override
	public void handleChangeSize(PacketChangeSize packetChangeSize) {
		Entity passenger = this.getEntityByID(packetChangeSize.entityId);
		if (packetChangeSize.toChicken) {
			passenger.bbWidth = 0.6f;
			passenger.bbHeight = 0.8f;
		} else {
			passenger.bbWidth = 0.6f;
			passenger.bbHeight = 1.8f;
		}
	}
}
