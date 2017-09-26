package lawilliahellmobmod.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderNetherWitch extends RenderLiving{
	public RenderNetherWitch() {
		super(new ModelNetherWitch(0.0F), 0.5F);
	}

	//テクスチャ登録
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("justfortthehellmobmod:textures/mobs/nether_witch.png");
	}
}
