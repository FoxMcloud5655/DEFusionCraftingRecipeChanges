package net.foxmcloud.defusioncraftingrecipechanges;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.brandon3055.draconicevolution.utils.LogHelper;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = DEFusionCraftingRecipeChanges.MODID, name = DEFusionCraftingRecipeChanges.NAME, version = DEFusionCraftingRecipeChanges.VERSION, dependencies = DEFusionCraftingRecipeChanges.dependencies)
public class DEFusionCraftingRecipeChanges {
	public static final String MODID = "defusioncraftingrecipechanges";
	public static final String NAME = "Draconic Evolution Fusion Crafting Recipe Changes";
	public static final String PROXY_COMMON = "net.foxmcloud.defusioncraftingrecipechanges.CommonProxy";
	public static final String VERSION = "${mod_version}";
	public static final String MODID_PREFIX = MODID + ":";
	public static final String dependencies = "required-after:draconicevolution";
	public static SimpleNetworkWrapper network;

	public static Logger logger = LogManager.getLogger(DEFusionCraftingRecipeChanges.MODID);

	@Mod.Instance(DEFusionCraftingRecipeChanges.MODID)
	public static DEFusionCraftingRecipeChanges instance;

	@SidedProxy(clientSide = DEFusionCraftingRecipeChanges.PROXY_COMMON, serverSide = DEFusionCraftingRecipeChanges.PROXY_COMMON)
	public static CommonProxy proxy;

	public DEFusionCraftingRecipeChanges() {
		if (Loader.isModLoaded("draconicevolution")) {
			logger.log(Level.INFO, "Ah...  Draconic Evolution.  I'm going to enjoy dissecting you.");
			LogHelper.info("no no no no no no no");

		}
		else {
			logger.log(Level.INFO, "Excuse me?  I can't modify Draconic Evolution if he's not here, baka.");
			throw new Error("Draconic Evolution is not loaded.  It is required for this mod to work.");
		}
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
