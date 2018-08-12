package mc.raphaeliG.stone_powders.container.implementer;

import mc.raphaeliG.stone_powders.util.interfaces.IStonePowder;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotInputPowder extends SlotItemHandler {

	public SlotInputPowder(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return super.isItemValid(stack) && stack.getItem() instanceof IStonePowder;
	}

}
