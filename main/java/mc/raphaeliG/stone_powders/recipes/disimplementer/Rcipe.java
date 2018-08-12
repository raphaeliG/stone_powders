package mc.raphaeliG.stone_powders.recipes.disimplementer;

import net.minecraft.item.ItemStack;

class Recipe
{
	public ItemStack input;
	public ItemStack output;
	public int time;
	public Recipe(ItemStack input, ItemStack output, int time) {
		this.input = input;
		this.output = output;
		this.time = time;
		DisimplementerRecipeHelper.recipes.add(this);
	}
}