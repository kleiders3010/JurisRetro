package kleiders.jurisretro.interfaces;

import kleiders.jurisretro.packets.PacketChangeData;
import kleiders.jurisretro.packets.PacketChangeSize;
import kleiders.jurisretro.packets.PacketRideEntity;

public interface NetHandlerPacketsExtensions {

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
