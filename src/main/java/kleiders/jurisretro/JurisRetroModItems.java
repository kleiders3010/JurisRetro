package kleiders.jurisretro;

import kleiders.jurisretro.entities.EntityChickenPigEgg;
import kleiders.jurisretro.items.AirSpellItem;
import kleiders.jurisretro.items.IceSpellItem;
import kleiders.jurisretro.items.TransformationSpellItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.*;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.ItemBuilder;

import java.util.Random;

public class JurisRetroModItems {
	public static final String MOD_ID = JurisRetroMod.MOD_ID;

	//Spells
	public static Item air_spell;
	public static Item chicken_pig_egg;
	public static Item transformation_spell;
	public static Item spider_boots;
	public static ArmorMaterial spider;
	public static Item spider_mother_leather;
	public static Item ice_spell;

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
				public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
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
		spider = ArmorMaterial.register((new ArmorMaterial(new NamespaceID("jurisretro", "spider"), 0, 240)).withProtectionPercentage(DamageType.COMBAT, 20.0F).withProtectionPercentage(DamageType.BLAST, 20.0F).withProtectionPercentage(DamageType.FIRE, 20.0F).withProtectionPercentage(DamageType.FALL, 240.0F));
		spider_boots = new ItemBuilder(MOD_ID)
			.setIcon("jurisretro:item/spider_boots")
			.build(new ItemArmor("spider_boots", nextItemID("spider_boots"), JurisRetroModItems.spider, 3)).setMaxStackSize(1);
		spider_mother_leather = new ItemBuilder(MOD_ID)
			.setIcon("jurisretro:item/spider_mother_leather")
			.build(new Item("spider_mother_leather", nextItemID("spider_mother_leather")));
		ice_spell = new ItemBuilder(MOD_ID)
			.setIcon("jurisretro:item/ice_spell")
			.build(new IceSpellItem("ice_spell", nextItemID("ice_spell")));
	}



}
