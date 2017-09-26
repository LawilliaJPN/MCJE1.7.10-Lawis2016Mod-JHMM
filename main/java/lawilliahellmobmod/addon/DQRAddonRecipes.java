package lawilliahellmobmod.addon;

import cpw.mods.fml.common.registry.GameRegistry;
import dqr.api.Items.DQIngots;
import dqr.api.Items.DQMiscs;
import lawilliahellmobmod.api.HMMItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class DQRAddonRecipes {
	public DQRAddonRecipes(){
		//ネザー関連アイテム＆DQRアイテムで、勝手に追加なレシピ
		//小さい魂＋砂＋ネザーラック⇒ソウルサンド
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.soul_sand, 1, 0),
				DQMiscs.itemTiisaitamasii ,
				Blocks.sand ,
				Blocks.netherrack );
		//ネザーウォート＋磨き砂⇒うるわしキノコ
		GameRegistry.addShapelessRecipe(new ItemStack(DQMiscs.itemUruwasikinoko, 1, 0),
				DQIngots.itemMigakizuna ,
				Blocks.nether_wart );

		//スポーンエッグの使い道増レシピ（聖者の灰）
		//黒オオカミ：トゲトゲの牙×4
		GameRegistry.addShapelessRecipe(new ItemStack(DQMiscs.itemTogetogenokiba, 4, 0),
				DQMiscs.itemSeijanohai, //聖者の灰
				HMMItems.eggBlackWolf );
		//水晶イカ：聖水×4
		GameRegistry.addShapelessRecipe(new ItemStack(DQMiscs.itemSeisui, 4, 0),
				DQMiscs.itemSeijanohai, //聖者の灰
				HMMItems.eggFlouncingSquid );
		//ヘルクリーパー：べっこう×4
		GameRegistry.addShapelessRecipe(new ItemStack(DQMiscs.itemBekkou, 4, 0),
				DQMiscs.itemSeijanohai, //聖者の灰
				HMMItems.eggHellCreeper );
	}
}
