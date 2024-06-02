package kleiders.jurisretro.mixin;

import kleiders.jurisretro.JurisRetroMod;
import kleiders.jurisretro.interfaces.KleidersEntityExtensions;
import kleiders.jurisretro.interfaces.KleidersNetHandlerPackets;
import kleiders.jurisretro.packets.PacketChangeData;
import kleiders.jurisretro.packets.PacketChangeSize;
import kleiders.jurisretro.packets.PacketRideEntity;
import net.minecraft.client.net.handler.NetClientHandler;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.net.handler.NetHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NetClientHandler.class)
public abstract class KleidersNetClientHandlerMixin implements KleidersNetHandlerPackets {
	@Shadow
	public abstract Entity getEntityByID(int i);

	@Unique
	@Override
	public void handleChangeData(PacketChangeData packetChangeData) {
		Entity entity = this.getEntityByID(packetChangeData.entityId);
		if (entity != null) {
			((KleidersEntityExtensions)entity).setExtraCustomData(packetChangeData.tag);
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
