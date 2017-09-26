package lawilliahellmobmod.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityFlouncingSquid extends EntityMob{
	private int jumpDelay;

	public EntityFlouncingSquid(World world){
		super(world);
		this.setSize(0.95F, 0.95F);
		this.experienceValue = 10;

		this.isImmuneToFire = true; //溶岩や火のダメージ無効
		this.jumpDelay = this.rand.nextInt(10) + 5;
	}

	protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
    }

    protected void updateEntityActionState(){
        this.despawnEntity();

        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 32.0D);
        if (entityplayer != null){  //プレイヤーが近くにいる時の処理
        	//プレイヤーの方向を向く
            this.faceEntity(entityplayer, 10.0F, 20.0F);

            int lv = 0; //難易度によって、召喚頻度が変わる
        	if (this.worldObj.difficultySetting == EnumDifficulty.EASY){
                lv = 3; //30秒に1回目安
            } else if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL){
                lv = 2; //20秒に1回目安
            } else if (this.worldObj.difficultySetting == EnumDifficulty.HARD){
                lv = 1; //10秒に1回目安
            }

            int rs = new java.util.Random().nextInt(20 * lv * 10);
            World world = this.worldObj;
            int x = (int)this.posX;
            int y = (int)this.posY;
            int z = (int)this.posZ;
            Block block = world.getBlock(x, y, z);

            if(!world.isRemote && lv != 0 && rs == 0 && !block.getMaterial().isLiquid()){
            	EntitySquidInkBat entitySquidInkBat = new EntitySquidInkBat(world);
                entitySquidInkBat.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
                world.spawnEntityInWorld(entitySquidInkBat);
                entitySquidInkBat.spawnExplosionParticle();
            }
        }

        if (this.onGround && this.jumpDelay-- <= 0){
            this.jumpDelay = this.getJumpDelay();

            if (entityplayer != null){
                this.jumpDelay /= 3;
            }
            this.isJumping = true;

            this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
            this.moveForward = 0.95F; //サイズ

        } else {
            this.isJumping = false;
            Block block = this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ);

            if (this.onGround || block.getMaterial().isLiquid()){
            	int r = new java.util.Random().nextInt(10);
            	if(r == 0){
            		this.moveForward = 0.95F;
            	} else {
            		this.moveStrafing = this.moveForward = 0.0F;
            		if(r == 1){
            			this.motionX = 0.3D;
            		} else if(r == 2){
                		this.motionX = -0.3D;
                	} else if(r == 3){
                		this.motionZ = 0.3D;
                	} else if(r == 4){
                		this.motionZ = -0.3D;
                	}
            	}
            	this.motionY = 0.6D;
            }
        }
    }

    //水晶イカと衝突したエンティティへの処理
    protected void collideWithEntity(Entity entity) {
    	int lv = 0; //難易度によって飛ぶ高さを調整
    	if (this.worldObj.difficultySetting == EnumDifficulty.EASY){
            lv = 2;
        } else if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL){
            lv = 3;
        } else if (this.worldObj.difficultySetting == EnumDifficulty.HARD){
            lv = 4;
        }

    	//アイアンゴーレムに攻撃されたときみたいな感じ
    	if (super.attackEntityAsMob(entity)){
            if (entity instanceof EntityLivingBase){
            	entity.motionY = 0.3D * lv;
            }
    	}
    }

    //水晶イカに攻撃したエンティティへの処理
    public boolean attackEntityFrom(DamageSource damage, float f1){
    	if (this.isEntityInvulnerable()){
            return false;
        } else {

            if (damage instanceof EntityDamageSource){
            	Entity entity = damage.getEntity();
            	if(entity instanceof EntityLivingBase){
            		entity.motionY = 0.6D;
            	}
            }

            return super.attackEntityFrom(damage, f1);
        }
    }

    protected int getJumpDelay(){
        return this.rand.nextInt(10) + 5;
    }

    public void setDead(){ //水晶イカの死亡時の処理
        int i = 1;
        World world = this.worldObj;
        int x = (int)this.posX;
        int y = (int)this.posY;
        int z = (int)this.posZ;
        Block block = world.getBlock(x, y, z);

        //インクコウモリをスポーンさせる
        if (!world.isRemote && this.getHealth() <= 0.0F && !block.getMaterial().isLiquid()){
        	EntitySquidInkBat entitySquidInkBat = new EntitySquidInkBat(world);
            entitySquidInkBat.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(entitySquidInkBat);
            entitySquidInkBat.spawnExplosionParticle();
        }

        super.setDead();
    }

    public void onCollideWithPlayer(EntityPlayer entityPlayer){
    	if (this.canEntityBeSeen(entityPlayer) && this.getDistanceSqToEntity(entityPlayer) < 1.44D && entityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F)){
            this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected String getLivingSound(){
        return "mob.bat.idle";
    }

    protected String getHurtSound(){
        return "mob.bat.hurt";
    }

    protected String getDeathSound(){
        return "mob.bat.death";
    }

    protected float getSoundVolume(){
        return 0.4F;
    }

    /*溶岩スポーンさせたかった
    public boolean getCanSpawnHere(){
    	World world = this.worldObj;
    	int dim = world.provider.dimensionId;
    	int x = MathHelper.floor_double(this.posX);
    	int y = MathHelper.floor_double(this.boundingBox.minY);
    	int z = MathHelper.floor_double(this.posZ);
    	Block block = world.getBlock(x, y, z);

        Chunk chunk = world.getChunkFromBlockCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));
        if(dim == -1 && block.getMaterial() == Material.lava){

        }
	}*/
        /*ヤマネコ
        if (this.worldObj.rand.nextInt(3) == 0){
            return false;
        } else {
            if (this.worldObj.checkNoEntityCollision(this.boundingBox)
            	&& this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()
            	&& !this.worldObj.isAnyLiquid(this.boundingBox)){

                int i = MathHelper.floor_double(this.posX);
                int j = MathHelper.floor_double(this.boundingBox.minY);
                int k = MathHelper.floor_double(this.posZ);

                if (j < 63){
                    return false;
                }

                Block block = this.worldObj.getBlock(i, j - 1, k);

                if (block == Blocks.grass || block.isLeaves(worldObj, i, j - 1, k)){
                    return true;
                }
            }

            return false;
        }*/

        /*スライム
        Chunk chunk = this.worldObj.getChunkFromBlockCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));

        if (this.worldObj.getWorldInfo().getTerrainType().handleSlimeSpawnReduction(rand, worldObj)){
            return false;
        } else {
            if (this.getSlimeSize() == 1 || this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL){
                BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));

                if (biomegenbase == BiomeGenBase.swampland && this.posY > 50.0D && this.posY < 70.0D
                && this.rand.nextFloat() < 0.5F && this.rand.nextFloat() < this.worldObj.getCurrentMoonPhaseFactor()
                && this.worldObj.getBlockLightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) <= this.rand.nextInt(8)){
                    return super.getCanSpawnHere();
                }

                if (this.rand.nextInt(10) == 0 && chunk.getRandomWithSeed(987234911L).nextInt(10) == 0 && this.posY < 40.0D){
                    return super.getCanSpawnHere();
                }
            }
            return false;
        }*/
}
