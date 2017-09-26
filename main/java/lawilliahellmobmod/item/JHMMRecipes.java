package lawilliahellmobmod.item;

import cpw.mods.fml.common.registry.GameRegistry;
import lawilliahellmobmod.api.HMMBlocks;
import lawilliahellmobmod.api.HMMItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class JHMMRecipes {
	public JHMMRecipes(){
		//モンスターエッグを精錬して、スポーンエッグに。(精錬経験値はサボテン、ラピスラズリ相当。)
		GameRegistry.addSmelting(HMMBlocks.meBlackWolf,new ItemStack(HMMItems.eggBlackWolf, 1, 0),0.2f);
		GameRegistry.addSmelting(HMMBlocks.meHellCreeper,new ItemStack(HMMItems.eggHellCreeper, 1, 0),0.2f);

		//スポーンエッグの使い道増レシピ
		//黒オオカミ：革×4
		GameRegistry.addShapelessRecipe(new ItemStack(Items.leather, 4, 0),
				new ItemStack(Items.dye, 1, 4), //ラピスラズリ
				HMMItems.eggBlackWolf );
		//水晶イカ：イカスミ×4
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 4, 0),
				new ItemStack(Items.dye, 1, 4), //ラピスラズリ
				HMMItems.eggFlouncingSquid );
		//ヘルクリーパー：火薬×4
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder, 4, 0),
				new ItemStack(Items.dye, 1, 4), //ラピスラズリ
				HMMItems.eggHellCreeper );

		//スポーンエッグ変換
		//黒オオカミ⇒[染料：白]⇒オオカミ
		GameRegistry.addRecipe(new ItemStack(Items.spawn_egg, 1, 95),
				"XXX",
				"XOX",
				"XXX",
				'X',new ItemStack(Items.dye, 1, 15),
				'O',HMMItems.eggBlackWolf );
		//水晶イカ⇒[染料：黒]⇒イカ
		GameRegistry.addRecipe(new ItemStack(Items.spawn_egg, 1, 94),
				"XXX",
				"XOX",
				"XXX",
				'X',new ItemStack(Items.dye, 1, 0),
				'O',HMMItems.eggFlouncingSquid );
		//ヘルクリーパー⇒[染料：緑]⇒クリーパー
		GameRegistry.addRecipe(new ItemStack(Items.spawn_egg, 1, 50),
				"XXX",
				"XOX",
				"XXX",
				'X',new ItemStack(Items.dye, 1, 2),
				'O',HMMItems.eggFlouncingSquid );

		//ダイヤの剣とスポーンエッグで、mobの剣に。
		GameRegistry.addShapelessRecipe(new ItemStack(HMMItems.swordBlackWolf),
				Items.diamond_sword ,
				HMMItems.eggBlackWolf );
		GameRegistry.addShapelessRecipe(new ItemStack(HMMItems.swordFlouncingSquid),
				Items.diamond_sword ,
				HMMItems.eggFlouncingSquid );
	}
}
