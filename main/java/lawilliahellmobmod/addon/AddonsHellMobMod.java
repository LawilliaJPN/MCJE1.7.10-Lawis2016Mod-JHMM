package lawilliahellmobmod.addon;

import cpw.mods.fml.common.Loader;
import lawilliahmt.HandyMobTools;

public class AddonsHellMobMod {
	public static boolean DQRAddon = false;

	public AddonsHellMobMod(){
		//DQRアドオン
		if(Loader.isModLoaded("DQMIIINext")){
			try{
				DQRAddon = true;

				new DQRAddonRecipes();
			} catch (Throwable t) {
				HandyMobTools.logger.warn("Failed to get items of DQMIIINext.");
			}
		}
	}
}
