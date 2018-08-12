package mc.raphaeliG.stone_powders.tileentity;

import mc.raphaeliG.stone_powders.recipes.disimplementer.DisimplementerRecipeHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TileEntityImplementer extends TileEntityMachine {
	
	public static final int SLOT_INPUT_FUEL = 0;
	public static final int SLOT_INPUT_STONE = 1;
	public static final int SLOT_INPUT_POWDER = 2;
	public static final int SLOT_OUTPUT_STONE = 3;
	
	public static final int SLOTS_DOWN[] = new int[] {SLOT_OUTPUT_STONE};
	public static final int SLOTS_UP[] = new int[] {SLOT_INPUT_POWDER};
	public static final int SLOTS_NORTH[] = new int[] {SLOT_INPUT_FUEL};
	public static final int SLOTS_SOUTH[] = new int[] {SLOT_INPUT_FUEL};
	public static final int SLOTS_WEST[] = new int[] {SLOT_INPUT_STONE};
	public static final int SLOTS_EAST[] = new int[] {SLOT_INPUT_STONE};
	
	public TileEntityImplementer() {
		super(SLOTS_DOWN, SLOTS_UP, SLOTS_NORTH, SLOTS_SOUTH, SLOTS_WEST, SLOTS_EAST);
	}
	
	@Override
	protected boolean isStackValid(int slot, ItemStack stack) {
		switch (slot)
		{
		case SLOT_INPUT_FUEL:
			return DisimplementerRecipeHelper.isFuel(stack);
		case SLOT_INPUT_STONE:
			//CHECK if stack is stone
			Block temp = Block.getBlockFromItem(stack.getItem());
			return temp == Blocks.STONE || temp == Blocks.OBSIDIAN || temp == Blocks.NETHERRACK || temp == Blocks.END_STONE;
		default:
			return false;
		}
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void progress() {
		
	}
	
	@Override
	public boolean canProgress() {
		return false;
	}

}
