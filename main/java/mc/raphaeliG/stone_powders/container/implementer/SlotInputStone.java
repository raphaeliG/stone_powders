package mc.raphaeliG.stone_powders.container.implementer;

import mc.raphaeliG.stone_powders.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotInputStone extends SlotItemHandler {

	public SlotInputStone(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return super.isItemValid(stack) && Block.getBlockFromItem(stack.getItem()) == BlockInit.POWDERLESS_STONE;
	}

}
