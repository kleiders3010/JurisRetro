package kleiders.jurisretro;

import kleiders.jurisretro.client.model.ChickenPigModel;
import kleiders.jurisretro.client.renderer.IceZombieRenderer;
import kleiders.jurisretro.client.renderer.SpiderBabyRenderer;
import kleiders.jurisretro.client.renderer.SpiderMotherRenderer;
import kleiders.jurisretro.entities.*;
import kleiders.jurisretro.packets.PacketChangeData;
import kleiders.jurisretro.packets.PacketChangeSize;
import kleiders.jurisretro.packets.PacketRideEntity;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.client.render.entity.ChickenRenderer;
import net.minecraft.client.render.entity.SnowballRenderer;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.achievement.stat.StatMob;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class JurisRetroMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "jurisretro";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info(MOD_ID + "initialized.");
    }

	@Override
	public void beforeGameStart() {
		new JurisRetroModItems().initializeItems();
		PacketChangeData.addIdClassMapping(216, true, false, PacketChangeData.class);
		PacketChangeData.addIdClassMapping(217, true, false, PacketRideEntity.class);
		PacketChangeSize.addIdClassMapping(218, true, false, PacketChangeSize.class);
	}


	@Override
	public void afterGameStart() {
		new JurisRetroModEntities().initializeEntities();
	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
