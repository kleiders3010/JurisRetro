package kleiders.jurisretro;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSound;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.CreativeHelper;

import static kleiders.jurisretro.JurisRetroMod.MOD_ID;
import static net.minecraft.core.block.Block.planksOak;
import static net.minecraft.core.item.tool.ItemToolPickaxe.miningLevels;

public class JurisRetroModBlocks {

	private int nextBlockID(String itemName) {
		return JurisRetroModConfig.cfg.getInt("Block IDs." + itemName);
	}

	//public static Block juice_press;

	private void initializeBlockDetails() {
		//miningLevels.put(juice_press, 0);
		//CreativeHelper.setParent(juice_press, planksOak);
	}
	public void initializeBlocks() {
		initializeBlockDetails();
	}
}
