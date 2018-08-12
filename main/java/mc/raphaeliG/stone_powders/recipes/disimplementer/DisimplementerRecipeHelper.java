package mc.raphaeliG.stone_powders.recipes.disimplementer;

import java.util.ArrayList;
import java.util.List;

import mc.raphaeliG.stone_powders.init.ItemInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class DisimplementerRecipeHelper {
	
	public static List<Recipe> recipes = new ArrayList<>();
	public static List<Fuel> fuels = new ArrayList<>();
	
	//recipes
	public static final Recipe OBSIDIAN = new Recipe(new ItemStack(Blocks.OBSIDIAN), new ItemStack(ItemInit.OBSIDIAN_POWDER), 120);
	//fuels
	public static final Fuel REDSTONE = new Fuel(new ItemStack(Items.REDSTONE), 100);
	
	public static boolean isRecipe(ItemStack stack) {
		return getRecipeTime(stack) > 0;
	}
	
	public static int getRecipeTime(ItemStack stack) {
		if (recipes.isEmpty()) return 0;
		for (Recipe recipe : recipes)
		{
			if (recipe.input.getItem() == stack.getItem() && recipe.input.getMetadata() == stack.getMetadata()) return recipe.time;
		}
		return 0;
	}
	
	public static ItemStack getRecipeOutput(ItemStack stack) {
		if (recipes.isEmpty()) return ItemStack.EMPTY;
		for (Recipe recipe : recipes)
		{
			if (recipe.input.getItem() == stack.getItem() && recipe.input.getMetadata() == stack.getMetadata()) return recipe.output;
		}
		return ItemStack.EMPTY;
	}
	
	public static boolean isFuel(ItemStack stack) {
		return getFuelTime(stack) > 0;
	}
	
	public static int getFuelTime(ItemStack stack) {
		if (fuels.isEmpty()) return 0;
		for (Fuel fuel : fuels)
		{
			if (fuel.fuel.getItem() == stack.getItem() && fuel.fuel.getMetadata() == stack.getMetadata()) return fuel.time;
		}
		return 0;
	}
}
