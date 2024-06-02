package kleiders.jurisretro.mixin;

import kleiders.jurisretro.client.model.ChickenPigModel;
import kleiders.jurisretro.client.renderer.KleidersChickenRenderer;
import kleiders.jurisretro.interfaces.KleidersEntityExtensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.entity.ChickenRenderer;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.core.Global;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.entity.animal.EntityChicken;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.biome.Biome;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = PlayerRenderer.class, remap = false)
public class KleidersPlayerRendererMixin extends LivingRenderer<EntityPlayer> {

	public KleidersPlayerRendererMixin(ModelBase modelbase, float f) {
		super(modelbase, f);
	}

	@Inject(method = "render", remap = false, at = @At("HEAD"), cancellable = true)
	private void onRender(EntityPlayer entity, double x, double y, double z, float yaw, float partialTick, CallbackInfo ci) {
		if (((KleidersEntityExtensions) entity).getExtraCustomData().getDouble("chickenTime") > 0) {
			if (Minecraft.getMinecraft(this).thePlayer == entity) {
				y -= 1.62;//1.62;
			}
			this.mainModel = new ChickenPigModel();
			this.shadowSize = 0.3f;
			GL11.glPushMatrix();
			GL11.glScalef(1F, 1F, 1F);
			GL11.glDisable(2884);
			this.mainModel.onGround = this.getSwingProgress(entity, partialTick);
			if (this.renderPassModel != null) {
				this.renderPassModel.onGround = this.mainModel.onGround;
			}

			if (this.overlayModel != null) {
				this.overlayModel.onGround = this.mainModel.onGround;
			}

			this.mainModel.isRiding = entity.isPassenger();
			if (this.renderPassModel != null) {
				this.renderPassModel.isRiding = this.mainModel.isRiding;
			}

			if (this.overlayModel != null) {
				this.overlayModel.isRiding = this.mainModel.isRiding;
			}

			try {
				float headYawOffset = entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * partialTick;
				float headYaw = entity.yRotO + (entity.yRot - entity.yRotO) * partialTick;
				float headPitch = entity.xRotO + (entity.xRot - entity.xRotO) * partialTick;
				this.translateModel(entity, x, y, z);
				float ticksExisted = this.ticksExisted(entity, partialTick);
				this.rotateModel(entity, ticksExisted, headYawOffset, partialTick);
				float scale = 0.0625F;
				GL11.glEnable(32826);
				GL11.glScalef(-1.0F, -1.0F, 1.0F);
				this.preRenderCallback(entity, partialTick);
				GL11.glTranslatef(0.0F, -24.0F * scale - 0.0078125F, 0.0F);
				float limbYaw = entity.prevLimbYaw + (entity.limbYaw - entity.prevLimbYaw) * partialTick;
				float limbSwing = entity.limbSwing - entity.limbYaw * (1.0F - partialTick);
				if (limbYaw > 1.0F) {
					limbYaw = 1.0F;
				}

				this.loadTexture("/assets/jurisretro/textures/entities/chicken_pig.png");
				GL11.glEnable(3008);
				this.mainModel.setLivingAnimations(entity, limbSwing, limbYaw, partialTick);
				this.mainModel.render(limbSwing, limbYaw, ticksExisted, headYaw - headYawOffset, headPitch, scale);
				if (this.overlayModel != null) {
					this.overlayModel.setLivingAnimations(entity, limbSwing, limbYaw, partialTick);
					this.loadTexture(this.overlayTexture);
					this.overlayModel.render(limbSwing, limbYaw, ticksExisted, headYaw - headYawOffset, headPitch, scale);
				}

				if (this.renderPassModel != null) {
					this.renderPassModel.setLivingAnimations(entity, limbSwing, limbYaw, partialTick);
				}

				for(int renderPass = 0; renderPass < 4; ++renderPass) {
					if (this.shouldRenderPass(entity, renderPass, partialTick)) {
						this.renderPassModel.setLivingAnimations(entity, limbSwing, limbYaw, partialTick);
						this.renderPassModel.render(limbSwing, limbYaw, ticksExisted, headYaw - headYawOffset, headPitch, scale);
						GL11.glDisable(3042);
						GL11.glEnable(3008);
					}
				}

				this.renderEquippedItems(entity, partialTick);
				float f9 = entity.getBrightness(partialTick);
				if (Global.accessor.isFullbrightEnabled() || LightmapHelper.isLightmapEnabled()) {
					f9 = 1.0F;
				}

				int j = this.getColorMultiplier(entity, f9, partialTick);
				if ((j >> 24 & 255) > 0 || entity.hurtTime > 0 || entity.deathTime > 0) {
					GL11.glDisable(3553);
					GL11.glDisable(3008);
					GL11.glEnable(3042);
					GL11.glBlendFunc(770, 771);
					GL11.glDepthFunc(514);
					if (entity.hurtTime > 0 || entity.deathTime > 0) {
						GL11.glColor4f(f9, 0.0F, 0.0F, 0.4F);
						this.mainModel.render(limbSwing, limbYaw, ticksExisted, headYaw - headYawOffset, headPitch, scale);
						if (this.overlayModel != null) {
							this.overlayModel.render(limbSwing, limbYaw, ticksExisted, headYaw - headYawOffset, headPitch, scale);
						}

						for(int k = 0; k < 4; ++k) {
							if (this.shouldRenderPassB(entity, k, partialTick)) {
								GL11.glColor4f(f9, 0.0F, 0.0F, 0.4F);
								this.renderPassModel.render(limbSwing, limbYaw, ticksExisted, headYaw - headYawOffset, headPitch, scale);
							}
						}
					}

					if ((j >> 24 & 255) > 0) {
						float f10 = (float)(j >> 16 & 255) / 255.0F;
						float f11 = (float)(j >> 8 & 255) / 255.0F;
						float f12 = (float)(j & 255) / 255.0F;
						float f13 = (float)(j >> 24 & 255) / 255.0F;
						GL11.glColor4f(f10, f11, f12, f13);
						this.mainModel.render(limbSwing, limbYaw, ticksExisted, headYaw - headYawOffset, headPitch, scale);
						if (this.overlayModel != null) {
							this.overlayModel.render(limbSwing, limbYaw, ticksExisted, headYaw - headYawOffset, headPitch, scale);
						}

						for(int l = 0; l < 4; ++l) {
							if (this.shouldRenderPassB(entity, l, partialTick)) {
								GL11.glColor4f(f10, f11, f12, f13);
								this.renderPassModel.render(limbSwing, limbYaw, ticksExisted, headYaw - headYawOffset, headPitch, scale);
							}
						}
					}

					GL11.glDepthFunc(515);
					GL11.glDisable(3042);
					GL11.glEnable(3008);
					GL11.glEnable(3553);
				}

				GL11.glDisable(32826);
			} catch (Exception var24) {
				Exception exception = var24;
				exception.printStackTrace();
			}

			GL11.glEnable(2884);
			GL11.glPopMatrix();
			this.passSpecialRender(entity, x, y, z);
			ci.cancel();
		}
	}
}
