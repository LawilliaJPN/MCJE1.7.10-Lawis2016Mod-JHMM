package lawilliahellmobmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockBatBlockN extends Block {

	protected BlockBatBlockN() {
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

	//ブロックを壊したときに同x、z座標のブロックが逆さになる
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData){
	    if (!world.isRemote){
	    	for(int i = 0;i < (y - 10) / 2 ;i++){
        		Block block1 = world.getBlock(x, 10 + i, z);
        		Block block2 = world.getBlock(x, y - i, z);

        		if(block1.getMaterial().isLiquid()){
        			world.setBlockToAir(x, y - i, z);
        		} else {
        			world.setBlock(x, y - i, z, block1);
        		}

        		if(block2.getMaterial().isLiquid()){
        			world.setBlockToAir(x, 10 + i, z);
        		} else {
        			world.setBlock(x, 10 + i, z, block2);
        		}
        	}
	    }
	        super.onBlockDestroyedByPlayer(world, x, y, z, metaData);
	}

	//ブロックが爆発されたときの処理
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion){
    	if (!world.isRemote){
    		for(int i = 0;i < (y - 10) / 2 ;i++){
        		Block block1 = world.getBlock(x, 10 + i, z);
        		Block block2 = world.getBlock(x, y - i, z);

        		if(block1.getMaterial().isLiquid()){
        			world.setBlockToAir(x, y - i, z);
        		} else {
        			world.setBlock(x, y - i, z, block1);
        		}

        		if(block2.getMaterial().isLiquid()){
        			world.setBlockToAir(x, 10 + i, z);
        		} else {
        			world.setBlock(x, 10 + i, z, block2);
        		}
        	}
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
