package kleiders.jurisretro;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.util.ConfigUpdater;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JurisRetroModConfig {
	public static int entityID;
	public static ConfigUpdater updater = ConfigUpdater.fromProperties();
	private static final Toml properties = new Toml("Juros Retro Mod TOML Config");
	public static TomlConfigHandler cfg;
	static {
		properties.addCategory("Juris Retro Mod")
			.addEntry("cfgVersion", 5);

		properties.addCategory("Block IDs");
		properties.addEntry("Block IDs.startingID", 2630);
		properties.addCategory("Item IDs");
		properties.addEntry("Item IDs.startingID", 27350);
		properties.addCategory("Entity IDs");
		properties.addEntry("Entity IDs.startingID", 750);

		cfg = new TomlConfigHandler(updater, JurisRetroMod.MOD_ID, properties);

		int blockIDs = cfg.getInt("Block IDs.startingID");
		int itemIDs = cfg.getInt("Item IDs.startingID");
		entityID = cfg.getInt("Entity IDs.startingID");
		List<Field> blockFields = Arrays.stream(JurisRetroModBlocks.class.getDeclaredFields()).filter((F)-> Block.class.isAssignableFrom(F.getType())).collect(Collectors.toList());
		for (Field blockField : blockFields) {
			properties.addEntry("Block IDs." + blockField.getName(), blockIDs++);
		}
		List<Field> itemFields = Arrays.stream(JurisRetroModItems.class.getDeclaredFields()).filter((F)-> Item.class.isAssignableFrom(F.getType())).collect(Collectors.toList());
		for (Field itemField : itemFields) {
			properties.addEntry("Item IDs." + itemField.getName(), itemIDs++);
		}

		cfg = new TomlConfigHandler(updater, JurisRetroMod.MOD_ID, properties);

	}
}
