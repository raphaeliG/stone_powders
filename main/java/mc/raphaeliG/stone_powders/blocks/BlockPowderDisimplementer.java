package mc.raphaeliG.stone_powders.blocks;

import mc.raphaeliG.stone_powders.StonePowders;
import mc.raphaeliG.stone_powders.blocks.templates.BlockBase;
import mc.raphaeliG.stone_powders.client.gui.GuiHandler;
import mc.raphaeliG.stone_powders.init.CreativeTabInit;
import mc.raphaeliG.stone_powders.tileentity.TileEntityDisimplementer;
import mc.raphaeliG.stone_powders.tileentity.TileEntityMachine;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockPowderDisimplementer extends BlockBase implements ITileEntityProvider{
	
	public BlockPowderDisimplementer() {
		super("powder_disimplementer", Material.IRON, CreativeTabInit.MAIN, 3.5F, "pickaxe", 2, 17.5F, SoundType.STONE);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityDisimplementer();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		IItemHandler handler = ((TileEntityMachine)worldIn.getTileEntity(pos)).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for (int i = 0; i < handler.getSlots(); ++i)
		{
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(i));
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote)
		{
			playerIn.openGui(StonePowders.instance, GuiHandler.POWDER_DISIMPLEMETNER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
}
