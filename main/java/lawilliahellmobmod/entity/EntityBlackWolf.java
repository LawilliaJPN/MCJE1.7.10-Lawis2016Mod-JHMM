package lawilliahellmobmod.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityBlackWolf extends EntityMob{
	private int setGlassTick;
	private int setGlassLimit;

    public EntityBlackWolf(World world){
        super(world);
        this.setSize(0.65F, 0.8F);
        this.experienceValue = 5;
        this.isImmuneToFire = true;
        this.stepHeight = 3.1F;

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityIronGolem.class, 6.0F, 1.5D, 3.0D));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.5D, false));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityHellCreeper.class, 4.0F, 1.0D, 1.5D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityOcelot.class, 0, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityFlouncingSquid.class, 0, false));
        this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntityZombie.class, 0, false));
        this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, false));
        this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntitySlime.class, 0, false));
        this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntityNetherZombie.class, 0, false));
        this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntityNetherWitch.class, 0, false));
        this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntityBlaze.class, 0, false));
    }

    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
    }

    //このentityに攻撃された相手に対する処理
    public boolean attackEntityAsMob(Entity entity){
        if (super.attackEntityAsMob(entity)){
            if (entity instanceof EntityLivingBase){
                byte b0 = 0;
                int lv = 0;

                if (this.worldObj.difficultySetting == EnumDifficulty.EASY){
                    b0 = 5; lv = 0;
                } else if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL){
                    b0 = 15; lv = 1;
                } else if (this.worldObj.difficultySetting == EnumDifficulty.HARD){
                    b0 = 30; lv = 2;
                }

                if (b0 > 0){
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.weakness.id, b0 * 20, lv - 1));
                }
            }

            if (entity instanceof EntityFlouncingSquid){
            	int rs = new java.util.Random().nextInt(4);

                if (rs == 0){
                    World world = entity.worldObj;
                    int x = (int)entity.posX;
                    int y = (int)entity.posY;
                    int z = (int)entity.posZ;
                    Block block = world.getBlock(x, y, z);

                    //インクコウモリをスポーンさせる
                    if (!world.isRemote && !block.getMaterial().isLiquid()){
                    	EntitySquidInkBat entitySquidInkBat = new EntitySquidInkBat(world);
                        entitySquidInkBat.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
                        world.spawnEntityInWorld(entitySquidInkBat);
                        entitySquidInkBat.spawnExplosionParticle();
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public void onLivingUpdate(){
        super.onLivingUpdate();

        if (this.setGlassTick > 0 && this.setGlassLimit <= 20){
            --this.setGlassTick;
        } else {
        	int dif = this.worldObj.difficultySetting.getDifficultyId();
        	World world = this.worldObj;
        	int x = MathHelper.floor_double(this.posX);
    		int y = MathHelper.floor_double(this.boundingBox.minY);
            int z = MathHelper.floor_double(this.posZ);
            Block block = world.getBlock(x, y - 1, z);

        	if(block.getMaterial().isLiquid()){
        		int r = new java.util.Random().nextInt(4);
        		if(r == 0){
        			world.setBlock(x, y - 1, z, Blocks.glowstone);
        		} else if(r == 1){
        			r = new java.util.Random().nextInt(16);
        			world.setBlock(x, y - 1, z, Blocks.stained_glass, r, 4);
        		} else {
        			world.setBlock(x, y - 1, z, Blocks.glass);
        		}
        		++this.setGlassLimit;
        		this.setGlassTick = 20 * 5 * (4 - dif);
            }
        }
    }


    public boolean isAIEnabled(){
        return true;
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_){
        this.playSound("mob.wolf.step", 0.15F, 1.0F);
    }

    protected String getLivingSound(){
        return "mob.wolf.growl";
    }

    protected String getHurtSound(){
        return "mob.wolf.hurt";
    }

    protected String getDeathSound(){
        return "mob.wolf.death";
    }

    protected float getSoundVolume(){
        return 0.4F;
    }
}
