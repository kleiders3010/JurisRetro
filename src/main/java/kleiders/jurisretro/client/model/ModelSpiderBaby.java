package kleiders.jurisretro.client.model;

import net.minecraft.client.render.model.Cube;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.core.util.helper.MathHelper;

public class ModelSpiderBaby extends ModelBase {
	public Cube spiderHead;
	public Cube spiderNeck;
	public Cube spiderBody;
	public Cube spiderLeg1;
	public Cube spiderLeg2;
	public Cube spiderLeg3;
	public Cube spiderLeg4;
	public Cube spiderLeg5;
	public Cube spiderLeg6;
	public Cube spiderLeg7;
	public Cube spiderLeg8;

	public ModelSpiderBaby(float expandAmount) {
		int i = 230;
		this.spiderHead = new Cube(32, 4);
		this.spiderHead.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, expandAmount);
		this.spiderHead.setRotationPoint(0.0F, (float)(0 + i), -3.0F);
		this.spiderNeck = new Cube(0, 0);
		this.spiderNeck.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, expandAmount);
		this.spiderNeck.setRotationPoint(0.0F, (float)i, 0.0F);
		this.spiderBody = new Cube(0, 12);
		this.spiderBody.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, expandAmount);
		this.spiderBody.setRotationPoint(0.0F,  i, 7.0F);
		this.spiderLeg1 = new Cube(18, 0);
		this.spiderLeg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, expandAmount);
		this.spiderLeg1.setRotationPoint(-4.0F, (float)(0 + i), 2.0F);
		this.spiderLeg2 = new Cube(18, 0);
		this.spiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, expandAmount);
		this.spiderLeg2.setRotationPoint(4.0F, (float)(0 + i), 2.0F);
		this.spiderLeg3 = new Cube(18, 0);
		this.spiderLeg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, expandAmount);
		this.spiderLeg3.setRotationPoint(-4.0F, (float)(0 + i), 1.0F);
		this.spiderLeg4 = new Cube(18, 0);
		this.spiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, expandAmount);
		this.spiderLeg4.setRotationPoint(4.0F, (float)(0 + i), 1.0F);
		this.spiderLeg5 = new Cube(18, 0);
		this.spiderLeg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, expandAmount);
		this.spiderLeg5.setRotationPoint(-4.0F, (float)(0 + i), 0.0F);
		this.spiderLeg6 = new Cube(18, 0);
		this.spiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, expandAmount);
		this.spiderLeg6.setRotationPoint(4.0F, (float)(0 + i), 0.0F);
		this.spiderLeg7 = new Cube(18, 0);
		this.spiderLeg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, expandAmount);
		this.spiderLeg7.setRotationPoint(-4.0F, (float)(0 + i), -1.0F);
		this.spiderLeg8 = new Cube(18, 0);
		this.spiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, expandAmount);
		this.spiderLeg8.setRotationPoint(4.0F, (float)(0 + i), -1.0F);
	}

	public void render(float limbSwing, float limbYaw, float limbPitch, float headYaw, float headPitch, float scale) {
		scale *= 0.1f;
		this.setRotationAngles(limbSwing, limbYaw, limbPitch, headYaw, headPitch, scale);
		this.spiderHead.render(scale);
		this.spiderNeck.render(scale);
		this.spiderBody.render(scale);
		this.spiderLeg1.render(scale);
		this.spiderLeg2.render(scale);
		this.spiderLeg3.render(scale);
		this.spiderLeg4.render(scale);
		this.spiderLeg5.render(scale);
		this.spiderLeg6.render(scale);
		this.spiderLeg7.render(scale);
		this.spiderLeg8.render(scale);
	}

	public void setRotationAngles(float limbSwing, float limbYaw, float limbPitch, float headYaw, float headPitch, float scale) {
		limbSwing *= 4;
		this.spiderHead.rotateAngleY = headYaw / 57.29578F;
		this.spiderHead.rotateAngleX = headPitch / 57.29578F;
		float f6 = 0.7853982F;
		this.spiderLeg1.rotateAngleZ = -f6;
		this.spiderLeg2.rotateAngleZ = f6;
		this.spiderLeg3.rotateAngleZ = -f6 * 0.74F;
		this.spiderLeg4.rotateAngleZ = f6 * 0.74F;
		this.spiderLeg5.rotateAngleZ = -f6 * 0.74F;
		this.spiderLeg6.rotateAngleZ = f6 * 0.74F;
		this.spiderLeg7.rotateAngleZ = -f6;
		this.spiderLeg8.rotateAngleZ = f6;
		float f7 = -0.0F;
		float f8 = 0.3926991F;
		this.spiderLeg1.rotateAngleY = f8 * 2.0F + f7;
		this.spiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
		this.spiderLeg3.rotateAngleY = f8 * 1.0F + f7;
		this.spiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
		this.spiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
		this.spiderLeg6.rotateAngleY = f8 * 1.0F - f7;
		this.spiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
		this.spiderLeg8.rotateAngleY = f8 * 2.0F - f7;
		float pair1Y = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbYaw;
		float pair2Y = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 3.141593F) * 0.4F) * limbYaw;
		float pair3Y = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 1.570796F) * 0.4F) * limbYaw;
		float pair4Y = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 4.712389F) * 0.4F) * limbYaw;
		float pair1Z = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbYaw;
		float pair2Z = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 3.141593F) * 0.4F) * limbYaw;
		float pair3Z = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 1.570796F) * 0.4F) * limbYaw;
		float pair4Z = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 4.712389F) * 0.4F) * limbYaw;
		Cube var10000 = this.spiderLeg1;
		var10000.rotateAngleY += pair1Y;
		var10000 = this.spiderLeg2;
		var10000.rotateAngleY += -pair1Y;
		var10000 = this.spiderLeg3;
		var10000.rotateAngleY += pair2Y;
		var10000 = this.spiderLeg4;
		var10000.rotateAngleY += -pair2Y;
		var10000 = this.spiderLeg5;
		var10000.rotateAngleY += pair3Y;
		var10000 = this.spiderLeg6;
		var10000.rotateAngleY += -pair3Y;
		var10000 = this.spiderLeg7;
		var10000.rotateAngleY += pair4Y;
		var10000 = this.spiderLeg8;
		var10000.rotateAngleY += -pair4Y;
		var10000 = this.spiderLeg1;
		var10000.rotateAngleZ += pair1Z;
		var10000 = this.spiderLeg2;
		var10000.rotateAngleZ += -pair1Z;
		var10000 = this.spiderLeg3;
		var10000.rotateAngleZ += pair2Z;
		var10000 = this.spiderLeg4;
		var10000.rotateAngleZ += -pair2Z;
		var10000 = this.spiderLeg5;
		var10000.rotateAngleZ += pair3Z;
		var10000 = this.spiderLeg6;
		var10000.rotateAngleZ += -pair3Z;
		var10000 = this.spiderLeg7;
		var10000.rotateAngleZ += pair4Z;
		var10000 = this.spiderLeg8;
		var10000.rotateAngleZ += -pair4Z;
	}
}
