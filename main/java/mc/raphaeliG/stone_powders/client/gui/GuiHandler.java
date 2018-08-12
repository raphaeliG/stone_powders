package mc.raphaeliG.stone_powders.client.gui;

import mc.raphaeliG.stone_powders.container.disimplementer.ContainerDisimplementer;
import mc.raphaeliG.stone_powders.container.implementer.ContainerImplementer;
import mc.raphaeliG.stone_powders.tileentity.TileEntityDisimplementer;
import mc.raphaeliG.stone_powders.tileentity.TileEntityImplementer;
import mc.raphaeliG.stone_powders.tileentity.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public static final int POWDER_DISIMPLEMETNER = 0;
	public static final int POWDER_IMPLEMETNER = 1;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case POWDER_DISIMPLEMETNER:
			return new ContainerDisimplementer(player.inventory, (TileEntityDisimplementer) world.getTileEntity(new BlockPos(x, y, z)));
		case POWDER_IMPLEMETNER:
			return new ContainerImplementer(player.inventory, (TileEntityImplementer) world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case POWDER_DISIMPLEMETNER:
			return new GuiPowderDisimplementer(player.inventory, (TileEntityDisimplementer) world.getTileEntity(new BlockPos(x, y, z)));
		case POWDER_IMPLEMETNER:
			return new GuiPowderImplementer(player.inventory, (TileEntityImplementer) world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
	}

}
