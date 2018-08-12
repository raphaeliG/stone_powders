package mc.raphaeliG.stone_powders.recipes.implementer;

import net.minecraft.item.ItemStack;

public class Recipe
{
	public ItemStack input;
	public ItemStack output;
	public int time;
	public Recipe(ItemStack input, ItemStack output, int time) {
		this.input = input;
		this.output = output;
		this.time = time;
		ImplementerRecipeHelper.recipes.add(this);
	}
}