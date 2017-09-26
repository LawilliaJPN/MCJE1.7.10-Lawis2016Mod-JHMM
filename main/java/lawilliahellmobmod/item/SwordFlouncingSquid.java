package lawilliahellmobmod.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class SwordFlouncingSquid extends ItemSword{
	public SwordFlouncingSquid(ToolMaterial toolMaterial) {
		super(toolMaterial);
	}

	public boolean hitEntity(ItemStack item, EntityLivingBase entity, EntityLivingBase player){
		World world = entity.worldObj;
		int x = (int)entity.posX;
		int y = (int)entity.posY;
		int z = (int)entity.posZ;
		int wy = 0;

		for(int ay = 5; ay < 10 ; ay++){
        	if(world.isAirBlock(x, y + ay, z) && world.isAirBlock(x, y + ay + 1, z)){
        		wy = y + ay;
        		break;
        	}
        }
		if (wy == 0){
			return true;
		} else {
			entity.setPositionAndUpdate(x + 0.5D, wy, z + 0.5D);
		}

		return true;
    }
}