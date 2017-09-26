package lawilliahellmobmod.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dqr.api.Items.DQIngots;
import dqr.api.Items.DQMiscs;
import lawilliahellmobmod.addon.AddonsHellMobMod;
import lawilliahellmobmod.api.HMMItems;
import lawilliahellmobmod.entity.EntityBlackWolf;
import lawilliahellmobmod.entity.EntityFlouncingSquid;
import lawilliahellmobmod.entity.EntityHellCreeper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class DropItemHandler {
	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event){
        World world = event.entityLiving.worldObj; //EntityItemの第1引数
        double x = event.entityLiving.posX; //EntityItemの第2引数
        double y = event.entityLiving.posY; //EntityItemの第3引数
        double z = event.entityLiving.posZ; //EntityItemの第4引数

    	int r4 = new java.util.Random().nextInt(4);     //25％
    	int r20 = new java.util.Random().nextInt(20);   //05％
    	int r50 = new java.util.Random().nextInt(50);   //02％
    	int r100 = new java.util.Random().nextInt(100); //01％

		if(event.entityLiving.worldObj.isRemote) {
			return;
		}

		//黒オオカミ
		if(event.entityLiving instanceof EntityBlackWolf){
			if(r4 == 0){ //革
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.leather)));
			} if(r20 == 0) { //発酵した蜘蛛の目
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.fermented_spider_eye)));
			} if(r50 == 0) { //スポーンエッグ
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(HMMItems.eggBlackWolf)));
			} if(r100 == 0){ //ラピスラズリ×4
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.dye, 4, 4)));
			}

			if(AddonsHellMobMod.DQRAddon == true){
				if(r4 == 1){ //まじゅうの皮
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemMajuunokawa)));
				} if(r20 == 1) { //トゲトゲの牙
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemTogetogenokiba)));
				} if(r50 == 1) { //聖者の灰
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemSeijanohai)));
				} if(r100 == 1){ //小さなメダル
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemLittlemedal)));
				}
			}
		}

		//水晶イカ
		if(event.entityLiving instanceof EntityFlouncingSquid){
			if(r4 == 0){ //ネザー水晶
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.quartz)));
			} if(r20 == 0) { //イカスミ×4
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.dye, 4, 0)));
			} if(r50 == 0) { //スポーンエッグ
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(HMMItems.eggFlouncingSquid)));
			} if(r100 == 0){ //骨粉×8
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.dye, 8, 15)));
			}

			if(AddonsHellMobMod.DQRAddon == true){
				if(r4 == 1){ //氷の結晶
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQIngots.itemKoorinokessyou)));
				} if(r20 == 1) { //聖水
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemSeisui)));
				} if(r50 == 1) { //命の石
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQIngots.itemInotinoisi)));
				} if(r100 == 1){ //小さなメダル
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemLittlemedal)));
				}
			}
		}

		//ヘルクリーパー
		if(event.entityLiving instanceof EntityHellCreeper){
			if(r4 == 0){ //火薬
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.gunpowder)));
			} if(r20 == 0) { //ブレイズパウダー
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.blaze_powder)));
			} if(r50 == 0) { //スポーンエッグ
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(HMMItems.eggHellCreeper)));
			} if(r100 == 0){ //火打石×16
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.flint, 16, 0)));
			}

			if(AddonsHellMobMod.DQRAddon == true){
				if(r4 == 1){ //爆弾石
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQIngots.itemBakudanisi)));
				} if(r20 == 1) { //べっこう
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemBekkou)));
				} if(r50 == 1) { //溶岩石のかけら
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQIngots.itemYougansekinokakera)));
				} if(r100 == 1){ //小さなメダル
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemLittlemedal)));
				}
			}
		}

		//イカスミコウモリ
		if(event.entityLiving instanceof EntityFlouncingSquid){
			if(r4 == 0){ //羽
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.feather)));
			} if(r20 == 0) { //イカスミ×4
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.dye, 4, 0)));
			} if(r50 == 0) { //レッドストーン×4
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.redstone, 4, 0)));
			} if(r100 == 0){ //スポーンエッグ(コウモリ)
				event.drops.add(new EntityItem(world, x, y, z, new ItemStack(Items.spawn_egg, 1, 65)));
			}

			if(AddonsHellMobMod.DQRAddon == true){
				if(r4 == 1){ //こうもりの羽根
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemKoumorinohane)));
				} if(r20 == 1) { //あやかし草
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemAyakasisou)));
				} if(r50 == 1) { //よるのとばり
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemYorunotobari)));
				} if(r100 == 1){ //小さなメダル
					event.drops.add(new EntityItem(world, x, y, z, new ItemStack(DQMiscs.itemLittlemedal)));
				}
			}
		}
	}
}
