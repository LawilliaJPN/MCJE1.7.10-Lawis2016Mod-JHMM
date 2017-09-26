package lawilliahellmobmod.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderHellCreeper extends RenderLiving{
 	public RenderHellCreeper() {
		super(new ModelHellCreeper() , 0.5F);
	}

	//テクスチャ登録
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("justfortthehellmobmod:textures/mobs/hell_creeper.png");
	}
}