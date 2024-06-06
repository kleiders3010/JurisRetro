package kleiders.jurisretro.items;

import kleiders.jurisretro.entities.EntityIceSpell;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.entity.projectile.EntitySnowball;
import net.minecraft.core.item.ItemSnowball;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.Random;

public class IceSpellItem extends ItemSnowball {
	public IceSpellItem(String name, int id) {
		super(name, id);
		this.maxStackSize = 64;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		itemstack.consumeItem(entityplayer);
		world.playSoundAtEntity(entityplayer, entityplayer, "random.pop", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!world.isClientSide) {
			world.entityJoinedWorld(new EntityIceSpell(world, entityplayer));
		}

		return itemstack;
	}

	public void onDispensed(ItemStack itemStack, World world, double x, double y, double z, int xOffset, int zOffset, Random random) {
		EntityIceSpell entitysnowball = new EntityIceSpell(world, x, y, z);
		entitysnowball.setHeading((double)xOffset, 0.1, (double)zOffset, 1.1F, 6.0F);
		world.entityJoinedWorld(entitysnowball);
	}
}
