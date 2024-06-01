package kleiders.jurisretro.entities;

import kleiders.jurisretro.JurisRetroModItems;
import net.minecraft.core.HitResult;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.animal.EntityChicken;
import net.minecraft.core.entity.projectile.EntityProjectile;
import net.minecraft.core.item.Item;
import net.minecraft.core.world.World;

public class EntityChickenPigEgg extends EntityProjectile {
	public EntityChickenPigEgg(World world) {
		super(world);
		this.modelItem = JurisRetroModItems.chicken_pig_egg;
	}

	public EntityChickenPigEgg(World world, EntityLiving entityliving) {
		super(world, entityliving);
		this.modelItem = JurisRetroModItems.chicken_pig_egg;
	}

	public EntityChickenPigEgg(World world, double d, double d1, double d2) {
		super(world, d, d1, d2);
		this.modelItem = JurisRetroModItems.chicken_pig_egg;
	}

	public void onHit(HitResult hitResult) {
		if (!this.world.isClientSide && this.random.nextInt(8) == 0) {
			byte byte0 = 1;
			if (this.random.nextInt(32) == 0) {
				byte0 = 4;
			}

			for(int k = 0; k < byte0; ++k) {
				EntityChickenPig entitychicken = new EntityChickenPig(this.world);
				entitychicken.moveTo(this.x, this.y, this.z, this.yRot, 0.0F);
				this.world.entityJoinedWorld(entitychicken);
			}
		}

		super.onHit(hitResult);
	}
}
