package kleiders.jurisretro.client.renderer;

import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.entity.animal.EntityChicken;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.MathHelper;

public class KleidersChickenRenderer extends LivingRenderer<EntityPlayer> {
	public KleidersChickenRenderer(ModelBase modelbase, float f) {
		super(modelbase, f);
	}

	private void renderChicken(EntityPlayer entity, double d, double d1, double d2, float f, float f1) {
		super.render(entity, d, d1, d2, f, f1);
	}

	protected float getWingRotation(EntityPlayer entity, float f) {
		float f1 = f;
		float f2 = -f;
		return (MathHelper.sin(f1) + 1.0F) * f2;
	}

	protected float ticksExisted(EntityPlayer entity, float partialTick) {
		return this.getWingRotation(entity, partialTick);
	}

	public void render(EntityPlayer entity, double x, double y, double z, float yaw, float partialTick) {
		this.renderChicken(entity, x, y, z, yaw, partialTick);
	}

	public void doRender(Tessellator tessellator, EntityPlayer entity, double x, double y, double z, float yaw, float partialTick) {
		this.renderChicken(entity, x, y, z, yaw, partialTick);
	}
}
