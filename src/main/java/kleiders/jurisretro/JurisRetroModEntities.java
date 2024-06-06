package kleiders.jurisretro;

import kleiders.jurisretro.client.model.ChickenPigModel;
import kleiders.jurisretro.client.renderer.IceZombieRenderer;
import kleiders.jurisretro.client.renderer.SpiderBabyRenderer;
import kleiders.jurisretro.client.renderer.SpiderMotherRenderer;
import kleiders.jurisretro.entities.*;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.client.render.entity.ChickenRenderer;
import net.minecraft.client.render.entity.SnowballRenderer;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.achievement.stat.StatMob;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.EntityHelper;

public class JurisRetroModEntities {

	public void initializeEntities() {
		EntityHelper.createEntity(EntityChickenPigEgg.class, JurisRetroModConfig.entityID + 1, "ChickenPigEgg", () -> new SnowballRenderer(JurisRetroModItems.chicken_pig_egg));

		EntityHelper.createEntity(EntityChickenPig.class, JurisRetroModConfig.entityID, "ChickenPig", () -> new ChickenRenderer(new ChickenPigModel(), 0.3F));
		MobInfoRegistry.register(EntityChickenPig.class, "section.jurisretro.chicken_pig.name", "section.jurisretro.chicken_pig.desc", 4, 10,
			new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(Item.foodPorkchopRaw.getDefaultStack(), 1f, 0, 1),
				new MobInfoRegistry.MobDrop(Item.featherChicken.getDefaultStack(), 1f, 0, 1)});
		StatList.mobEncounterStats.put("ChickenPig", new StatMob(0x1050000 + EntityDispatcher.getEntityID(EntityChickenPig.class), "stat.encounterMob", "ChickenPig").registerStat());

		EntityHelper.createEntity(EntitySpiderMother.class, JurisRetroModConfig.entityID + 2, "SpiderMother", () -> new SpiderMotherRenderer());
		MobInfoRegistry.register(EntitySpiderMother.class, "section.jurisretro.spider_mother.name", "section.jurisretro.spider_mother.desc", 30, 300,
			new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(Item.leather.getDefaultStack(), 1f, 0, 1),
				new MobInfoRegistry.MobDrop(Item.string.getDefaultStack(), 1f, 0, 2)});
		StatList.mobEncounterStats.put("SpiderMother", new StatMob(0x1050000 + EntityDispatcher.getEntityID(EntitySpiderMother.class), "stat.encounterMob", "SpiderMother").registerStat());

		EntityHelper.createEntity(EntitySpiderBaby.class, JurisRetroModConfig.entityID + 3, "SpiderBaby", () -> new SpiderBabyRenderer());
		MobInfoRegistry.register(EntitySpiderBaby.class, "section.jurisretro.spider_baby.name", "section.jurisretro.spider_baby.desc", 3, 15,
			new MobInfoRegistry.MobDrop[]{});
		StatList.mobEncounterStats.put("SpiderBaby", new StatMob(0x1050000 + EntityDispatcher.getEntityID(EntitySpiderBaby.class), "stat.encounterMob", "SpiderBaby").registerStat());

		EntityHelper.createEntity(EntityIceZombie.class, JurisRetroModConfig.entityID + 4, "IceZombie", () -> new IceZombieRenderer());
		MobInfoRegistry.register(EntityIceZombie.class, "section.jurisretro.ice_zombie.name", "section.jurisretro.ice_zombie.desc", 20, 500,
			new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(Item.cloth.getDefaultStack(), 1f, 0, 2),
				new MobInfoRegistry.MobDrop(Block.ice.getDefaultStack(), 1f, 1, 1)});
		StatList.mobEncounterStats.put("IceZombie", new StatMob(0x1050000 + EntityDispatcher.getEntityID(EntityIceZombie.class), "stat.encounterMob", "IceZombie").registerStat());

		EntityHelper.createEntity(EntityIceSpell.class, JurisRetroModConfig.entityID + 5, "IceSpell", () -> new SnowballRenderer(Block.ice.getDefaultStack().getItem()));
	}
}
