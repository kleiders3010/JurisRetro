package kleiders.jurisretro.items;

import net.minecraft.core.Global;
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

public class AirSpellItem extends Item {

	public AirSpellItem(String name, int id) {
		super(name, id);
	}

	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		ItemStack retVal = super.onUseItem(itemstack, world, entityplayer);

		//world.netHandler.addToSendQueue(new Packet14BlockDig(1, x, y, z, side));

		itemstack.consumeItem(entityplayer);
		for (int index0 = 0; index0 < 90; index0++) {
			Random random = new Random();
			if (Global.isServer && MinecraftServer.getInstance() != null) {
				MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(
				    new Packet63SpawnParticleEffect(
				        "explode",
				        entityplayer.x + (random.nextFloat() * 3) - (random.nextFloat() * 3),
				        entityplayer.y - 0.25 + (random.nextFloat() * 0.2) - (random.nextFloat() * 0.2),
					    entityplayer.z + (random.nextFloat() * 3) - (random.nextFloat() * 3), 0, 0.12, 0, 0),
					    world.dimension.id
					);
			} else {
				world.spawnParticle(
				    "explode",
				    entityplayer.x + (random.nextFloat() * 3) - (random.nextFloat() * 3),
				    entityplayer.y - 1 + (random.nextFloat() * 0.2) - (random.nextFloat() * 0.2),
					entityplayer.z + (random.nextFloat() * 3) - (random.nextFloat() * 3),
					0, 0.12, 0, 0
				);
			}
		}
		world.playSoundEffect(entityplayer, SoundCategory.ENTITY_SOUNDS, entityplayer.x, entityplayer.y, entityplayer.z, "random.pop", 1, 1);
		float explosionSize2 = 5.0F;
		int x1 = MathHelper.floor_double(entityplayer.x - (double)explosionSize2 - 1.0);
		int x2 = MathHelper.floor_double(entityplayer.x + (double)explosionSize2 + 1.0);
		int y1 = MathHelper.floor_double(entityplayer.y - (double)explosionSize2 - 1.0);
		int y2 = MathHelper.floor_double(entityplayer.y + (double)explosionSize2 + 1.0);
		int z1 = MathHelper.floor_double(entityplayer.z - (double)explosionSize2 - 1.0);
		int z2 = MathHelper.floor_double(entityplayer.z + (double)explosionSize2 + 1.0);
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entityplayer, AABB.getBoundingBoxFromPool((double)x1, (double)y1, (double)z1, (double)x2, (double)y2, (double)z2));
		Vec3d vec3d = Vec3d.createVector(entityplayer.x, entityplayer.y, entityplayer.z);
		Iterator var10 = list.iterator();

		while(var10.hasNext()) {
			Entity entity = (Entity)var10.next();
			double d4 = entity.distanceTo(entityplayer.x, entityplayer.y, entityplayer.z) / (double)explosionSize2;
			if (d4 <= 1.0) {
				entity.hurtMarked = true;
				/*
				((KleidersEntityExtensions) entity).getExtraCustomData().putDouble("winds", ((KleidersEntityExtensions) entity).getExtraCustomData().getDouble("winds") + 1);
				((KleidersEntityExtensions) entity).syncExtraCustomData();
				*/
				double d6 = entity.x - entityplayer.x;
				double d8 = entity.y - (entityplayer.y - 3);
				double d10 = entity.z - entityplayer.z;
				double d11 = (double)MathHelper.sqrt_double(d6 * d6 + d8 * d8 + d10 * d10);
				d6 /= d11;
				d8 /= d11;
				d10 /= d11;
				double d12 = (double)world.func_675_a(vec3d, entity.bb);
				double d13 = (1.0 - d4) * d12;
				double d14 = d13;
				entity.xd += (d6 * d14) * 2.5;
				entity.yd += (d8 * d14) * 1.5;
				entity.yd += 0.8;
				entity.zd += (d10 * d14) * 2.5;
			}
		}
		return retVal;
	}
}
