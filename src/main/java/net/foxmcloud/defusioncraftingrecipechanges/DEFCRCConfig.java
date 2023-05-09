package net.foxmcloud.defusioncraftingrecipechanges;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.brandon3055.brandonscore.handlers.FileHandler;
import com.brandon3055.brandonscore.registry.IModConfigHelper;
import com.brandon3055.brandonscore.registry.ModConfigContainer;

import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModConfigContainer(modid = DEFusionCraftingRecipeChanges.MODID)
public class DEFCRCConfig implements IModConfigHelper {

	public static Map<String, String> comments = new HashMap<String, String>();
	
	static {
		comments.put("Main Settings", "Allows you to tweak the settings for this mod.");
	}

	@Override
	public Configuration createConfiguration(FMLPreInitializationEvent event) {
		return new Configuration(new File(FileHandler.brandon3055Folder, "DEFusionCraftingRecipeChanges.cfg"), true);
	}

	@Override
	public String getCategoryComment(String category) {
		return comments.getOrDefault(category, "");
	}

	@Mod.EventBusSubscriber
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(DEFusionCraftingRecipeChanges.MODID)) {
				ConfigManager.sync(DEFusionCraftingRecipeChanges.MODID, Type.INSTANCE);
			}
		}
	}
}
