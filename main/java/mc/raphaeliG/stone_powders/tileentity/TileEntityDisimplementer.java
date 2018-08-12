package mc.raphaeliG.stone_powders.tileentity;

import mc.raphaeliG.stone_powders.init.BlockInit;
import mc.raphaeliG.stone_powders.recipes.disimplementer.DisimplementerRecipeHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TileEntityDisimplementer extends TileEntityMachine {
	
	public static final int SLOT_INPUT_FUEL = 0;
	public static final int SLOT_INPUT_STONE = 1;
	public static final int SLOT_OUTPUT_STONE = 2;
	public static final int SLOT_OUTPUT_POWDER = 3;
	
	public static final int SLOTS_DOWN[] = new int[] {SLOT_OUTPUT_POWDER, SLOT_OUTPUT_STONE};
	public static final int SLOTS_UP[] = new int[] {SLOT_INPUT_STONE};
	public static final int SLOTS_NORTH[] = new int[] {SLOT_INPUT_FUEL};
	public static final int SLOTS_SOUTH[] = new int[] {SLOT_INPUT_FUEL};
	public static final int SLOTS_WEST[] = new int[] {SLOT_INPUT_FUEL};
	public static final int SLOTS_EAST[] = new int[] {SLOT_INPUT_FUEL};
	
	private ItemStack disimplementedItem = ItemStack.EMPTY;
	
	public TileEntityDisimplementer() {
		super(SLOTS_DOWN, SLOTS_UP, SLOTS_NORTH, SLOTS_SOUTH, SLOTS_WEST, SLOTS_EAST);
		progress = 0;
		totalProgress = 0;
		fuelTime = 0;
		currentItemFuelTime = 0;
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
		if (!world.isRemote)
		{
			if (handler.getStackInSlot(SLOT_INPUT_STONE).isEmpty())
			{
				progress = 0;
				totalProgress = 0;
			}
			else
			{
				totalProgress = DisimplementerRecipeHelper.getRecipeTime(handler.getStackInSlot(SLOT_INPUT_STONE));
				if (handler.getStackInSlot(SLOT_INPUT_STONE).getItem() != disimplementedItem.getItem()) // TODO: Add metadata check aswell
				{
					disimplementedItem = handler.getStackInSlot(SLOT_INPUT_STONE);
					progress = 0;
				}
			}
			
			if(isWorking())
			{
				--fuelTime;
				if (canProgress())
				{
					progress();
				}
			}
			else
			{
				if (!handler.getStackInSlot(SLOT_INPUT_FUEL).isEmpty() && canProgress())
				{
					totalProgress = DisimplementerRecipeHelper.getRecipeTime(handler.getStackInSlot(SLOT_INPUT_STONE));
					progress = 0;
					currentItemFuelTime = DisimplementerRecipeHelper.getFuelTime(handler.getStackInSlot(SLOT_INPUT_FUEL));
					fuelTime = currentItemFuelTime;
					handler.getStackInSlot(SLOT_INPUT_FUEL).shrink(1);
					progress();
				}
				else
				{
					currentItemFuelTime = 0;
					progress = 0;
					totalProgress = 0;
				}
			}
		}
	}
	
	@Override
	public void progress() {
		++progress;
		if (progress == totalProgress)
		{
			progress = 0;
			if (handler.getStackInSlot(SLOT_OUTPUT_POWDER).isEmpty())
			{
				handler.setStackInSlot(SLOT_OUTPUT_POWDER, new ItemStack(DisimplementerRecipeHelper.getRecipeOutput(handler.getStackInSlot(SLOT_INPUT_STONE)).getItem(), 4));
			}
			else
			{
				handler.getStackInSlot(SLOT_OUTPUT_POWDER).grow(4);
			}
			
			if (handler.getStackInSlot(SLOT_OUTPUT_STONE).isEmpty())
			{
				handler.setStackInSlot(SLOT_OUTPUT_POWDER, new ItemStack(BlockInit.POWDERLESS_STONE));
			}
			else
			{
				handler.getStackInSlot(SLOT_OUTPUT_POWDER).grow(1);
			}
			handler.getStackInSlot(SLOT_INPUT_STONE).shrink(1);
		}
	}
	
	@Override
	public boolean canProgress() {
		if(handler.getStackInSlot(SLOT_INPUT_STONE).isEmpty()) return false;
		if(handler.getStackInSlot(SLOT_OUTPUT_POWDER).isEmpty())
		{
			if(handler.getStackInSlot(SLOT_OUTPUT_STONE).isEmpty()) return true;
			return handler.getStackInSlot(SLOT_OUTPUT_STONE).getCount() + 1 <= 64;
		}
		if (DisimplementerRecipeHelper.getRecipeOutput(handler.getStackInSlot(SLOT_INPUT_STONE)).getItem()
				!= handler.getStackInSlot(SLOT_OUTPUT_POWDER).getItem()) return false;
		if(handler.getStackInSlot(SLOT_OUTPUT_STONE).isEmpty()) return handler.getStackInSlot(SLOT_OUTPUT_POWDER).getCount() + 4 <= 64;
		return handler.getStackInSlot(SLOT_OUTPUT_STONE).getCount() + 1 <= 64 && handler.getStackInSlot(SLOT_OUTPUT_POWDER).getCount() + 4 <= 64;
	}
}
