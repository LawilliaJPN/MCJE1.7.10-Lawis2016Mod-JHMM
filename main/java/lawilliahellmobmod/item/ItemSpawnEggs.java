package lawilliahellmobmod.item;

import cpw.mods.fml.common.registry.GameRegistry;
import lawilliahellmobmod.api.HMMItems;
import net.minecraft.creativetab.CreativeTabs;

public class ItemSpawnEggs {
	public ItemSpawnEggs(){
		HMMItems.eggBlackWolf = new EggBlackWolf()
			.setCreativeTab(CreativeTabs.tabMisc)
			.setUnlocalizedName("eggBlackWolf")
			.setTextureName("justfortthehellmobmod:spawnegg_blackwolf");
		GameRegistry.registerItem(HMMItems.eggBlackWolf, "eggBlackWolf");

		HMMItems.eggFlouncingSquid = new EggFlouncingSquid()
			.setCreativeTab(CreativeTabs.tabMisc)
			.setUnlocalizedName("eggFlouncingSquid")
			.setTextureName("justfortthehellmobmod:spawnegg_flouncingsquid");
		GameRegistry.registerItem(HMMItems.eggFlouncingSquid, "eggFlouncingSquid");

		HMMItems.eggHellCreeper = new EggHellCreeper()
			.setCreativeTab(CreativeTabs.tabMisc)
			.setUnlocalizedName("eggHellCreeper")
			.setTextureName("justfortthehellmobmod:spawnegg_hellcreeper");
		GameRegistry.registerItem(HMMItems.eggHellCreeper, "eggHellCreeper");


		//以下、デバッグ用実装
		HMMItems.eggDarkGolem = new EggDarkGolem()
				.setCreativeTab(CreativeTabs.tabMisc)
				.setUnlocalizedName("eggDarkGolem")
				.setTextureName("egg");
		GameRegistry.registerItem(HMMItems.eggDarkGolem, "eggDarkGolem");

		HMMItems.eggNetherWitch = new EggNetherWitch()
				.setCreativeTab(CreativeTabs.tabMisc)
				.setUnlocalizedName("eggNetherWitch")
				.setTextureName("egg");
		GameRegistry.registerItem(HMMItems.eggNetherWitch, "eggNetherWitch");

		HMMItems.eggNetherZombie = new EggNetherZombie()
				.setCreativeTab(CreativeTabs.tabMisc)
				.setUnlocalizedName("eggNetherZombie")
				.setTextureName("egg");
		GameRegistry.registerItem(HMMItems.eggNetherZombie, "eggNetherZombie");

		HMMItems.eggSquidInkBat = new EggSquidInkBat()
				.setCreativeTab(CreativeTabs.tabMisc)
				.setUnlocalizedName("eggSquidInkBat")
				.setTextureName("egg");
		GameRegistry.registerItem(HMMItems.eggSquidInkBat, "eggSquidInkBat");
	}
}
