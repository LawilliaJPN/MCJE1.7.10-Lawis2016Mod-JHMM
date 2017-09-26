package lawilliahellmobmod.entity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import lawilliahellmobmod.JustForTheHellMobMod;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.biome.BiomeGenBase;

public class RegistryEntities {
	public RegistryEntities(){
		/*Entityの登録 引数
		 *registerModEntity(Class<? extends Entity> entityClass,
		 *String entityName, int id, Object mod, int trackingRange,
		 *int updateFrequency, boolean sendsVelocityUpdates)*/

		/*自然スポーンの設定 引数
         *addSpawn(Class<? extends EntityLiving> entityClass,
         *int weightedProb, int min, int max,
         *EnumCreatureType typeOfCreature, BiomeGenBase... biomes)*/

		//スポーン量メモ：マグマキューブ1、ガスト50、ゾンビピッグマン100

		//黒オオカミ Black Wolf
        //Entityの登録
      	EntityRegistry.registerModEntity(EntityBlackWolf.class, "BlackWolf", 355, JustForTheHellMobMod.MODID, 250, 1, true);
      	//自然スポーンの設定
      	EntityRegistry.addSpawn(EntityBlackWolf.class, 80, 1, 4, EnumCreatureType.monster, BiomeGenBase.hell);
      	//Entityのレンダーをクライアント側で追加
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
        	JustForTheHellMobMod.renderBlackWolf();
        }

        //ダークゴーレム Dark Golem 自然スポーンなし
      	//Entityの登録
      	EntityRegistry.registerModEntity(EntityDarkGolem.class, "DarkGolem", 399, JustForTheHellMobMod.MODID, 250, 1, true);
        //Entityのレンダーをクライアント側で追加
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
        	JustForTheHellMobMod.renderDarkGolem();
        }

        //水晶イカ Flouncing Squid
      	//Entityの登録
      	EntityRegistry.registerModEntity(EntityFlouncingSquid.class, "FlouncingSquid", 394, JustForTheHellMobMod.MODID, 250, 1, true);
      	//自然スポーンの設定
        EntityRegistry.addSpawn(EntityFlouncingSquid.class, 30, 1, 4, EnumCreatureType.monster, BiomeGenBase.hell);
        //Entityのレンダーをクライアント側で追加
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
        	JustForTheHellMobMod.renderFlouncingSquid();
        }

		//ヘルクリーパー Hell Creeper
		//Entityの登録
		EntityRegistry.registerModEntity(EntityHellCreeper.class, "HellCreeper", 310, JustForTheHellMobMod.MODID, 250, 1, true);
		//自然スポーンの設定
        EntityRegistry.addSpawn(EntityHellCreeper.class, 120, 1, 4, EnumCreatureType.monster, BiomeGenBase.hell);
        //Entityのレンダーをクライアント側で追加
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
        	JustForTheHellMobMod.renderHellCreeper();
        }

        //ネザーウィッチ Nether Witch 自然スポーンなし
        //Entityの登録
        EntityRegistry.registerModEntity(EntityNetherWitch.class, "NetherWitch", 366, JustForTheHellMobMod.MODID, 250, 1, true);
        //Entityのレンダーをクライアント側で追加
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
        	JustForTheHellMobMod.renderNetherWitch();
        }

        //ネザーゾンビ Nether Zombie
        //Entityの登録
        EntityRegistry.registerModEntity(EntityNetherZombie.class, "NetherZombie", 354, JustForTheHellMobMod.MODID, 250, 1, true);
  		//自然スポーンの設定
        EntityRegistry.addSpawn(EntityNetherZombie.class, 40, 1, 4, EnumCreatureType.monster, BiomeGenBase.hell);
        //Entityのレンダーをクライアント側で追加
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
        	JustForTheHellMobMod.renderNetherZombie();
        }

        //イカスミコウモリ Squid Ink Bat 自然スポーンなし
      	//Entityの登録
      	EntityRegistry.registerModEntity(EntitySquidInkBat.class, "SquidInkBat", 365, JustForTheHellMobMod.MODID, 250, 1, true);
        //Entityのレンダーをクライアント側で追加
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
        	JustForTheHellMobMod.renderSquidInkBat();
        }


        //バニラmobのスポーン追加
        EntityRegistry.addSpawn(EntityIronGolem.class, 10, 1, 2, EnumCreatureType.monster, BiomeGenBase.hell);
        EntityRegistry.addSpawn(EntityVillager.class, 10, 1, 2, EnumCreatureType.monster, BiomeGenBase.hell);
        EntityRegistry.addSpawn(EntityMagmaCube.class, 10, 1, 2, EnumCreatureType.monster, BiomeGenBase.hell);
	}
}