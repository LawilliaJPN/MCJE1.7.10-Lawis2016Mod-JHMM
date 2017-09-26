package lawilliahellmobmod.item;

import cpw.mods.fml.common.registry.GameRegistry;
import lawilliahellmobmod.api.HMMItems;
import lawilliahellmobmod.api.HMMToolMaterials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.util.EnumHelper;

public class ItemSwords{

	public ItemSwords(){
		// ツールの基本情報
		HMMToolMaterials.hmmSwords = EnumHelper.addToolMaterial("hmmSwords", 3, 1561, 8.0F, 3.0F, 10);

		// 剣の登録
		// 黒オオカミ
		HMMItems.swordBlackWolf = new SwordBlackWolf(HMMToolMaterials.hmmSwords)
			.setCreativeTab(CreativeTabs.tabCombat)
			.setUnlocalizedName("swordBlackWolf")
			.setTextureName("diamond_sword");
		GameRegistry.registerItem(HMMItems.swordBlackWolf, "swordBlackWolf");

		// 水晶イカ
		HMMItems.swordFlouncingSquid = new SwordFlouncingSquid(HMMToolMaterials.hmmSwords)
			.setCreativeTab(CreativeTabs.tabCombat)
			.setUnlocalizedName("swordFlouncingSquid")
			.setTextureName("diamond_sword");
		GameRegistry.registerItem(HMMItems.swordFlouncingSquid, "swordFlouncingSquid");
	}
}
