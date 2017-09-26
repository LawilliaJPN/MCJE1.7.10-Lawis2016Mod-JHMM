package lawilliahellmobmod.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBlackWolf extends RenderLiving{
	public RenderBlackWolf() {
		super(new ModelBlackWolf(), 0.5F);
	}

	/**テクスチャを登録するメソッド*/
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("justfortthehellmobmod:textures/mobs/black_wolf.png");
	}
}
