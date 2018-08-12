package mc.raphaeliG.stone_powders.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class TileEntityMachine extends TileEntitySidedInventory implements ITickable, ICapabilityProvider{
	
	/**
	 * The number of ticks that the machine will keep working
	 */
	public int fuelTime = 0;
	/**
	 * The number of ticks that a fresh copy of the currently-used fuel item would keep the machine working
	 */
	public int currentItemFuelTime = 0;
    /**
     * The number of ticks the machine is working
     */
	public int progress = 0;
    /**
     * The number of ticks that a fresh copy of the currently-worked item would need to be worked for
     */
	public int totalProgress = 0;
	
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
	public TileEntityMachine(int[] slotsDown, int[] slotsUp, int[] slotsNorth, int[] slotsSouth,
			int[] slotsWest, int[] slotsEast) {
		super(slotsDown, slotsUp, slotsNorth, slotsSouth, slotsWest, slotsEast);
	}
	
	/**
	 * Override this method
	 */
	@Override
	protected boolean isStackValid(int slot, ItemStack stack) {
		return false;
	}
	
	/**
	 * Override and call super
	 */
	private boolean isWorking()
	{
		return fuelTime > 0;
	}
	
	/**
	 * Override this.
	 * This is the function being called to update the TE
	 */
	@Override
	public void update() {
		
	}
	
	/**
	 * Override this.
	 * Function is called when progress is maid
	 */
	public void progress() {
		
	}
	
	/**
	 * Override this.
	 * This method is used to know if progress can be maid
	 * @return true if progress can be maid, false otherwise
	 */
	public boolean canProgress() {
		return false;
	}
	
	/**
	 * Override and call super
	 */
	public void setTEData(int fuelTime, int currentItemFuelTime, int progress, int totalProgress)
	{
		this.fuelTime = fuelTime;
		this.currentItemFuelTime = currentItemFuelTime;
		this.progress = progress;
		this.totalProgress = progress;
	}
	
	/**
	 * Override and call super
	 */
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return (oldState.getBlock() != newState.getBlock());
	}
	
	/**
	 * Override and call super
	 */
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return super.getCapability(capability, facing);
	}
	
	/**
	 * Override and call super
	 */
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return super.hasCapability(capability, facing);
	}
	
	/**
	 * Override and call super
	 */
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		int metadata = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
	}
	
	/**
	 * Override and call super
	 */
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}
	
	/**
	 * Override and call super
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		fuelTime = nbt.getInteger("FuelTime");
		currentItemFuelTime = nbt.getInteger("CurrentItemFuelTime");
		progress = nbt.getInteger("Progress");
		totalProgress = nbt.getInteger("TotalProgress");
	}
	
	/**
	 * Override and call super
	 */
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("FuelTime", fuelTime);
		nbt.setInteger("CurrentItemFuelTime", currentItemFuelTime);
		nbt.setInteger("Progress", progress);
		nbt.setInteger("TotalProgress", totalProgress);
		return super.writeToNBT(nbt);
	}
	
	/**
	 * Override and call super
	 */
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}
	
	/**
	 * Override and call super
	 */
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}
	
	/**
	 * Override and call super
	 */
	@Override
	public NBTTagCompound getTileData() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}
}