package lawilliahellmobmod.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockBPBlockN extends Block {
	protected BlockBPBlockN() {
        super(Material.rock);
	    //ブロックの特性の設定
	    //ブロックの硬さ (黒曜石50.0F、鉱石3.0F、石1.5F、土0.5F)
	    setHardness(0.4F); //バニラのネザーラックと同等
	    //ブロックの上を歩いた音
	    setStepSound(Block.soundTypePiston); //ネザーラックはピストンの音
		//適正ツールの設定(0:木、1:石、2:鉄、3:ダイヤ)
		setHarvestLevel("pickaxe", 1);
	    //ブロックの明るさ(×15した値が光源レベル)
	    setLightLevel(0.0F);
	}

	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData){
		int area = 5;

		int lv = 0;
        if (world.difficultySetting == EnumDifficulty.EASY){
            lv = 1;
        } else if (world.difficultySetting == EnumDifficulty.NORMAL){
            lv = 2;
        } else if (world.difficultySetting == EnumDifficulty.HARD){
            lv = 3;
        }

		if (!world.isRemote) {
			AxisAlignedBB AABB = AxisAlignedBB.getBoundingBox(
					(double) ((float) x - area), (double) y - area, (double) ((float) z - area),
					(double) ((float) x + area), (double) y + area, (double) ((float) z + area));
			List list = world.getEntitiesWithinAABB(EntityLivingBase.class, AABB);

			if (list != null && !list.isEmpty()){
	        	for (int i = 0 ; i < list.size() ; i++){
	        		EntityLivingBase entity = (EntityLivingBase)list.get(i);

	        		if (entity != null && entity instanceof EntityPlayer){
	        			EntityPlayer player = (EntityPlayer) entity;
	        			String potionS = "";
	        			int rp = new java.util.Random().nextInt(5);

	        	   		if(rp == 0){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.moveSlowdown";
	        	   		} else if(rp == 1){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.digSlowDown";
	        	   		} else if(rp == 2){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.hunger.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.hunger";
	        	   		} else if(rp == 3){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.weakness.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.weakness";
	        	   		} else if(rp == 4){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.poison.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.poison";
	        	   		}

	        	   		player.addChatMessage(new ChatComponentTranslation("msg.bpblock.txt"));
	        	   		player.addChatMessage(new ChatComponentTranslation(potionS));
	        		}
	        	}
			}
		}
	}

	//ブロックが爆発されたときの処理
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion){
    	int area = 15;

		int lv = 1;
        if (world.difficultySetting == EnumDifficulty.EASY){
            lv = 1;
        } else if (world.difficultySetting == EnumDifficulty.NORMAL){
            lv = 2;
        } else if (world.difficultySetting == EnumDifficulty.HARD){
            lv = 3;
        }

		if (!world.isRemote) {
			AxisAlignedBB AABB = AxisAlignedBB.getBoundingBox(
					(double) ((float) x - area), (double) y - area, (double) ((float) z - area),
					(double) ((float) x + area), (double) y + area, (double) ((float) z + area));
			List list = world.getEntitiesWithinAABB(EntityLivingBase.class, AABB);

			if (list != null && !list.isEmpty()){
	        	for (int i = 0 ; i < list.size() ; i++){
	        		EntityLivingBase entity = (EntityLivingBase)list.get(i);

	        		if (entity != null && entity instanceof EntityPlayer){
	        			EntityPlayer player = (EntityPlayer) entity;
	        			String potionS = "";
	        			int rp = new java.util.Random().nextInt(5);

	        	   		if(rp == 0){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.moveSlowdown";
	        	   		} else if(rp == 1){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.digSlowDown";
	        	   		} else if(rp == 2){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.hunger.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.hunger";
	        	   		} else if(rp == 3){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.weakness.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.weakness";
	        	   		} else if(rp == 4){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.poison.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.poison";
	        	   		}

	        	   		player.addChatMessage(new ChatComponentTranslation("msg.bpblock.txt"));
	        	   		player.addChatMessage(new ChatComponentTranslation(potionS));
	        		}
	        	}
			}
		}
    }

	/*
	@Override
	public int tickRate(World world){
        return 20 * 10;
    }

	public void updateTick(World world, int x, int y, int z, Random random){
		int area = 5;

		int lv = 1;
        if (world.difficultySetting == EnumDifficulty.EASY){
            lv = 1;
        } else if (world.difficultySetting == EnumDifficulty.NORMAL){
            lv = 2;
        } else if (world.difficultySetting == EnumDifficulty.HARD){
            lv = 3;
        }

		if (!world.isRemote) {
			AxisAlignedBB AABB = AxisAlignedBB.getBoundingBox(
					(double) ((float) x - area), (double) y - area, (double) ((float) z - area),
					(double) ((float) x + area), (double) y + area, (double) ((float) z + area));
			List list = world.getEntitiesWithinAABB(EntityLivingBase.class, AABB);

			if (list != null && !list.isEmpty()){
	        	for (int i = 0 ; i < list.size() ; i++){
	        		EntityLivingBase entity = (EntityLivingBase)list.get(i);

	        		if (entity != null && entity instanceof EntityPlayer){
	        			EntityPlayer player = (EntityPlayer) entity;
	        			String potionS = "";
	        			int rp = new java.util.Random().nextInt(5);

	        	   		if(rp == 0){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.moveSlowdown";
	        	   		} else if(rp == 1){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.digSlowDown";
	        	   		} else if(rp == 2){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.hunger.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.hunger";
	        	   		} else if(rp == 3){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.weakness.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.weakness";
	        	   		} else if(rp == 4){
	        	   			entity.addPotionEffect(new PotionEffect(Potion.poison.id, lv * 10 * 20, lv));
	        	   			potionS = "potion.poison";
	        	   		}

	        	   		player.addChatMessage(new ChatComponentTranslation(potionS));
	        		}
	        	}
			}
		}
    }*/

    //破壊時のドロップアイテムをネザーラックに
  	@Override
  	public Item getItemDropped(int meta, Random random, int fortune) {
  				return Item.getItemFromBlock(Blocks.netherrack);
  	}

    //シルクタッチ無効
  	protected boolean canSilkHarvest(){
        return false;
    }
}