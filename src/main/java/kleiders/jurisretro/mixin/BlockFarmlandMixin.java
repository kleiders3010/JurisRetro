package kleiders.jurisretro.mixin;

import kleiders.jurisretro.JurisRetroModItems;
import net.minecraft.core.block.BlockFarmland;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value=BlockFarmland.class, remap = false)
public class BlockFarmlandMixin {

	@Inject(method = "onEntityWalking", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/item/ItemStack;getItem()Lnet/minecraft/core/item/Item;"), cancellable = true)
	public void checkCustomBoots(World world, int x, int y, int z, Entity entity, CallbackInfo ci) {
		if (((EntityPlayer)entity).inventory.armorInventory[0] != null && ((EntityPlayer)entity).inventory.armorInventory[0].getItem() == JurisRetroModItems.spider_boots) {
			ci.cancel();
		}
	}
}
