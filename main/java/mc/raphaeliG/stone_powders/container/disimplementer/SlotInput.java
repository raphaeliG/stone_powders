package mc.raphaeliG.stone_powders.container.disimplementer;

import mc.raphaeliG.stone_powders.init.BlockInit;
import mc.raphaeliG.stone_powders.recipes.ImplementerRecipeHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotInput extends SlotItemHandler {
	
	public SlotInput(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		Block temp = Block.getBlockFromItem(stack.getItem());
		return super.isItemValid(stack) && temp == Blocks.STONE ||
				temp == Blocks.OBSIDIAN || temp == Blocks.NETHERRACK || temp == Blocks.END_STONE;
	}
}
