package kleiders.jurisretro.mixin;

import kleiders.jurisretro.entities.EntityChickenPig;
import kleiders.jurisretro.entities.EntityIceZombie;
import kleiders.jurisretro.entities.EntitySpiderMother;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = Biome.class, remap = false)
public class BiomeMixin {

	@Shadow
	protected List<SpawnListEntry> spawnableWaterCreatureList;
	@Shadow
	protected List<SpawnListEntry> spawnableCreatureList;

	@Shadow
	protected List<SpawnListEntry> spawnableMonsterList;

	@Inject(method = "<init>", remap = false, at = @At("TAIL"))
	private void addMobs(CallbackInfo ci) {
		Biome biome = (Biome) (Object) this;
		if (biome != Biomes.NETHER_NETHER) {
			spawnableMonsterList.add(new SpawnListEntry(EntityIceZombie.class, 6));
			spawnableMonsterList.add(new SpawnListEntry(EntitySpiderMother.class, 4));
			spawnableCreatureList.add(new SpawnListEntry(EntityChickenPig.class, 85));
		}
	}
}
