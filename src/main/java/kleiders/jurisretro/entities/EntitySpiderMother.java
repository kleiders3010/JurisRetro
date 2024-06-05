package kleiders.jurisretro.entities;

import kleiders.jurisretro.JurisRetroModItems;
import net.minecraft.core.Global;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.monster.EntitySpider;
import net.minecraft.core.item.Item;
import net.minecraft.core.world.World;

public class EntitySpiderMother extends EntitySpider {
	public EntitySpiderMother(World world) {
		super(world);
		this.moveSpeed = 0.5F;
		this.setSize(1.5F, 0.95F);
		this.attackStrength = 1;
		this.mobDrops.add(new WeightedRandomLootObject(JurisRetroModItems.spider_mother_leather.getDefaultStack(), 0, 1));
	}

	@Override
	public int getSkinVariant() {
		int skinVariantCount = Global.accessor.getSkinVariantList().getNumSkinsForEntity(EntitySpider.class);
		return this.entityData.getByte(1) % skinVariantCount;
	}


	@Override
	public void onEntityDeath() {
		super.onEntityDeath();
		if (!this.world.isClientSide) {
			byte byte0 = 13;
			byte0 += (byte) this.random.nextInt(6);
			for (int k = 0; k < byte0; ++k) {
				EntitySpiderBaby entitybabyspider = new EntitySpiderBaby(this.world);
				entitybabyspider.moveTo(this.x, this.y, this.z, this.yRot, 0.0F);
				entitybabyspider.push((this.random.nextDouble() * 0.2) - (this.random.nextDouble() * 0.2), this.random.nextDouble() * 0.3, (this.random.nextDouble() * 0.2) - (this.random.nextDouble() * 0.2));
				this.world.entityJoinedWorld(entitybabyspider);
			}
		}
	}

	@Override
	public int getMaxHealth() {
		return 30;
	}
}
