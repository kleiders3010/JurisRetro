package kleiders.jurisretro;

import kleiders.jurisretro.entities.EntityChickenPigEgg;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemEgg;
import net.minecraft.core.item.ItemPlaceable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.ItemBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class JurisRetroModItems {
	public static final String MOD_ID = JurisRetroMod.MOD_ID;

	//Spells
	public static Item air_spell;
	public static Item chicken_pig_egg;

	private int nextItemID(String itemName) {
		return JurisRetroModConfig.cfg.getInt("Item IDs." + itemName);
	}

	public void initializeItems() {
		air_spell = new ItemBuilder(MOD_ID)
			.setIcon("jurisretro:item/air_spell")
			.build(new AirSpellItem("air_spell", nextItemID("air_spell")));
		chicken_pig_egg = new ItemBuilder(MOD_ID)
			.setIcon("jurisretro:item/chicken_pig_egg")
			.build(new ItemEgg("chicken_pig_egg", nextItemID("chicken_pig_egg")) {
				@Override
				public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
					itemstack.consumeItem(entityplayer);
					world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
					if (!world.isClientSide) {
						world.entityJoinedWorld(new EntityChickenPigEgg(world, entityplayer));
					}

					return itemstack;
				}

				@Override
				public void onDispensed(ItemStack itemStack, World world, double x, double y, double z, int xOffset, int zOffset, Random random) {
					EntityChickenPigEgg egg = new EntityChickenPigEgg(world, x, y, z);
					egg.setHeading((double)xOffset, 0.1, (double)zOffset, 1.1F, 6.0F);
					world.entityJoinedWorld(egg);
				}
			});
	}


	class AirSpellItem extends Item {

		public AirSpellItem(String name, int id) {
			super(name, id);
		}

		public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
			ItemStack retVal = super.onItemRightClick(itemstack, world, entityplayer);
			itemstack.consumeItem(entityplayer);
			for (int index0 = 0; index0 < 90; index0++) {
				Random random = new Random();
				world.spawnParticle("explode", entityplayer.x + (random.nextFloat() * 3) - (random.nextFloat() * 3), entityplayer.y - 1 + (random.nextFloat() * 0.2) - (random.nextFloat() * 0.2),
					entityplayer.z + (random.nextFloat() * 3) - (random.nextFloat() * 3), 0, 0.12,0, 0);
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
}
