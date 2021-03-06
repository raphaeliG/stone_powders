package mc.raphaeliG.stone_powders.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

/**
 * A simple {@link net.minecraft.tileentity.TileEntity} which has a sided
 * inventory using capabilities
 *
 * @author CJMinecraft
 */
public class TileEntitySidedInventory extends TileEntity {

	/**
	 * The holder of all the item stacks
	 */
	protected SidedItemStackMainHandler handler;
	/**
	 * All of the handlers for each side
	 */
	private SidedItemStackHandler northHandler, southHandler, eastHandler, westHandler, upHandler, downHandler;
	/**
	 * The slots each side will represent
	 */
	private int[][] slotsForFace;

	/**
	 * Create a sided {@link TileEntity}, like the
	 * {@link net.minecraft.inventory.ISidedInventory interface}
	 *
	 * @param slotsDown
	 *            The slots which are represented on the down face
	 * @param slotsUp
	 *            The slots which are represented on the up face
	 * @param slotsNorth
	 *            The slots which are represented on the north face
	 * @param slotsSouth
	 *            The slots which are represented on the south face
	 * @param slotsWest
	 *            The slots which are represented on the west face
	 * @param slotsEast
	 *            The slots which are represented on the east face
	 */
	public TileEntitySidedInventory(int[] slotsDown, int[] slotsUp, int[] slotsNorth, int[] slotsSouth,
			int[] slotsWest, int[] slotsEast) {
		this.slotsForFace = new int[][] { slotsDown, slotsUp, slotsNorth, slotsSouth, slotsWest, slotsEast };
		this.handler = new SidedItemStackMainHandler(calculateSizeOfMainHandler());
		this.downHandler = new SidedItemStackHandler(slotsDown.length, EnumFacing.DOWN);
		this.upHandler = new SidedItemStackHandler(slotsUp.length, EnumFacing.UP);
		this.northHandler = new SidedItemStackHandler(slotsNorth.length, EnumFacing.NORTH);
		this.southHandler = new SidedItemStackHandler(slotsSouth.length, EnumFacing.SOUTH);
		this.westHandler = new SidedItemStackHandler(slotsWest.length, EnumFacing.WEST);
		this.eastHandler = new SidedItemStackHandler(slotsEast.length, EnumFacing.EAST);
	}

	/**
	 * Calculate the size of the main item handler
	 *
	 * @return the calculated size
	 */
	protected int calculateSizeOfMainHandler() {
		int lastSlot = 0;
		for (int[] slots : this.slotsForFace) {
			for (int slot : slots) {
				if (slot > lastSlot)
					lastSlot = slot;
			}
		}
		return lastSlot + 1;
	}

	/**
	 * Get the correct handler for the given face
	 *
	 * @param side
	 *            The side to get the handler of
	 * @return the correct handler for the given face
	 */
	private ItemStackHandler getHandlerForFace(@Nullable EnumFacing side) {
		if (side == null)
			return this.handler;
		switch (side) {
		case DOWN:
			return this.downHandler;
		case UP:
			return this.upHandler;
		case NORTH:
			return this.northHandler;
		case SOUTH:
			return this.southHandler;
		case WEST:
			return this.westHandler;
		case EAST:
			return this.eastHandler;
		default:
			return this.handler;
		}
	}
	
	/**
	 * Returns whether the stack in the given slot is valid
	 * 
	 * @param slot
	 *            The slot in which the item stack is in
	 * @param stack
	 *            The item stack which is in the slot
	 * @return whether the stack in the given slot is valid
	 */
	protected boolean isStackValid(int slot, ItemStack stack) {
		return true;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) getHandlerForFace(facing);
		return super.getCapability(capability, facing);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.handler.deserializeNBT(nbt.getCompoundTag("Inventory"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("Inventory", this.handler.serializeNBT());
		return super.writeToNBT(nbt);
	}

	/**
	 * Makes sure the client has all the data it needs
	 */
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		writeToNBT(nbtTag);
		return new SPacketUpdateTileEntity(this.pos, 1, nbtTag);
	}

	/**
	 * Make sure to read the client data when it receives the update packet
	 */
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	/**
	 * Returns the tag with all of the client data saved
	 */
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = super.getUpdateTag();
		writeToNBT(nbt);
		return nbt;
	}

	/**
	 * Handles when you get an update
	 */
	@Override
	public void handleUpdateTag(NBTTagCompound nbt) {
		readFromNBT(nbt);
	}

	/**
	 * Gets the tile entities nbt with all of the data stored in it
	 */
	@Override
	public NBTTagCompound getTileData() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}

	public class SidedItemStackMainHandler extends ItemStackHandler {

		public SidedItemStackMainHandler(int size) {
			super(size);
		}
		
		@Override
		protected void onContentsChanged(int slot) {
			for (int i = 0; i < slotsForFace.length; i++) {
				for (int j = 0; j < slotsForFace[i].length; j++) {
					if (slot == slotsForFace[i][j]) {
						switch (i) {
						case 0:
							downHandler.setStackInSlotInternal(j, handler.getStackInSlot(slot));
							break;
						case 1:
							upHandler.setStackInSlotInternal(j, handler.getStackInSlot(slot));
							break;
						case 2:
							northHandler.setStackInSlotInternal(j, handler.getStackInSlot(slot));
							break;
						case 3:
							southHandler.setStackInSlotInternal(j, handler.getStackInSlot(slot));
							break;
						case 4:
							westHandler.setStackInSlotInternal(j, handler.getStackInSlot(slot));
							break;
						case 5:
							eastHandler.setStackInSlotInternal(j, handler.getStackInSlot(slot));
							break;
						}
					}
				}
			}
		}
	}

	private class SidedItemStackHandler extends ItemStackHandler {

		private EnumFacing side;

		public SidedItemStackHandler(int size, EnumFacing side) {
			super(size);
			this.side = side;
		}
		
		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			if (!isStackValid(slotsForFace[this.side.getIndex()][slot], stack))
				return stack;
			return super.insertItem(slot, stack, simulate);
		}
		
		public void setStackInSlotInternal(int slot, ItemStack stack) {
			validateSlotIndex(slot);
	        if (ItemStack.areItemStacksEqual(this.stacks.get(slot), stack))
	            return;
	        this.stacks.set(slot, stack);
		}

		@Override
		protected void onContentsChanged(int slot) {
			handler.setStackInSlot(slotsForFace[this.side.getIndex()][slot], getStackInSlot(slot));
		}

	}
}