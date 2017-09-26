package lawilliahellmobmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockTNTBlockN extends Block {

	protected BlockTNTBlockN() {
        super(Material.rock);
	    //ブロックの特性の設定
	    //ブロックの硬さ (黒曜石50.0F、鉱石3.0F、石1.5F、土0.5F)
	    setHardness(0.4F); //バニラのネザーラックと同等
	    //爆発耐性（黒曜石2000.0F、石30.0F、鉱石15.0F、土2.5F）
	    setResistance(2000.0F); //黒曜石と同等。
	    //ブロックの上を歩いた音
	    setStepSound(Block.soundTypePiston); //ネザーラックはピストンの音
		//適正ツールの設定(0:木、1:石、2:鉄、3:ダイヤ)
		this.setHarvestLevel("pickaxe", 1);
	    //ブロックの明るさ(×15した値が光源レベル)
	    setLightLevel(0.0F);
	}

	//BlockSilverfish.classを参考に、ブロックを壊したときに洞窟蜘蛛を召喚
	public void onBlockDestroyedByPlayer(World p_149664_1_, int p_149664_2_, int p_149664_3_, int p_149664_4_, int p_149664_5_)
	{
	       if (!p_149664_1_.isRemote){
	    	    p_149664_1_.setBlockToAir(p_149664_2_, p_149664_3_ + 1, p_149664_4_);

	            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(p_149664_1_);
	            entitytntprimed.setLocationAndAngles((double)p_149664_2_ + 0.5D, (double)p_149664_3_ + 1, (double)p_149664_4_ + 0.5D, 0.0F, 0.0F);

	    	    int r = new java.util.Random().nextInt(5);
	            entitytntprimed.fuse = 40 + 10 * r  ; //現れてから爆発するまでの時間

	            p_149664_1_.spawnEntityInWorld(entitytntprimed);
	        }
	        super.onBlockDestroyedByPlayer(p_149664_1_, p_149664_2_, p_149664_3_, p_149664_4_, p_149664_5_);
	}

	//ブロックが爆発されたときの処理
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
    {
    		if (!world.isRemote){
	    		world.setBlockToAir(x, y, z);
	    		world.setBlockToAir(x, y + 1, z);

	    		EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world);
	    		entitytntprimed.setLocationAndAngles((double)x + 0.5D, (double)y + 1, (double)z + 0.5D, 0.0F, 0.0F);

	    	    int re = new java.util.Random().nextInt(4);
	            entitytntprimed.fuse = 40 + 40 * re  ; //現れてから爆発するまでの時間

	    		world.spawnEntityInWorld(entitytntprimed);
    		}
    }

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