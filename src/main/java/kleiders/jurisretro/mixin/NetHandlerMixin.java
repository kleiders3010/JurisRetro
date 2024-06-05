package kleiders.jurisretro.mixin;

import kleiders.jurisretro.interfaces.NetHandlerPacketsExtensions;
import kleiders.jurisretro.packets.PacketChangeData;
import kleiders.jurisretro.packets.PacketChangeSize;
import kleiders.jurisretro.packets.PacketRideEntity;
import net.minecraft.core.net.handler.NetHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NetHandler.class)
public class NetHandlerMixin implements NetHandlerPacketsExtensions {


	@Unique
	@Override
	public void handleChangeData(PacketChangeData packetChangeData) {
		((NetHandler) (Object) this).handleInvalidPacket(packetChangeData);
	}

	@Unique
	@Override
	public void handleChangeSize(PacketChangeSize packetChangeData) {
		((NetHandler) (Object) this).handleInvalidPacket(packetChangeData);
	}

	@Unique
	@Override
	public void handleRideEntity(PacketRideEntity packetRideEntity) {
		((NetHandler) (Object) this).handleInvalidPacket(packetRideEntity);
	}
}
