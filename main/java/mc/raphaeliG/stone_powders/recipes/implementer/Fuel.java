package mc.raphaeliG.stone_powders.recipes.implementer;

import net.minecraft.item.ItemStack;

public class Fuel
{
	public ItemStack fuel;
	public int time;
	public Fuel(ItemStack fuel, int time) {
		this.fuel = fuel;
		this.time = time;
		ImplementerRecipeHelper.fuels.add(this);
	}
}