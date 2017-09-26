package lawilliahellmobmod.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityNetherWitch extends EntityMob {
	private int summonTimer;
	private int setFireTick;

	public EntityNetherWitch(World world){
        super(world);
        this.experienceValue = 5;
        this.isImmuneToFire = true;
        this.stepHeight = 10.1F;

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 4.0F, 2.0D, 4.0D));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityIronGolem.class, 4.0F, 2.0D, 4.0D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityBlaze.class, 4.0F, 2.0D, 4.0D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityDarkGolem.class, 4.0F, 2.0D, 4.0D));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true));
    }

	protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
    }

	public boolean isAIEnabled(){
        return true;
    }

	public void onLivingUpdate(){
        super.onLivingUpdate();

        if (!this.onGround && this.motionY < 0.0D){
            this.motionY *= 0.8D;
            this.fallDistance = 0.0F;
        }


        if (this.summonTimer > 0){
            --this.summonTimer;
        } else {
        	int dif = this.worldObj.difficultySetting.getDifficultyId();
        	World world = this.worldObj;
        	if(world.getBlock((int)this.posX, (int)this.posY, (int)this.posZ).getMaterial().isLiquid()){
        		int r3 = 1 + new java.util.Random().nextInt(dif);
        		for (int i = 0; i < r3; i++){
            		int x = MathHelper.floor_double(this.posX);
            		int y = MathHelper.floor_double(this.boundingBox.minY) + 1 + (new java.util.Random().nextInt(4));
                    int z = MathHelper.floor_double(this.posZ);
                    for(int j = 0; j < 5; j++){
                    	setLocation(x, z);
                		if(world.isAirBlock(x, y, z) && world.isAirBlock(x, y + 1, z)){
                			int rm = 1 + new java.util.Random().nextInt(10);
            				if(rm <= 5 || world.getBlock(x, y, z).getMaterial().isLiquid()){
            					summonEntity(world, new EntityFlouncingSquid(world), x, y, z);
            				} else if(rm == 8){
            					summonEntity(world, new EntityBlaze(world), x, y, z);
            				} else {
            					summonEntity(world, new EntityNetherZombie(world), x, y, z);
            				}

                			if (!world.isRemote){

                    		}
                			break;
                		}
                    }
        		}
        		this.summonTimer = 20 * 40 * (4 - dif);

        	} else {
        		int r3 = 1 + new java.util.Random().nextInt(dif);
        		for (int i = 0; i < r3; i++){
            		int x = MathHelper.floor_double(this.posX);
            		int y = MathHelper.floor_double(this.boundingBox.minY) + 1 + (new java.util.Random().nextInt(2));
                    int z = MathHelper.floor_double(this.posZ);
                    for(int j = 0; j < 5; j++){
                    	setLocation(x, z);
                		if(world.isAirBlock(x, y, z) && world.isAirBlock(x, y + 1, z)){
                			if (!world.isRemote){
                				int rm = 1 + new java.util.Random().nextInt(10);
                				if(rm <= 6){
                					summonEntity(world, new EntityNetherZombie(world), x, y, z);
                				} else if(rm <= 8){
                					summonEntity(world, new EntityPigZombie(world), x, y, z);
                				} else {
                					summonEntity(world, new EntityBlaze(world), x, y, z);
                				}
                    		}
                			break;
                		}
                    }
        		}
        		this.summonTimer = 20 * 20 * (4 - dif);
        	}
        }

        if (this.setFireTick > 0){
            --this.setFireTick;
        } else {
        	int dif = this.worldObj.difficultySetting.getDifficultyId();
        	World world = this.worldObj;
        	int x = MathHelper.floor_double(this.posX);
    		int y = MathHelper.floor_double(this.boundingBox.minY);
            int z = MathHelper.floor_double(this.posZ);

        	if(world.isAirBlock(x, y, z)){
                world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "fire.ignite", 1.0F, 0.8F);
                world.setBlock(x, y, z, Blocks.fire);
            }
        	this.setFireTick = 20 * 5 * (4 - dif);
        }
	}

	private void setLocation(int x, int z){
		int r = new java.util.Random().nextInt(8);
		if(r == 0){
			++x;
		} else if(r == 1){
			--x;
		} else if(r == 2){
			++z;
		} else if(r == 3){
			--z;
		} else if(r == 4){
			++x; ++z;
		} else if(r == 5){
			++x; --z;
		} else if(r == 6){
			--x; ++z;
		} else if(r == 7){
			--x; --z;
		}
	}

	private void summonEntity(World world, EntityLiving entity, int x, int y, int z){
		entity.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
		entity.onSpawnWithEgg((IEntityLivingData)null);
		world.spawnEntityInWorld(entity);
	}

    protected String getLivingSound(){
        return "mob.witch.idle";
    }

    protected String getHurtSound(){
        return "mob.witch.hurt";
    }

    protected String getDeathSound(){
        return "mob.witch.death";
    }

}
