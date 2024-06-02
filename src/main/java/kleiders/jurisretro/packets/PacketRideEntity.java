package kleiders.jurisretro.packets;

import com.mojang.nbt.CompoundTag;
import kleiders.jurisretro.interfaces.KleidersNetHandlerPackets;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;
import net.minecraft.core.world.IVehicle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketRideEntity extends Packet {
	public int passengerId;
	public int vehicleId;


	public PacketRideEntity() {
	}

	public PacketRideEntity(Entity passenger, Entity vehicle) {
		this.passengerId = passenger.id;
		this.vehicleId = vehicle.id;
	}



	public void readPacketData(DataInputStream dis) throws IOException {
		this.passengerId = dis.readInt();
		this.vehicleId = dis.readInt();
	}

	public void writePacketData(DataOutputStream dos) throws IOException {
		dos.writeInt(this.passengerId);
		dos.writeInt(this.vehicleId);
	}

	public void processPacket(NetHandler netHandler) {
		((KleidersNetHandlerPackets) netHandler).handleRideEntity(this);
	}

	public int getPacketSize() {
		return 35;
	}
}

