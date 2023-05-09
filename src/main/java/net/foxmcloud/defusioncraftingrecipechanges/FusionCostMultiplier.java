package net.foxmcloud.defusioncraftingrecipechanges;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.brandon3055.brandonscore.registry.ModConfigContainer;
import com.brandon3055.brandonscore.registry.ModConfigProperty;
import com.brandon3055.draconicevolution.api.fusioncrafting.FusionRecipeAPI;
import com.brandon3055.draconicevolution.api.fusioncrafting.IFusionRecipe;
import com.brandon3055.draconicevolution.api.fusioncrafting.SimpleFusionRecipe;
import com.brandon3055.draconicevolution.api.itemupgrade.FusionUpgradeRecipe;
import com.brandon3055.draconicevolution.utils.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@ModConfigContainer(modid = DEFusionCraftingRecipeChanges.MODID)
public class FusionCostMultiplier {
	@ModConfigProperty(category = "Main Settings", name = "Fusion Crafting Cost Multiplier", comment = "Allows you to adjust the multiplier on fusion crafting. This config is NOT automatically updated when you join another server, and you must restart Minecraft for this config to take effect.", requiresMCRestart = true, requiresSync = true)
	@ModConfigProperty.MinMax(min = "0", max = "10")
	public static double POWER_COST_MULTIPLIER = 1.0D;

	/**
	 * None of the default recipes define ingredients using an ItemStack.<br>
	 * Most of the ingredients will be found as Items.<br>
	 * A few of the ingredients are Blocks, and only one ingredient is an Ore Dictionary tag; "gemDiamond".<br>
	 * <br>
	 * You can see where the recipes are defined by looking at {@link com.brandon3055.draconicevolution.items.ToolUpgrade#registerRecipe(String, int)}.<br>
	 * To visit the above link easily in Eclipse, hover over the {@link #postInit()} function either here or below, then click on the link.
	 * From there, simply go down to the 4th option with the arrow pointing inwards towards a document, then click the icon.
	 */
	public static void postInit() {
		List<IFusionRecipe> recipes = FusionRecipeAPI.getRecipes();
		DEFusionCraftingRecipeChanges.logger.log(Level.INFO, "Starting takeover of Draconic Evolution. Prepare thyself :)");
		LogHelper.info("no no no no NOT AGAI-!!!");
		LogHelper.info("Ha! Too easy. Beginning recipe rebalancing at power level " + POWER_COST_MULTIPLIER + " and editing some additional recipes, just for fun.  You like fun, right?");
		for (IFusionRecipe oldRecipe : recipes) {
			if (!oldRecipe.getRecipeCatalyst().isEmpty() || oldRecipe instanceof FusionUpgradeRecipe) {
				LogHelper.dev(oldRecipe.getRecipeOutput(oldRecipe.getRecipeCatalyst()).getItem().getUnlocalizedName() + " was " + oldRecipe.getIngredientEnergyCost());
				FusionRecipeAPI.removeRecipe(oldRecipe);

				IFusionRecipe newRecipe;
				ItemStack output = oldRecipe.getRecipeOutput(oldRecipe.getRecipeCatalyst());
				ItemStack catalyst = oldRecipe.getRecipeCatalyst();
				long rfCost = (long)(oldRecipe.getIngredientEnergyCost() * POWER_COST_MULTIPLIER);
				int tier = oldRecipe.getRecipeTier();
				List ingredients = oldRecipe.getRecipeIngredients();

				if (oldRecipe instanceof FusionUpgradeRecipe) {
					FusionUpgradeRecipe oldUpgradeRecipe = (FusionUpgradeRecipe)oldRecipe;
					List newIngredients = new ArrayList();
					// For each ingredient...
					for (int i = 0; i < ingredients.size(); i++) {
						Object obj = ingredients.get(i);

						// This is where you'll change the ingredients based on criteria.
						if (obj.equals(Items.GOLDEN_APPLE)) {
							obj = Items.APPLE;
						}
						else if (obj.equals(Blocks.DRAGON_EGG)) {
							obj = "ingotGold";
						}
						else if (obj.equals(Blocks.EMERALD_BLOCK)) {
							obj = Blocks.ANVIL;
						}
						else if (obj.equals("gemDiamond")) {
							obj = Items.ARROW;
						}

						// We don't want to re-add the upgrade key, since it gets added dynamically into the recipe when you make a new FusionUpgradeRecipe.
						if (!obj.equals(oldUpgradeRecipe.upgradeKey)) {
							newIngredients.add(obj);
						}
					}
					newRecipe = new FusionUpgradeRecipe(oldUpgradeRecipe.upgrade, oldUpgradeRecipe.upgradeKey, rfCost, tier, oldUpgradeRecipe.upgradeLevel, newIngredients.toArray());
				}
				else {
					newRecipe = new SimpleFusionRecipe(output, catalyst, rfCost, tier, ingredients);
				}
				FusionRecipeAPI.addRecipe(newRecipe);
				if (!FusionRecipeAPI.getRecipes().contains(newRecipe)) {
					LogHelper.warn("One of the recipes in the fusion crafting database cannot be changed.  Attempting to put old recipe back...");
					FusionRecipeAPI.addRecipe(oldRecipe);
					if (!FusionRecipeAPI.getRecipes().contains(oldRecipe)) {
						LogHelper.error("Something went terribly wrong and the fusion crafting recipe database is damaged beyond repair.  Cannot continue.");
						throw new Error("The fusion crafting recipe database was damaged beyond repair when attempting to modify the recipes contained within.");
					}
					LogHelper.warn("The old recipe was successfully placed back into the registry.");
				}
				else LogHelper.dev(newRecipe.getRecipeOutput(newRecipe.getRecipeCatalyst()).getItem().getUnlocalizedName() + " now " + newRecipe.getIngredientEnergyCost());
			}
			else {
				if (!oldRecipe.getRecipeOutput(oldRecipe.getRecipeCatalyst()).getItem().getUnlocalizedName().contains("air"))
					LogHelper.dev("Recipe with catalyst " + oldRecipe.getRecipeOutput(oldRecipe.getRecipeCatalyst()).getItem().getUnlocalizedName() + " is not compatible.  Skipping... ");
			}
		}
		LogHelper.info("Recipe rebalancing complete. Returning control of Draconic Evolution.");
		LogHelper.info("... im done with this. can you addons just leave me alone for once?");
		DEFusionCraftingRecipeChanges.logger.log(Level.INFO, "Nope :)");
		LogHelper.info("figures...");
	}
}
