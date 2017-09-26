package lawilliahellmobmod.entity.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelFlouncingSquid extends ModelBase {
	/** The squid's body */
    ModelRenderer squidBody;
    /** The squid's tentacles */
    ModelRenderer[] squidTentacles = new ModelRenderer[8];
    private static final String __OBFID = "CL_00000861";

    public ModelFlouncingSquid(){
        byte b0 = -12;
        this.squidBody = new ModelRenderer(this, 0, 0);
        this.squidBody.addBox(-6.0F, -8.0F, -6.0F, 12, 16, 12);
        this.squidBody.rotationPointY += (float)(24 + b0);

        for (int i = 0; i < this.squidTentacles.length; ++i){
            this.squidTentacles[i] = new ModelRenderer(this, 48, 0);
            this.squidTentacles[i].addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2);

            //i本目×円周率×2÷足の数
            double d0 = (double)i * Math.PI * 2.0D / (double)this.squidTentacles.length;

            //起点のX座標
            float f = (float)Math.cos(d0) * 5.0F; //その足の起点位置の方向×中心からの距離
            this.squidTentacles[i].rotationPointX = f;

            //起点のZ座標
            float f1 = (float)Math.sin(d0) * 5.0F; //その足の起点位置の方向×中心からの距離
            this.squidTentacles[i].rotationPointZ = f1;

            //起点のY座標
            this.squidTentacles[i].rotationPointY = (float)(31 + b0);

            //触手の向き
            d0 = (double)i * Math.PI * -2.0D / (double)this.squidTentacles.length + (Math.PI / 2D);
            this.squidTentacles[i].rotateAngleY = (float)d0;
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float time, float far, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_){
        ModelRenderer[] amodelrenderer = this.squidTentacles;
        int i = amodelrenderer.length;

        for (int j = 0; j < i; ++j){
            ModelRenderer modelrenderer = amodelrenderer[j];
            modelrenderer.rotateAngleX = p_78087_3_;
        }
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_){
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.squidBody.render(p_78088_7_);

        for (int i = 0; i < this.squidTentacles.length; ++i){
            this.squidTentacles[i].render(p_78088_7_);
        }
    }
}
