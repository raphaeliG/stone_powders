package mc.raphaeliG.stone_powders.util.handlers;

import mc.raphaeliG.stone_powders.init.BlockInit;
import mc.raphaeliG.stone_powders.init.ItemInit;
import mc.raphaeliG.stone_powders.tileentity.TileEntityDisimplementer;
import mc.raphaeliG.stone_powders.tileentity.TileEntityImplementer;
import mc.raphaeliG.stone_powders.util.Reference;
import mc.raphaeliG.stone_powders.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		GameRegistry.registerTileEntity(TileEntityDisimplementer.class, Reference.MODID + ":tileentity.disimplementer");
		GameRegistry.registerTileEntity(TileEntityImplementer.class, Reference.MODID + ":tileentity.implementer");
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for (Item item : ItemInit.ITEMS)
		{
			if (item instanceof IHasModel)
			{
				
				((IHasModel)item).registerModels();
			}
		}
		
		for (Block block : BlockInit.BLOCKS)
		{
			if (block instanceof IHasModel)
			{
				
				((IHasModel)block).registerModels();
			}
		}
	}
}
