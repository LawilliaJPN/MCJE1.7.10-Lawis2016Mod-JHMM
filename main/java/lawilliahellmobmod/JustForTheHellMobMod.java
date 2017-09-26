package lawilliahellmobmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lawilliahellmobmod.addon.AddonsHellMobMod;
import lawilliahellmobmod.block.MonsterEggs;
import lawilliahellmobmod.entity.EntityBlackWolf;
import lawilliahellmobmod.entity.EntityDarkGolem;
import lawilliahellmobmod.entity.EntityFlouncingSquid;
import lawilliahellmobmod.entity.EntityHellCreeper;
import lawilliahellmobmod.entity.EntityNetherWitch;
import lawilliahellmobmod.entity.EntityNetherZombie;
import lawilliahellmobmod.entity.EntitySquidInkBat;
import lawilliahellmobmod.entity.RegistryEntities;
import lawilliahellmobmod.entity.model.RenderBlackWolf;
import lawilliahellmobmod.entity.model.RenderDarkGolem;
import lawilliahellmobmod.entity.model.RenderFlouncingSquid;
import lawilliahellmobmod.entity.model.RenderHellCreeper;
import lawilliahellmobmod.entity.model.RenderNetherWitch;
import lawilliahellmobmod.entity.model.RenderNetherZombie;
import lawilliahellmobmod.entity.model.RenderSquidInkBat;
import lawilliahellmobmod.event.DropItemHandler;
import lawilliahellmobmod.event.SpawnDarkGolem;
import lawilliahellmobmod.item.ItemSpawnEggs;
import lawilliahellmobmod.item.ItemSwords;
import lawilliahellmobmod.item.JHMMRecipes;
import lawilliahellmobmod.world.GeneratorMonsterEggs;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = JustForTheHellMobMod.MODID, name = JustForTheHellMobMod.MODNAME, version = JustForTheHellMobMod.VERSION)

public class JustForTheHellMobMod {
	public static final String MODID = "justforthehellmobmod";
	public static final String MODNAME = "Just for the Hell Mob Mod";
	public static final String VERSION = "1.0";
	public static Logger logger = LogManager.getLogger("justfortthehellmobmod");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		//スポーンエッグの追加
		new ItemSpawnEggs();
		//モンスターエッグの追加
		new MonsterEggs();
		//装備の追加
		new ItemSwords();
	}

	@EventHandler
	public void init(FMLInitializationEvent event){
		//レシピの追加
		new JHMMRecipes();
		//mobの追加
		new RegistryEntities();
		//モンスターエッグのワールド生成
		GameRegistry.registerWorldGenerator(new GeneratorMonsterEggs(), 0);
		//モンスターのドロップ追加
		MinecraftForge.EVENT_BUS.register(new DropItemHandler());
		//ダークゴーレムのスポーン追加
		MinecraftForge.EVENT_BUS.register(new SpawnDarkGolem());
	}

	@EventHandler
	public void postInit (FMLPostInitializationEvent event){
		//他modとの連携
		new AddonsHellMobMod();
	}


	//以下、追加Entityの見た目の追加
	@SideOnly(Side.CLIENT) //黒オオカミ
	public static void renderBlackWolf(){
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackWolf.class, new RenderBlackWolf());
	}

	@SideOnly(Side.CLIENT) //ダークゴーレム
	public static void renderDarkGolem(){
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkGolem.class, new RenderDarkGolem());
	}

	@SideOnly(Side.CLIENT) //水晶イカ
	public static void renderFlouncingSquid(){
		RenderingRegistry.registerEntityRenderingHandler(EntityFlouncingSquid.class, new RenderFlouncingSquid());
	}

	@SideOnly(Side.CLIENT) //ネザーウィッチ
	public static void renderNetherWitch(){
		RenderingRegistry.registerEntityRenderingHandler(EntityNetherWitch.class, new RenderNetherWitch());
	}

	@SideOnly(Side.CLIENT) //ネザーゾンビ
	public static void renderNetherZombie(){
		RenderingRegistry.registerEntityRenderingHandler(EntityNetherZombie.class, new RenderNetherZombie());
	}

	@SideOnly(Side.CLIENT) //ヘルクリーパー
	public static void renderHellCreeper(){
		RenderingRegistry.registerEntityRenderingHandler(EntityHellCreeper.class, new RenderHellCreeper());
	}

	@SideOnly(Side.CLIENT) //イカスミコウモリ
	public static void renderSquidInkBat(){
		RenderingRegistry.registerEntityRenderingHandler(EntitySquidInkBat.class, new RenderSquidInkBat());
	}
}
