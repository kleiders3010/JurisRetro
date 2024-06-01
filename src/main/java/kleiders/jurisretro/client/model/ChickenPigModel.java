package kleiders.jurisretro.client.model;

import net.minecraft.client.render.model.Cube;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.core.util.helper.MathHelper;

public class ChickenPigModel extends ModelBase {
	public Cube head;
	public Cube body;
	public Cube rightLeg;
	public Cube leftLeg;
	public Cube rightWing;
	public Cube leftWing;
	public Cube nose;
	public Cube chin;

	public ChickenPigModel() {
		byte byte0 = 16;
		this.head = new Cube(0, 0);
		this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
		this.head.setRotationPoint(0.0F, (float)(-1 + byte0), -4.0F);
		this.nose = new Cube(16, 16);
		this.nose.addBox(-2.0F, -4.0F, -3.0F, 4, 3, 1);
		this.nose.setRotationPoint(0.0F, (float)(-1 + byte0), -4.0F);
		this.chin = new Cube(24, 4);
		this.chin.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 2, 0.0F);
		this.chin.setRotationPoint(0.0F, (float)(-1 + byte0), -4.0F);
		this.body = new Cube(28, 8);
		this.body.addBox(-5.0F, 0.0F, -7.0F, 10, 16, 8, 0.0F);
		this.body.setRotationPoint(0.0F, (float)(25), -7.0F);
		this.rightLeg = new Cube(36, 0);
		this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
		this.rightLeg.setRotationPoint(-2.0F, (float)(3 + byte0), 1.0F);
		this.leftLeg = new Cube(36, 0);
		this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
		this.leftLeg.setRotationPoint(1.0F, (float)(3 + byte0), 1.0F);
		this.rightWing = new Cube(0, 13);
		this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
		this.rightWing.setRotationPoint(-4.0F, (float)(-2 + byte0), 0.0F);
		this.leftWing = new Cube(0, 13);
		this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
		this.leftWing.setRotationPoint(4.0F, (float)(-2 + byte0), 0.0F);
	}

	public void render(float limbSwing, float limbYaw, float limbPitch, float headYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbYaw, limbPitch, headYaw, headPitch, scale);
		this.head.render(scale);
		this.nose.render(scale);
		this.chin.render(scale);
		this.body.render(scale * 0.6f);
		this.rightLeg.render(scale);
		this.leftLeg.render(scale);
		this.rightWing.render(scale);
		this.leftWing.render(scale);
	}

	public void setRotationAngles(float limbSwing, float limbYaw, float limbPitch, float headYaw, float headPitch, float scale) {
		this.head.rotateAngleX = headPitch / 57.29578F;
		this.head.rotateAngleY = headYaw / 57.29578F;
		this.nose.rotateAngleX = this.head.rotateAngleX;
		this.nose.rotateAngleY = this.head.rotateAngleY;
		this.chin.rotateAngleX = this.head.rotateAngleX;
		this.chin.rotateAngleY = this.head.rotateAngleY;
		this.body.rotateAngleX = 1.570796F;
		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbYaw;
		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbYaw;
		this.rightWing.rotateAngleZ = limbPitch;
		this.leftWing.rotateAngleZ = -limbPitch;
	}
}
