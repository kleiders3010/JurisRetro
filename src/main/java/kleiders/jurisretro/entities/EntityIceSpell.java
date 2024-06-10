package kleiders.jurisretro.entities;

import kleiders.jurisretro.JurisRetroModItems;
import kleiders.jurisretro.interfaces.EntityExtensions;
import net.minecraft.core.HitResult;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.projectile.EntityProjectile;
import net.minecraft.core.net.packet.Packet63SpawnParticleEffect;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;
import net.minecraft.server.MinecraftServer;

import java.util.Random;

public class EntityIceSpell extends EntityProjectile {
	public EntityIceSpell(World world) {
		super(world);
		this.modelItem = Block.ice.getDefaultStack().getItem();
	}

	@Override
	protected void init() {
		this.damage = 2;
		this.defaultGravity = 0.03F;
		this.defaultProjectileSpeed = 0.99F;
	}

	public EntityIceSpell(World world, EntityLiving entityliving) {
		super(world, entityliving);
		this.modelItem = Block.ice.getDefaultStack().getItem();
	}

	public EntityIceSpell(World world, double d, double d1, double d2) {
		super(world, d, d1, d2);
		this.modelItem = Block.ice.getDefaultStack().getItem();
	}

	@Override
	public void tick() {
		super.tick();
		Random random = new Random();
		for (int index0 = 0; index0 < 5; index0++) {
			if (!this.world.isClientSide && MinecraftServer.getInstance() != null) {
				MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(new Packet63SpawnParticleEffect("snowshovel",
					this.x + (random.nextFloat() * 0.3) - (random.nextFloat() * 0.3), this.y + 0.1 + (random.nextFloat() * 0.3) - (random.nextFloat() * 0.3),
					this.z + (random.nextFloat() * 0.3) - (random.nextFloat() * 0.3), 0, -0.06, 0, 0), world.dimension.id);
			} else {
				this.world.spawnParticle("snowshovel",
					this.x + (random.nextFloat() * 0.3) - (random.nextFloat() * 0.3), this.y + 0.1 + (random.nextFloat() * 0.3) - (random.nextFloat() * 0.3),
					this.z + (random.nextFloat() * 0.3) - (random.nextFloat() * 0.3), 0, -0.06, 0, 0);
			}
		}
	}

	public void onHit(HitResult hitResult) {
		if (hitResult.entity != null) {
			if (hitResult.entity.hurt(this.owner, this.damage, DamageType.COMBAT)) {
				((EntityExtensions) hitResult.entity).getExtraCustomData().putDouble("iceSlowness", 150);
				((EntityExtensions) hitResult.entity).syncExtraCustomData();
				if (hitResult.entity instanceof EntityIceZombie) {
					((EntityExtensions) hitResult.entity).getExtraCustomData().putDouble("iceTicks", 300);
					((EntityExtensions) hitResult.entity).syncExtraCustomData();
				}
			}
		}
		Random random = new Random();
		for (int index0 = 0; index0 < 50; index0++) {
			if (!this.world.isClientSide && MinecraftServer.getInstance() != null) {
				MinecraftServer.getInstance().playerList.sendPacketToAllPlayersInDimension(new Packet63SpawnParticleEffect("snowshovel",
					this.x + (random.nextFloat() * 0.55) - (random.nextFloat() * 0.55), this.y + 0.1 + (random.nextFloat() * 0.55) - (random.nextFloat() * 0.55),
					this.z + (random.nextFloat() * 0.55) - (random.nextFloat() * 0.55), (random.nextFloat() * 0.05) - (random.nextFloat() * 0.05), -0.05, (random.nextFloat() * 0.05) - (random.nextFloat() * 0.05), 0), world.dimension.id);
			} else {
				this.world.spawnParticle("snowshovel",
					this.x + (random.nextFloat() * 0.55) - (random.nextFloat() * 0.55), this.y + 0.1 + (random.nextFloat() * 0.55) - (random.nextFloat() * 0.55),
					this.z + (random.nextFloat() * 0.55) - (random.nextFloat() * 0.55), (random.nextFloat() * 0.05) - (random.nextFloat() * 0.05), -0.05, (random.nextFloat() * 0.05) - (random.nextFloat() * 0.05), 0);
			}
		}
		this.remove();
	}
}
