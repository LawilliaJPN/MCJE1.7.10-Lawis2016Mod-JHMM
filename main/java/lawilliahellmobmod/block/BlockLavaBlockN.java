package lawilliahellmobmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockLavaBlockN extends Block {

	protected BlockLavaBlockN() {
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

	//ブロックを壊したときに溶岩が流れる
	public void onBlockDestroyedByPlayer(World p_149664_1_, int p_149664_2_, int p_149664_3_, int p_149664_4_, int p_149664_5_)
	{
	       if (!p_149664_1_.isRemote){
	    	    p_149664_1_.setBlock(p_149664_2_, p_149664_3_, p_149664_4_, Blocks.flowing_lava, 0, 2);
	        }
	        super.onBlockDestroyedByPlayer(p_149664_1_, p_149664_2_, p_149664_3_, p_149664_4_, p_149664_5_);
	}

	//ブロックが爆発されたときの処理
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
    {
    		if (!world.isRemote){
	    		world.setBlock(x, y, z, Blocks.flowing_lava, 0, 2);
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