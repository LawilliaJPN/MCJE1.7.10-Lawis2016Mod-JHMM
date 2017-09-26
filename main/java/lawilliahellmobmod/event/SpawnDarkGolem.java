package lawilliahellmobmod.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lawilliahellmobmod.entity.EntityDarkGolem;
import lawilliahellmobmod.entity.EntityNetherWitch;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class SpawnDarkGolem {
	@SubscribeEvent
	public void onHurtEvent(LivingHurtEvent event) {
		EntityLivingBase entity = event.entityLiving;
		if (entity instanceof EntityIronGolem){
			int r = new java.util.Random().nextInt(20);
			int dim = entity.worldObj.provider.dimensionId;

			if(event.source.isFireDamage() && r == 0 && dim == -1){
				World world = entity.worldObj;
				double x = entity.posX;
            	double y = entity.posY;
            	double z = entity.posZ;
            	entity.setDead();

            	EntityDarkGolem entityS = new EntityDarkGolem(world);
                entityS.setLocationAndAngles(x + 0.5D, y + 0.5D, z + 0.5D, 0.0F, 0.0F);
                entityS.fallDistance =0;

                world.spawnEntityInWorld(entityS);
                entityS.spawnExplosionParticle();
			}

		} else if (entity instanceof EntityVillager){
			int dim = entity.worldObj.provider.dimensionId;

			if(event.source.isFireDamage() && dim == -1){
				World world = entity.worldObj;
				double x = entity.posX;
            	double y = entity.posY;
            	double z = entity.posZ;
            	entity.setDead();

            	EntityNetherWitch entityS = new EntityNetherWitch(world);
                entityS.setLocationAndAngles(x + 0.5D, y + 0.5D, z + 0.5D, 0.0F, 0.0F);
                entityS.fallDistance =0;

                world.spawnEntityInWorld(entityS);
                entityS.spawnExplosionParticle();
			}
		}
	}
}
