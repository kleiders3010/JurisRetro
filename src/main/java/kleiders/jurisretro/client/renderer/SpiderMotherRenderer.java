package kleiders.jurisretro.client.renderer;

import kleiders.jurisretro.client.model.ModelSpiderMother;
import kleiders.jurisretro.entities.EntitySpiderMother;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.entity.SpiderRenderer;
import net.minecraft.client.render.model.ModelSpider;
import net.minecraft.core.Global;
import net.minecraft.core.entity.monster.EntitySpider;
import org.lwjgl.opengl.GL11;

public class SpiderMotherRenderer extends LivingRenderer<EntitySpiderMother> {
	public SpiderMotherRenderer() {
		super(new ModelSpiderMother(0.0F), 1.0F);
		this.setRenderPassModel(new ModelSpiderMother(0.01F));
	}

	protected float setSpiderDeathMaxRotation(EntitySpiderMother spider) {
		return 180.0F;
	}

	protected boolean setSpiderEyeBrightness(EntitySpiderMother spider, int renderPass, float partialTick) {
		if (renderPass == 0) {
			boolean useVariants = (Boolean) Minecraft.getMinecraft(this).gameSettings.mobVariants.value;
			this.loadTexture("/assets/minecraft/textures/entity/spider/eyes/" + (useVariants ? spider.getSkinVariant() : 0) + ".png");
			float brightness = spider.getBrightness(1.0F);
			if (Global.accessor.isFullbrightEnabled()) {
				brightness = 1.0F;
			}

			float f1 = (1.0F - brightness) * 0.5F;
			GL11.glEnable(3042);
			GL11.glDisable(3008);
			GL11.glBlendFunc(770, 771);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
			return true;
		} else {
			return false;
		}
	}

	protected float getDeathMaxRotation(EntitySpiderMother entity) {
		return this.setSpiderDeathMaxRotation(entity);
	}

	protected boolean shouldRenderPass(EntitySpiderMother entity, int renderPass, float partialTick) {
		return this.setSpiderEyeBrightness(entity, renderPass, partialTick);
	}
}

