package kleiders.jurisretro.items;

import kleiders.jurisretro.interfaces.KleidersEntityExtensions;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.packet.Packet63SpawnParticleEffect;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;
import net.minecraft.server.MinecraftServer;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TransformationSpellItem extends Item {

	public TransformationSpellItem(String name, int id) {
		super(name, id);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		ItemStack retVal = super.onItemRightClick(itemstack, world, entityplayer);

		itemstack.consumeItem(entityplayer);
		for (int index0 = 0; index0 < 90; index0++) {
			Random random = new Random();
			if (!world.isClientSide && MinecraftServer.getInstance() != null) {
				MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(new Packet63SpawnParticleEffect("explode", entityplayer.x + (random.nextFloat() * 1) - (random.nextFloat() * 1), entityplayer.y - 0.25 + (random.nextFloat() * 0.4) - (random.nextFloat() * 0.3),
					entityplayer.z + (random.nextFloat() * 1) - (random.nextFloat() * 1), 0, 0.12, 0, 0), world.dimension.id);
			} else if (MinecraftServer.getInstance() == null) {
				world.spawnParticle("explode", entityplayer.x + (random.nextFloat() * 1) - (random.nextFloat() * 1), entityplayer.y - 1 + (random.nextFloat() * 0.4) - (random.nextFloat() * 0.2),
					entityplayer.z + (random.nextFloat() * 1) - (random.nextFloat() * 1), 0, 0.12,0, 0);
			}
		}
		world.playSoundEffect(entityplayer, SoundCategory.ENTITY_SOUNDS, entityplayer.x, entityplayer.y, entityplayer.z, "mob.chicken", 1, 1);
		((KleidersEntityExtensions) entityplayer).getExtraCustomData().putDouble("chickenTime", ((KleidersEntityExtensions) entityplayer).getExtraCustomData().getDouble("chickenTime") + 1200);
		((KleidersEntityExtensions) entityplayer).syncExtraCustomData();
		return retVal;
	}
}
