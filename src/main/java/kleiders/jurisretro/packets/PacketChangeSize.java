package kleiders.jurisretro.packets;

import com.mojang.nbt.CompoundTag;
import kleiders.jurisretro.interfaces.KleidersNetHandlerPackets;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketChangeSize extends Packet {
	public int entityId;
	public boolean toChicken;

	public PacketChangeSize() {
	}

	public PacketChangeSize(Entity entity, boolean toChicken) {
		this.entityId = entity.id;
		this.toChicken = toChicken;
	}


	public void readPacketData(DataInputStream dis) throws IOException {
		this.entityId = dis.readInt();
		this.toChicken = dis.readBoolean();
	}

	public void writePacketData(DataOutputStream dos) throws IOException {
		dos.writeInt(this.entityId);
		dos.writeBoolean(this.toChicken);
	}

	public void processPacket(NetHandler netHandler) {
		((KleidersNetHandlerPackets) netHandler).handleChangeSize(this);
	}

	public int getPacketSize() {
		return 34;
	}
}
