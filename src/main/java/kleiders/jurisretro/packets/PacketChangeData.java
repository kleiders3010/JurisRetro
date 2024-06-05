package kleiders.jurisretro.packets;

import com.mojang.nbt.CompoundTag;
import kleiders.jurisretro.interfaces.NetHandlerPacketsExtensions;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketChangeData extends Packet {
	public int entityId;
	public CompoundTag tag;

	public PacketChangeData() {
	}

	public PacketChangeData(Entity entity) {
		this.entityId = entity.id;
		this.tag = new CompoundTag();
	}

	public PacketChangeData(Entity entity, CompoundTag tag) {
		this.entityId = entity.id;
		this.tag = tag;
	}


	public void readPacketData(DataInputStream dis) throws IOException {
		this.entityId = dis.readInt();
		this.tag = readCompressedCompoundTag(dis);
	}

	public void writePacketData(DataOutputStream dos) throws IOException {
		dos.writeInt(this.entityId);
		writeCompressedCompoundTag(this.tag, dos);
	}

	public void processPacket(NetHandler netHandler) {
		((NetHandlerPacketsExtensions) netHandler).handleChangeData(this);
	}

	public int getPacketSize() {
		return 34;
	}
}
