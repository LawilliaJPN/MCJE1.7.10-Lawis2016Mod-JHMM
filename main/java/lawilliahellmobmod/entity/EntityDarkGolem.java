package lawilliahellmobmod.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityDarkGolem extends EntityMob{
    /** deincrements, and a distance-to-home check is done at 0 */
    private int homeCheckTimer;
    Village villageObj;
    private int attackTimer;
    private int holdTNTTick;
    private int liquidTick;
    private static final String __OBFID = "CL_00001652";

    public EntityDarkGolem(World world){
        super(world);
        this.setSize(1.4F, 2.9F);
        this.experienceValue = 20;
        this.isImmuneToFire = true;
        this.stepHeight = 1.6F;
        this.getNavigator().setAvoidsWater(true);

    	this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityTNTPrimed.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.5D, 32.0F));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityFlouncingSquid.class, 0, true));
    }

    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(250.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
    }

    protected void entityInit(){
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled(){
        return true;
    }

    /**
     * main AI tick function, replaces updateEntityActionState
     */
    //protected void updateAITick(){}

    /**
     * Decrements the entity's air supply when underwater
     */
    protected int decreaseAirSupply(int p_70682_1_){
        return p_70682_1_;
    }

    protected void collideWithEntity(Entity p_82167_1_){
        if (p_82167_1_ instanceof IMob && this.getRNG().nextInt(20) == 0){
            this.setAttackTarget((EntityLivingBase)p_82167_1_);
        }

        super.collideWithEntity(p_82167_1_);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate(){
        super.onLivingUpdate();

        if (this.attackTimer > 0){
            --this.attackTimer;
        }

        if (this.holdTNTTick > 0){
            --this.holdTNTTick;
        }

        if (this.liquidTick > 0){
        	--this.liquidTick;
        }

        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0){
            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset);
            int k = MathHelper.floor_double(this.posZ);
            Block block = this.worldObj.getBlock(i, j, k);

            if (block.getMaterial() != Material.air){
                this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(block) + "_" + this.worldObj.getBlockMetadata(i, j, k), this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.boundingBox.minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, 4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D);
            }
        }

        if(this.holdTNTTick == 0){
        	int dif = this.worldObj.difficultySetting.getDifficultyId();
        	Block block = this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ);

        	if(this.getHealth() >= 100 && !block.getMaterial().isLiquid()){
        		int r3 = 2 + new java.util.Random().nextInt(3);
        		for (int i = 0; i < r3; i++){
        			int r = new java.util.Random().nextInt(8);
            		int r1 = 3 + new java.util.Random().nextInt(8);
            		int r2 = new java.util.Random().nextInt(4);
            		int x = MathHelper.floor_double(this.posX);
            		int y = MathHelper.floor_double(this.boundingBox.minY);
                    int z = MathHelper.floor_double(this.posZ);
            		if(r == 0){
            			x = x + r1;
            		} else if(r == 1){
            			x = x - r1;
            		} else if(r == 2){
            			z = z + r1;
            		} else if(r == 3){
            			z = z - r1;
            		} else if(r == 4){
            			x = x + r1;
            			z = z + r1;
            		} else if(r == 5){
            			x = x + r1;
            			z = z - r1;
            		} else if(r == 6){
            			x = x - r1;
            			z = z + r1;
            		} else if(r == 7){
            			x = x - r1;
            			z = z - r1;
            		}

            		World world = this.worldObj;
            		if (!world.isRemote){
        	    		EntityTNTPrimed entity = new EntityTNTPrimed(world);
        	    		entity.setLocationAndAngles((double)x + 0.5D, (double)y + 1 + r2, (double)z + 0.5D, 0.0F, 0.0F);
        	    	    entity.fuse = 40; //現れてから爆発するまでの時間
        	    		world.spawnEntityInWorld(entity);
            		}
        		}
        	} else {
        		this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, dif * 20 * 10, dif));
        		this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, dif * 20 * 10, dif));
        	}


        	this.tasks.onUpdateTasks();
    		int dif2 = 4 - dif;
    		this.holdTNTTick = 20 * 10 * dif2 * dif2 + 20 * 5;
        }

        if(this.liquidTick == 0){
    		Block block = this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ);

    		if (block.getMaterial().isLiquid()){
        		liquidTick = 20 * 10;
        		for (int ay = 0; ay < 5; ay++){
                	for (int ax = 0; ax < 5; ax++){
                    	for (int az = 0; az < 5; az++){
                    		if (this.worldObj.getBlock((int)this.posX + ax - 2, (int)this.posY + ay + 1, (int)this.posZ + az - 2).getMaterial().isLiquid()){
                    			if (!this.worldObj.isRemote){
                    				this.worldObj.setBlockToAir((int)this.posX + ax - 2, (int)this.posY + ay + 1, (int)this.posZ + az - 2);
                    			}
                			}
                    	}
                	}
            	}
        		int r = new java.util.Random().nextInt(20);
        		if(r == 0){
        			this.setDead();
        		}
    		}
    	}
    }

    /**
     * Returns true if this entity can attack entities of the specified class.
     */
    public boolean canAttackClass(Class p_70686_1_){
        return this.isPlayerCreated() && EntityPlayer.class.isAssignableFrom(p_70686_1_) ? false : super.canAttackClass(p_70686_1_);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_){
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setBoolean("PlayerCreated", this.isPlayerCreated());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_){
        super.readEntityFromNBT(p_70037_1_);
        this.setPlayerCreated(p_70037_1_.getBoolean("PlayerCreated"));
    }

    public boolean attackEntityAsMob(Entity entity){
        this.attackTimer = 10;
        this.worldObj.setEntityState(this, (byte)4);
        boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + this.rand.nextInt(15)));

        if (flag){
        	entity.motionY += 0.4000000059604645D;
        }
        this.playSound("mob.irongolem.throw", 1.0F, 1.0F);

        if (!this.worldObj.isRemote){
        	int x = (int)entity.posX;
            int y = (int)entity.posY;
            int z = (int)entity.posZ;

            if (this.worldObj.isAirBlock(x, y, z)){
                this.worldObj.setBlock(x, y, z, Blocks.fire);
            }
        }

        return flag;
    }

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte b0){
        if (b0 == 4){
            this.attackTimer = 10;
            this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
        } else if (b0 == 11) {
            this.holdTNTTick = 400;
        } else {
            super.handleHealthUpdate(b0);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getAttackTimer(){
        return this.attackTimer;
    }

    public void setHoldingTNT(boolean p_70851_1_){
        this.holdTNTTick = p_70851_1_ ? 400 : 0;
        this.worldObj.setEntityState(this, (byte)11);
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound(){
        return "mob.irongolem.hit";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound(){
        return "mob.irongolem.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_){
        this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
    }

    public int getHoldTNTTick(){
        return this.holdTNTTick;
    }

    public boolean isPlayerCreated(){
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setPlayerCreated(boolean p_70849_1_){
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70849_1_){
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
        } else {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
        }
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource p_70645_1_){
        if (!this.isPlayerCreated() && this.attackingPlayer != null && this.villageObj != null){
            this.villageObj.setReputationForPlayer(this.attackingPlayer.getCommandSenderName(), -5);
        }

        super.onDeath(p_70645_1_);
    }
}