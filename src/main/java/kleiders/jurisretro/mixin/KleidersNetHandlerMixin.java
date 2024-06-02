package kleiders.jurisretro.mixin;

import com.mojang.nbt.CompoundTag;
import kleiders.jurisretro.interfaces.KleidersEntityExtensions;
import kleiders.jurisretro.interfaces.KleidersNetHandlerPackets;
import kleiders.jurisretro.packets.PacketChangeData;
import kleiders.jurisretro.packets.PacketChangeSize;
import kleiders.jurisretro.packets.PacketRideEntity;
import net.minecraft.core.net.handler.NetHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandler.class)
public class KleidersNetHandlerMixin implements KleidersNetHandlerPackets {


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
