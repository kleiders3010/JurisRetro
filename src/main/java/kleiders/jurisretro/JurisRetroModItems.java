package kleiders.jurisretro;

import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemHelper;

public class JurisRetroModItems {
	public static final String MOD_ID = JurisRetroMod.MOD_ID;

	//Spells
	public static Item fire_spell;

	private int nextItemID(String itemName) {
		return JurisRetroModConfig.cfg.getInt("Item IDs." + itemName);
	}

	public void initializeItems() {
		fire_spell = ItemHelper.createItem(MOD_ID, new Item("fire_spell",nextItemID( "fire_spell"))).setMaxStackSize(64);
	}
}
