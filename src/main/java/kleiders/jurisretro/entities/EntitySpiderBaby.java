package kleiders.jurisretro.entities;

import net.minecraft.core.Global;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.monster.EntitySpider;
import net.minecraft.core.item.Item;
import net.minecraft.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntitySpiderBaby extends EntitySpider {
	public EntitySpiderBaby(World world) {
		super(world);
		this.moveSpeed = 0.5F;
		this.setSize(0.2F, 0.2F);
		this.attackStrength = 1;
	}

	@Override
	public int getSkinVariant() {
		int skinVariantCount = Global.accessor.getSkinVariantList().getNumSkinsForEntity(EntitySpider.class);
		return this.entityData.getByte(1) % skinVariantCount;
	}

	@Override
	protected List<WeightedRandomLootObject> getMobDrops() {
		return new ArrayList<WeightedRandomLootObject>();
	}

	@Override
	public int getMaxHealth() {
		return 3;
	}
}
