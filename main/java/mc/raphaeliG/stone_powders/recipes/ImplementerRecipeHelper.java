package mc.raphaeliG.stone_powders.recipes;

import java.util.List;

import mc.raphaeliG.stone_powders.init.ItemInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ImplementerRecipeHelper {
	
	public static List<Recipe> recipes;
	public static List<Fuel> fuels;
	
	//recipes
	
	//fuels
	
	public static boolean isRecipe(ItemStack stack) {
		return getRecipeTime(stack) > 0;
	}
	
	public static int getRecipeTime(ItemStack stack) {
		for (Recipe recipe : recipes)
		{
			if (recipe.input.getItem() == stack.getItem() && recipe.input.getMetadata() == stack.getMetadata()) return recipe.time;
		}
		return 0;
	}
	
	public static ItemStack getRecipeOutput(ItemStack stack) {
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
		for (Fuel fuel : fuels)
		{
			if (fuel.fuel.getItem() == stack.getItem() && fuel.fuel.getMetadata() == stack.getMetadata()) return fuel.time;
		}
		return 0;
	}
	
	static class Recipe
	{
		public ItemStack input;
		public ItemStack output;
		public int time;
		public Recipe(ItemStack input, ItemStack output, int time) {
			this.input = input;
			this.output = output;
			this.time = time;
			recipes.add(this);
		}
	}
	
	static class Fuel
	{
		public ItemStack fuel;
		public int time;
		public Fuel(ItemStack fuel, int time) {
			this.fuel = fuel;
			this.time = time;
			fuels.add(this);
		}
	}
}
