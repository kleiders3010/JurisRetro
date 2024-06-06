package kleiders.jurisretro.client.renderer;

import kleiders.jurisretro.entities.EntityIceZombie;
import net.minecraft.client.render.block.model.BlockModel;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.model.ModelZombie;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class IceZombieRenderer extends MobRenderer<EntityIceZombie> {
	public IceZombieRenderer() {
		super(new ModelZombie(), 0.5F);
	}

	@Override
	protected void renderEquippedItems(EntityIceZombie entity, float partialTick) {
		this.renderSpecials(entity, partialTick);
	}

	protected void renderSpecials(EntityIceZombie entity, float partialTick) {
		ItemStack itemstack = null;
		if (entity.hasIceCube()) {
			itemstack = new ItemStack(Block.ice);
		}
		if (itemstack != null && itemstack.getItem().id < Block.blocksList.length) {
			GL11.glPushMatrix();
			this.modelBipedMain.bipedHead.postRender(0.0625F);
			if (((BlockModel) BlockModelDispatcher.getInstance().getDispatch(Block.blocksList[itemstack.itemID])).shouldItemRender3d()) {
				float f1 = 0.625F;
				GL11.glTranslatef(0.0F, -0.25F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(f1, -f1, f1);
			}

			ItemModelDispatcher.getInstance().getDispatch(itemstack).renderItem(Tessellator.instance, this.renderDispatcher.itemRenderer, entity, itemstack);
			GL11.glPopMatrix();
		}
	}
}
