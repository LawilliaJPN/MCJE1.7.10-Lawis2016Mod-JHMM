package lawilliahellmobmod.block;

import java.util.Random;

import lawilliahellmobmod.entity.EntityBlackWolf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class MEBlackWolf extends Block {

	protected MEBlackWolf() {
        super(Material.rock);
	    //ブロックの特性の設定
	    //ブロックの硬さ (黒曜石50.0F、鉱石3.0F、石1.5F、土0.5F)
	    setHardness(0.4F); //バニラのネザーラックと同等
	    //ブロックの上を歩いた音
	    setStepSound(Block.soundTypePiston); //ネザーラックはピストンの音
		//適正ツールの設定(0:木、1:石、2:鉄、3:ダイヤ)
		this.setHarvestLevel("pickaxe", 1);
	    //ブロックの明るさ(×15した値が光源レベル)
	    setLightLevel(0.0F);
	}

	//プレイヤーがブロックを破壊したときにエンティティをスポーンさせる
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData){
		if (!world.isRemote){
    	   	EntityBlackWolf entityBlackWolf = new EntityBlackWolf(world);

            int lv = 1;
            if (world.difficultySetting == EnumDifficulty.EASY){
                lv = 1;
            } else if (world.difficultySetting == EnumDifficulty.NORMAL){
                lv = 2;
            } else if (world.difficultySetting == EnumDifficulty.HARD){
                lv = 3;
            }

    	   	for(int i = 0; i < lv * 2; i++){
    	   		int rp = new java.util.Random().nextInt(6);
    	   		if(rp == 1){
    	   			entityBlackWolf.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, lv * 10 * 20, lv));
    	   		} else if(rp == 2){
    	   			entityBlackWolf.addPotionEffect(new PotionEffect(Potion.damageBoost.id, lv * 10 * 20, lv));
    	   		} else if(rp == 3){
    	   			entityBlackWolf.addPotionEffect(new PotionEffect(Potion.jump.id, lv * 10 * 20, lv));
    	   		} else if(rp == 4){
    	   			entityBlackWolf.addPotionEffect(new PotionEffect(Potion.regeneration.id, lv * 10 * 20, lv));
    	   		} else if(rp == 5){
    	   			entityBlackWolf.addPotionEffect(new PotionEffect(Potion.resistance.id, lv * 10 * 20, lv));
    	   		}
    	   	}

    	   	entityBlackWolf.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(entityBlackWolf);
            entityBlackWolf.spawnExplosionParticle();
        }
        super.onBlockDestroyedByPlayer(world, x, y, z, metaData);
	}

	//ブロックが爆発されたときの処理
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion){
    	if (!world.isRemote){
			world.setBlockToAir(x, y, z);
    	   	EntityBlackWolf entityBlackWolf = new EntityBlackWolf(world);

            int lv = 1;
            if (world.difficultySetting == EnumDifficulty.EASY){
                lv = 1;
            } else if (world.difficultySetting == EnumDifficulty.NORMAL){
                lv = 2;
            } else if (world.difficultySetting == EnumDifficulty.HARD){
                lv = 3;
            }

    	   	for(int i = 0; i < lv * 2; i++){
    	   		int rp = new java.util.Random().nextInt(6);
    	   		if(rp == 1){
    	   			entityBlackWolf.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, lv * 10 * 20, lv));
    	   		} else if(rp == 2){
    	   			entityBlackWolf.addPotionEffect(new PotionEffect(Potion.damageBoost.id, lv * 10 * 20, lv));
    	   		} else if(rp == 3){
    	   			entityBlackWolf.addPotionEffect(new PotionEffect(Potion.jump.id, lv * 10 * 20, lv));
    	   		} else if(rp == 4){
    	   			entityBlackWolf.addPotionEffect(new PotionEffect(Potion.regeneration.id, lv * 10 * 20, lv));
    	   		} else if(rp == 5){
    	   			entityBlackWolf.addPotionEffect(new PotionEffect(Potion.resistance.id, lv * 10 * 20, lv));
    	   		}
    	   	}

    	   	entityBlackWolf.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(entityBlackWolf);
            entityBlackWolf.spawnExplosionParticle();
        }
    }

	//破壊時のドロップアイテムをネザーラックorモンスターエッグに
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		int r = new java.util.Random().nextInt(10);

		if(r >= 7){ //30％の確率でモンスターエッグ自身をドロップ
			return Item.getItemFromBlock(this);
		} else {
			return Item.getItemFromBlock(Blocks.netherrack);
		}
	}
}
