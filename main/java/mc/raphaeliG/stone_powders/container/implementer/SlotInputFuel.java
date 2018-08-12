package mc.raphaeliG.stone_powders.container.implementer;

import mc.raphaeliG.stone_powders.recipes.disimplementer.DisimplementerRecipeHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotInputFuel extends SlotItemHandler {

	public SlotInputFuel(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return super.isItemValid(stack) && DisimplementerRecipeHelper.isFuel(stack);
	}

}
