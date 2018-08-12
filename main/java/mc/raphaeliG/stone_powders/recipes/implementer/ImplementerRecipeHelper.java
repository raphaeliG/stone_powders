package mc.raphaeliG.stone_powders.recipes.implementer;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class ImplementerRecipeHelper {
	
	public static List<Recipe> recipes = new ArrayList<>();
	public static List<Fuel> fuels = new ArrayList<>();
	
	//recipes
	
	//fuels
	
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
