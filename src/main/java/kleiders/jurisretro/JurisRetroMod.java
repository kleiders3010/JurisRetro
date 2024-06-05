package kleiders.jurisretro;

import com.mojang.nbt.CompoundTag;
import kleiders.jurisretro.client.model.ChickenPigModel;
import kleiders.jurisretro.client.model.ModelSpiderMother;
import kleiders.jurisretro.client.renderer.SpiderBabyRenderer;
import kleiders.jurisretro.client.renderer.SpiderMotherRenderer;
import kleiders.jurisretro.entities.EntityChickenPig;
import kleiders.jurisretro.entities.EntityChickenPigEgg;
import kleiders.jurisretro.entities.EntitySpiderBaby;
import kleiders.jurisretro.entities.EntitySpiderMother;
import kleiders.jurisretro.packets.PacketChangeData;
import kleiders.jurisretro.packets.PacketChangeSize;
import kleiders.jurisretro.packets.PacketRideEntity;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.client.render.entity.ChickenRenderer;
import net.minecraft.client.render.entity.SnowballRenderer;
import net.minecraft.client.render.entity.SpiderRenderer;
import net.minecraft.client.render.model.ModelChicken;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.achievement.stat.StatMob;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.entity.monster.EntitySpider;
import net.minecraft.core.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.NetworkHelper;
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
		PacketChangeData.addIdClassMapping(216, true, false, PacketChangeData.class);
		PacketChangeData.addIdClassMapping(217, true, false, PacketRideEntity.class);
		PacketChangeSize.addIdClassMapping(218, true, false, PacketChangeSize.class);
		new JurisRetroModItems().initializeItems();

		EntityHelper.createEntity(EntityChickenPigEgg.class, JurisRetroModConfig.entityID + 1, "ChickenPigEgg", () -> new SnowballRenderer(JurisRetroModItems.chicken_pig_egg));

		EntityHelper.createEntity(EntityChickenPig.class, JurisRetroModConfig.entityID, "ChickenPig", () -> new ChickenRenderer(new ChickenPigModel(), 0.3F));
		MobInfoRegistry.register(EntityChickenPig.class, "section.jurisretro.chicken_pig.name", "section.jurisretro.chicken_pig.desc", 4, 10,
			new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(Item.foodPorkchopRaw.getDefaultStack(), 1f, 0, 1),
				new MobInfoRegistry.MobDrop(Item.featherChicken.getDefaultStack(), 1f, 0, 1)});
		StatList.mobEncounterStats.put("ChickenPig", new StatMob(0x1050000 + EntityDispatcher.getEntityID(EntityChickenPig.class), "stat.encounterMob", "ChickenPig").registerStat());

		EntityHelper.createEntity(EntitySpiderMother.class, JurisRetroModConfig.entityID + 2, "SpiderMother", () -> new SpiderMotherRenderer());
		MobInfoRegistry.register(EntitySpiderMother.class, "section.jurisretro.spider_mother.name", "section.jurisretro.spider_mother.desc", 30, 250,
			new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(Item.leather.getDefaultStack(), 1f, 0, 1),
				new MobInfoRegistry.MobDrop(Item.string.getDefaultStack(), 1f, 0, 2)});
		StatList.mobEncounterStats.put("SpiderMother", new StatMob(0x1050000 + EntityDispatcher.getEntityID(EntitySpiderMother.class), "stat.encounterMob", "SpiderMother").registerStat());

		EntityHelper.createEntity(EntitySpiderBaby.class, JurisRetroModConfig.entityID + 3, "SpiderBaby", () -> new SpiderBabyRenderer());
		MobInfoRegistry.register(EntitySpiderBaby.class, "section.jurisretro.spider_baby.name", "section.jurisretro.spider_baby.desc", 3, 5,
			new MobInfoRegistry.MobDrop[]{});
		StatList.mobEncounterStats.put("SpiderBaby", new StatMob(0x1050000 + EntityDispatcher.getEntityID(EntitySpiderBaby.class), "stat.encounterMob", "SpiderBaby").registerStat());
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
