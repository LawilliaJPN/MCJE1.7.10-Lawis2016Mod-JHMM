package lawilliahellmobmod.block;

import cpw.mods.fml.common.registry.GameRegistry;
import lawilliahellmobmod.api.HMMBlocks;
import net.minecraft.creativetab.CreativeTabs;

public class MonsterEggs {
	public MonsterEggs(){
		//モンスターエッグ
		//黒オオカミ
		HMMBlocks.meBlackWolf = new MEBlackWolf()
			.setCreativeTab(CreativeTabs.tabDecorations)
			.setBlockName("meBlackWold")
			.setBlockTextureName("netherrack");
		GameRegistry.registerBlock(HMMBlocks.meBlackWolf, "meBlackWold");

		//ヘルクリーパー
		HMMBlocks.meHellCreeper = new MEHellCreeper()
			.setCreativeTab(CreativeTabs.tabDecorations)
			.setBlockName("meBlackWold")
			.setBlockTextureName("netherrack");
		GameRegistry.registerBlock(HMMBlocks.meHellCreeper, "meHellCreeper");


		//特殊ネザーラック
		//コウモリブロック
		HMMBlocks.batBlockN = new BlockBatBlockN()
			.setCreativeTab(CreativeTabs.tabDecorations)
			.setBlockName("batBlockN")
			.setBlockTextureName("netherrack");
		GameRegistry.registerBlock(HMMBlocks.batBlockN, "batBlockN");
		//有害ブロック
		HMMBlocks.bpBlockN= new BlockBPBlockN()
			.setCreativeTab(CreativeTabs.tabDecorations)
			.setBlockName("bpBlockN")
			.setBlockTextureName("netherrack");
		GameRegistry.registerBlock(HMMBlocks.bpBlockN, "bpBlockN");
		//溶岩ブロック
		HMMBlocks.lavaBlockN = new BlockLavaBlockN()
			.setCreativeTab(CreativeTabs.tabDecorations)
			.setBlockName("lavaBlockN")
			.setBlockTextureName("netherrack");
		GameRegistry.registerBlock(HMMBlocks.lavaBlockN, "lavaBlockN");
		//TNTブロック
		HMMBlocks.tntBlockN = new BlockTNTBlockN()
			.setCreativeTab(CreativeTabs.tabDecorations)
			.setBlockName("tntBlockN")
			.setBlockTextureName("netherrack");
		GameRegistry.registerBlock(HMMBlocks.tntBlockN, "tntBlockN");

	}
}
