package lawilliahellmobmod.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityNetherZombie extends EntityMob {
	private int setBlockTick;
    public EntityNetherZombie(World world){
        super(world);
        this.setSize(0.6F, 1.8F);
        this.experienceValue = 5;
        this.isImmuneToFire = true;
        this.stepHeight = 1.6F;

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityIronGolem.class, 6.0F, 3.0D, 6.0D));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.5D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityBlackWolf.class, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityDarkGolem.class, 0, false));
    }

    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
    }

    public EnumCreatureAttribute getCreatureAttribute(){
        return EnumCreatureAttribute.UNDEAD;
    }

    public boolean isAIEnabled(){
        return true;
    }

    public boolean attackEntityAsMob(Entity entity){
        if (super.attackEntityAsMob(entity)){
            if (entity instanceof EntityLivingBase){
            	EntityLivingBase entityLB = (EntityLivingBase) entity;
                int lv = entity.worldObj.difficultySetting.getDifficultyId();

                if (lv > 0){
                	if(entityLB.isPotionActive(Potion.hunger.id)){
                		entityLB.addPotionEffect(new PotionEffect(Potion.poison.id, lv * 5 * 20, lv - 1));
                	} else {
                		entityLB.addPotionEffect(new PotionEffect(Potion.hunger.id, lv * 5 * 20, lv - 1));
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

        if (this.setBlockTick > 0){
            --this.setBlockTick;
        } else {
        	int dif = this.worldObj.difficultySetting.getDifficultyId();
        	World world = this.worldObj;
        	int x = MathHelper.floor_double(this.posX);
    		int y = MathHelper.floor_double(this.boundingBox.minY);
            int z = MathHelper.floor_double(this.posZ);
            Block block = world.getBlock(x, y - 1, z);

        	if(block != Blocks.bedrock){
        		int r = new java.util.Random().nextInt(4);
        		if(block.getMaterial().isLiquid()){
        			if(r == 0){
        				world.setBlock(x, y - 1, z, Blocks.obsidian);
                	} else {
                		world.setBlock(x, y - 1, z, Blocks.gravel);
                	}
                } else if(block == Blocks.netherrack){
                	if(r == 0){
                		world.setBlock(x, y - 1, z, Blocks.obsidian);
                	} else {
                		world.setBlock(x, y - 1, z, Blocks.soul_sand);
                	}
                } else if(block == Blocks.nether_brick){
                	if(r == 0){
                		world.setBlock(x, y - 1, z, Blocks.flowing_lava);
                	} else {
                		world.setBlock(x, y - 1, z, Blocks.gravel);
                	}
                } else if(block == Blocks.gravel || block == Blocks.soul_sand){
                	if(r == 0){
                		this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, dif * 5 * 20, dif - 1));
                	} else if(r == 1){
                		this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, dif * 5 * 20, dif - 1));
                	} else if(r == 2){
                		this.addPotionEffect(new PotionEffect(Potion.resistance.id, dif * 5 * 20, dif - 1));
                	} else {
                		this.addPotionEffect(new PotionEffect(Potion.jump.id, dif * 5 * 20, dif - 1));
                	}
                } else if(block == Blocks.obsidian){
                	if(r == 0){
                		world.setBlock(x, y - 1, z, Blocks.gravel);
                	} else {
                		world.setBlock(x, y - 1, z, Blocks.air);
                	}
                } else if(block.isOpaqueCube()){
                	if(r == 0){
                		world.setBlock(x, y - 1, z, Blocks.gravel);
                	} else {
                		world.setBlock(x, y - 1, z, Blocks.netherrack);
                	}
                }
            }
        	this.setBlockTick = 20 * 5 * (4 - dif);
        }
    }

    public void onKillEntity(EntityLivingBase entity){
        super.onKillEntity(entity);
        if (entity instanceof EntityVillager){
            EntityNetherZombie entityzombie = new EntityNetherZombie(this.worldObj);
            entityzombie.copyLocationAndAnglesFrom(entity);
            this.worldObj.removeEntity(entity);
            entityzombie.onSpawnWithEgg((IEntityLivingData)null);

            this.worldObj.spawnEntityInWorld(entityzombie);
            this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1016, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
        }
    }

    protected String getLivingSound(){
        return "mob.zombie.say";
    }

    protected String getHurtSound(){
        return "mob.zombie.hurt";
    }

    protected String getDeathSound(){
        return "mob.zombie.death";
    }

    /*
    protected void addRandomArmor(){
        super.addRandomArmor();

        int i = this.rand.nextInt(5);
        if(i == 0){
            this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
        } else if(i == 1) {
        	this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
        } else if(i == 2) {
        	this.setCurrentItemOrArmor(0, new ItemStack(Items.diamond_hoe));
        } else if(i == 3) {
        	this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_axe));
        } else if(i == 4) {
        	this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_axe));
        }
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data){
        Object obj = super.onSpawnWithEgg(data);
        this.addRandomArmor();
        this.enchantEquipment();
        return (IEntityLivingData)obj;
    }
    */
}
