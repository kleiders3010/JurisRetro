package kleiders.jurisretro.entities;

import kleiders.jurisretro.JurisRetroModItems;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.animal.EntityAnimal;
import net.minecraft.core.entity.animal.EntityChicken;
import net.minecraft.core.item.Item;
import net.minecraft.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityChickenPig extends EntityChicken {
	public List<WeightedRandomLootObject> altMobDrops = new ArrayList();
	public List<WeightedRandomLootObject> burningMobDrops = new ArrayList();

	public EntityChickenPig(World world) {
		super(world);
		this.setSize(0.4F, 0.8F);
		this.altMobDrops.add(new WeightedRandomLootObject(Item.foodPorkchopRaw.getDefaultStack(), 0, 1));
		this.altMobDrops.add(new WeightedRandomLootObject(Item.featherChicken.getDefaultStack(), 0, 1));
		this.burningMobDrops.add(new WeightedRandomLootObject(Item.foodPorkchopCooked.getDefaultStack(), 0, 1));

	}

	@Override
	public void onLivingUpdate() {
		if (!this.world.isClientSide && --this.timeUntilNextEgg <= 5) {
			this.world.playSoundAtEntity((Entity)null, this, "mob.chickenplop", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			this.spawnAtLocation(JurisRetroModItems.chicken_pig_egg.id, 1);
			this.timeUntilNextEgg = this.random.nextInt(6000) + 6000;
		}
		super.onLivingUpdate();
	}
	@Override
	protected String getHurtSound() {
		return "mob.pig";
	}

	@Override
	protected String getDeathSound() {
		return "mob.pigdeath";
	}

	@Override
	protected List<WeightedRandomLootObject> getMobDrops() {
		return this.remainingFireTicks > 0 ? this.burningMobDrops : this.altMobDrops;
	}

	@Override
	public String getEntityTexture() {
		return "/assets/jurisretro/textures/entities/chicken_pig.png";
	}

	@Override
	public String getDefaultEntityTexture() {
		return "/assets/jurisretro/textures/entities/chicken_pig.png";
	}
}
