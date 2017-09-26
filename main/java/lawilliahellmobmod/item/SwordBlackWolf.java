package lawilliahellmobmod.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class SwordBlackWolf extends ItemSword{
	public SwordBlackWolf(ToolMaterial toolMaterial) {
		super(toolMaterial);
	}

	public boolean hitEntity(ItemStack item, EntityLivingBase entity, EntityLivingBase player){
		if(entity.isPotionActive(Potion.weakness.id)){
			entity.addPotionEffect(new PotionEffect(Potion.weakness.id, 15 * 20, 2));
		} else {
			entity.addPotionEffect(new PotionEffect(Potion.weakness.id, 5 * 20, 0));
		}
        return true;
    }
}
