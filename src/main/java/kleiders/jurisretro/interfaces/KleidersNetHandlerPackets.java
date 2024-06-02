package kleiders.jurisretro.interfaces;

import kleiders.jurisretro.packets.PacketChangeData;
import kleiders.jurisretro.packets.PacketChangeSize;
import kleiders.jurisretro.packets.PacketRideEntity;
import net.minecraft.core.net.packet.Packet63SpawnParticleEffect;

public interface KleidersNetHandlerPackets {

	default void handleChangeData(PacketChangeData packetChangeData) {
		throw new RuntimeException("this should be overridden via mixin. what?");
	}

	default void handleChangeSize(PacketChangeSize packetChangeSize) {
		throw new RuntimeException("this should be overridden via mixin. what?");
	}

	default void handleRideEntity(PacketRideEntity packetChangeData) {
		throw new RuntimeException("this should be overridden via mixin. what?");
	}
}
