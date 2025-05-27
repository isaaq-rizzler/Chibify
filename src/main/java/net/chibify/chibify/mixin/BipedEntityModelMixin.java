package net.chibify.chibify.mixin;

import net.chibify.chibify.Chibify;
import net.chibify.chibify.config.ModConfig;
import net.chibify.chibify.mixininterface.IEntityRenderState;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends BipedEntityRenderState> extends EntityModel<T> implements ModelWithArms, ModelWithHead {
    @Shadow
    @Final
    public ModelPart head;
    @Shadow
    @Final
    public ModelPart hat;
    @Shadow
    @Final
    public ModelPart body;
    @Shadow
    @Final
    public ModelPart leftArm;
    @Shadow
    @Final
    public ModelPart rightArm;
    @Shadow
    @Final
    public ModelPart leftLeg;
    @Shadow
    @Final
    public ModelPart rightLeg;

    protected BipedEntityModelMixin(ModelPart root) {
        super(root);
    }

    @Inject(
            method = "setAngles(Lnet/minecraft/client/render/entity/state/BipedEntityRenderState;)V",
            at = {@At("TAIL")}
    )
    private void test(T bipedEntityRenderState, CallbackInfo ci) {
        Entity entity = ((IEntityRenderState) bipedEntityRenderState).chibify$getEntity();
        if (!(entity instanceof LivingEntity livingEntity) || (entity == Chibify.mc.player && !ModConfig.INSTANCE.shrinkSelf)) return;
        if (livingEntity instanceof PlayerEntity) {
//            this.head.pitch += 5.4f;

            this.head.yScale = 1.0F;
            this.head.xScale = 1.0F;
            this.head.zScale = 1.0F;
            this.head.pivotY = 12.0F;

            this.rightArm.yScale = 0.5F;
            this.rightArm.pivotY = 13.0F;
            this.rightArm.pivotX += 1.4f;
            this.rightArm.zScale = 0.7f;
            this.rightArm.xScale = 0.7f;

            this.leftArm.yScale = 0.5F;
            this.leftArm.pivotY = 13.0F;
            this.leftArm.zScale = 0.7f;
            this.leftArm.xScale = 0.7f;
            this.leftArm.pivotX -= 1.4f;

            this.leftLeg.yScale = 0.5F;
            this.leftLeg.pivotY = 18.0F;
            this.leftLeg.zScale = 0.7f;
            this.leftLeg.xScale = 0.7f;
            this.leftLeg.pivotX -= 0.6f;

            this.rightLeg.pivotY = 18.0F;
            this.rightLeg.yScale = 0.5F;
            this.rightLeg.zScale = 0.7f;
            this.rightLeg.xScale = 0.7f;
            this.rightLeg.pivotX += 0.6f;

            this.body.pivotY = 12.0F;
            this.body.yScale = 0.5f;
            this.body.zScale = 0.7f;
            this.body.xScale = 0.7f;


            this.hat.pivotY = 0.0F;

            if (livingEntity.isInSneakingPose()) {

                this.body.pitch = 0.5F;
//                this.rightLeg.pivotZ += 4.0f;
//                this.leftLeg.pivotZ += 4.0f;

//                ModelPart var20 = this.rightArm;
//                var20.pitch += 0.4F;
//                var20 = this.leftArm;
//                var20.pitch += 0.4F;
//                var20 = this.rightLeg;
//                var20.pivotZ += 4.0F;
//                var20 = this.leftLeg;
//                var20.pivotZ += 4.0F;
//                var20 = this.head;
//                var20.pivotY += 4.2F;
//                var20 = this.body;
//                var20.pivotY += 1.2F;
//                var20 = this.leftArm;
//                var20.pivotY += 3.2F;
//                var20 = this.rightArm;
//                var20.pivotY += 3.2F;
            }
        }
    }
}
