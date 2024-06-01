package kleiders.jurisretro;

import com.mojang.nbt.CompoundTag;
import kleiders.jurisretro.client.model.ChickenPigModel;
import kleiders.jurisretro.entities.EntityChickenPig;
import kleiders.jurisretro.entities.EntityChickenPigEgg;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.client.render.entity.ChickenRenderer;
import net.minecraft.client.render.entity.SnowballRenderer;
import net.minecraft.client.render.model.ModelChicken;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.achievement.stat.StatMob;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class JurisRetroMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "juris_retro";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info(MOD_ID + "initialized.");
    }




	@Override
	public void beforeGameStart() {
		new JurisRetroModItems().initializeItems();
		EntityHelper.createEntity(EntityChickenPigEgg.class, JurisRetroModConfig.entityID, "ChickenPig", () -> new SnowballRenderer(JurisRetroModItems.chicken_pig_egg));
		EntityHelper.createEntity(EntityChickenPig.class, JurisRetroModConfig.entityID, "ChickenPig", () -> new ChickenRenderer(new ChickenPigModel(), 0.3F));
		MobInfoRegistry.register(EntityChickenPig.class, "section.jurisretro.chicken_pig.name", "section.jurisretro.chicken_pig.desc", 10, 20,
			new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(Item.foodPorkchopRaw.getDefaultStack(), 1f, 0, 1),
				new MobInfoRegistry.MobDrop(Item.featherChicken.getDefaultStack(), 1f, 0, 1)});
		StatList.mobEncounterStats.put("ChickenPig", new StatMob(0x1050000 + EntityDispatcher.getEntityID(EntityChickenPig.class), "stat.encounterMob", "ChickenPig").registerStat());
	}


	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
