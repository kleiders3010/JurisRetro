package kleiders.jurisretro.entities;

import com.mojang.nbt.CompoundTag;
import kleiders.jurisretro.interfaces.EntityExtensions;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.monster.EntityZombie;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.season.Seasons;
import net.minecraft.core.world.weather.Weather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityIceZombie extends EntityZombie {
	public List<WeightedRandomLootObject> altMobDrops = new ArrayList();

	public EntityIceZombie(World world) {
		super(world);
		this.setIceCube(true);
		this.altMobDrops.add(new WeightedRandomLootObject(Block.ice.getDefaultStack(), 1, 1));
		this.altMobDrops.add(new WeightedRandomLootObject(Item.cloth.getDefaultStack(), 0, 2));
		((EntityExtensions) this).getExtraCustomData().putDouble("iceTicks", 300);
	}

	/*@Override
	protected void attackEntity(Entity entity, float distance) {
		if (this.attackTime <= 0 && distance < 2.0F && entity.bb.maxY > this.bb.minY && entity.bb.minY < this.bb.maxY) {
			((EntityExtensions) entity).getExtraCustomData().putDouble("iceSlowness", 100);
			((EntityExtensions) entity).syncExtraCustomData();
		}
		super.attackEntity(entity, distance);
	}*/

	@Override
	public void onLivingUpdate() {
		((EntityExtensions) this).getExtraCustomData().putDouble("iceTicks", ((EntityExtensions) this).getExtraCustomData().getDouble("iceTicks") + 1);
		if (this.world.isDaytime()) {
			float f = this.getBrightness(1.0F);
			if (f > 0.5F && this.world.canBlockSeeTheSky(MathHelper.floor_double(this.x), MathHelper.floor_double(this.y), MathHelper.floor_double(this.z)) && (this.world.getCurrentWeather() != Weather.overworldFog || this.world.weatherManager.getWeatherPower() < 0.75F)) {
				if (((EntityExtensions) this).getExtraCustomData().getDouble("iceTicks") > 0) {
					((EntityExtensions) this).getExtraCustomData().putDouble("iceTicks", ((EntityExtensions) this).getExtraCustomData().getDouble("iceTicks") - 2);
				}
			}
		}
		if (!this.hasIceCube() && ((EntityExtensions) this).getExtraCustomData().getDouble("iceTicks") > 100) {
			this.setIceCube(true);
			this.remainingFireTicks = 0;
		} else if (this.hasIceCube() && ((EntityExtensions) this).getExtraCustomData().getDouble("iceTicks") < 0) {
			this.setIceCube(false);
		}
		super.onLivingUpdate();
	}

	@Override
	public boolean getCanSpawnHere() {
		int x = (int)this.x;
		int y = (int)this.y;
		int z = (int)this.z;
		Biome biome = this.world.getBlockBiome(x, y, z);
		return super.getCanSpawnHere() && this.world.canBlockSeeTheSky(x, y, z) &&
			(biome.hasSurfaceSnow() ||
				((this.world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER || this.world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER_ENDLESS) && (this.world.getCurrentWeather() == Weather.overworldSnow || this.world.getCurrentWeather() == Weather.overworldWinterSnow)));
	}

	@Override
	protected void init() {
		super.init();
		this.entityData.define(16, (byte)0);
	}

	public boolean hasIceCube() {
		return (this.entityData.getByte(16) & 2) != 0;
	}

	public void setIceCube(boolean flag) {
		byte byte0 = this.entityData.getByte(16);
		if (flag) {
			this.entityData.set(16, (byte)(byte0 | 0b10));
		} else {
			this.entityData.set(16, (byte)(byte0 & ~0b10));
		}

	}

	@Override
	public String getEntityTexture() {
		return "/assets/jurisretro/textures/entities/cold_zombie.png";
	}

	@Override
	public String getDefaultEntityTexture() {
		return "/assets/jurisretro/textures/entities/cold_zombie.png";
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("IceCube", this.hasIceCube());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setIceCube(tag.getBoolean("IceCube"));
	}

	@Override
	protected List<WeightedRandomLootObject> getMobDrops() {
		return this.hasIceCube() ? this.altMobDrops : this.mobDrops;
	}
}
