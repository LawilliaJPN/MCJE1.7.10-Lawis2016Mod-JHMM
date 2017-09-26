package lawilliahellmobmod.entity.ai;

import lawilliahellmobmod.entity.EntityHellCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIHellCreeperSwell extends EntityAIBase{
    /** The creeper that is swelling. */
    EntityHellCreeper swellingHellCreeper;
    /** The creeper's attack target. This is used for the changing of the creeper's state. */
    EntityLivingBase creeperAttackTarget;
    private static final String __OBFID = "CL_00001614";

    public EntityAIHellCreeperSwell(EntityHellCreeper hellCreeper){
        this.swellingHellCreeper = hellCreeper;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute(){
        EntityLivingBase entitylivingbase = this.swellingHellCreeper.getAttackTarget();
        return this.swellingHellCreeper.getCreeperState() > 0 || entitylivingbase != null && this.swellingHellCreeper.getDistanceSqToEntity(entitylivingbase) < 9.0D;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting(){
        this.swellingHellCreeper.getNavigator().clearPathEntity();
        this.creeperAttackTarget = this.swellingHellCreeper.getAttackTarget();
    }

    /**
     * Resets the task
     */
    public void resetTask(){
        this.creeperAttackTarget = null;
    }

    /**
     * Updates the task
     */
    public void updateTask(){
        if (this.creeperAttackTarget == null){
            this.swellingHellCreeper.setCreeperState(-1);
        } else if (this.swellingHellCreeper.getDistanceSqToEntity(this.creeperAttackTarget) > 49.0D){
            this.swellingHellCreeper.setCreeperState(-1);
        } else if (!this.swellingHellCreeper.getEntitySenses().canSee(this.creeperAttackTarget)){
            this.swellingHellCreeper.setCreeperState(-1);
        } else {
            this.swellingHellCreeper.setCreeperState(1);
        }
    }
}