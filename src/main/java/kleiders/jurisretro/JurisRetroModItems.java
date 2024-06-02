package kleiders.jurisretro;

import kleiders.jurisretro.entities.EntityChickenPigEgg;
import kleiders.jurisretro.interfaces.KleidersEntityExtensions;
import kleiders.jurisretro.items.AirSpellItem;
import kleiders.jurisretro.items.TransformationSpellItem;
import kleiders.jurisretro.mixin.KleidersEntityMixin;
import net.minecraft.client.entity.player.EntityClientPlayerMP;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemEgg;
import net.minecraft.core.item.ItemPlaceable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.packet.Packet14BlockDig;
import net.minecraft.core.net.packet.Packet63SpawnParticleEffect;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;
import net.minecraft.server.MinecraftServer;
import turniplabs.halplibe.helper.ItemBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class JurisRetroModItems {
	public static final String MOD_ID = JurisRetroMod.MOD_ID;

	//Spells
	public static Item air_spell;
	public static Item chicken_pig_egg;
	public static Item transformation_spell;

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
		transformation_spell = new ItemBuilder(MOD_ID)
			.setIcon("jurisretro:item/transformation_spell")
			.build(new TransformationSpellItem("transformation_spell", nextItemID("transformation_spell")));
	}



}
