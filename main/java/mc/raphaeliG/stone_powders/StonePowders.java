package mc.raphaeliG.stone_powders;

import mc.raphaeliG.stone_powders.client.gui.GuiHandler;
import mc.raphaeliG.stone_powders.network.PacketHandler;
import mc.raphaeliG.stone_powders.proxy.CommonProxy;
import mc.raphaeliG.stone_powders.util.Reference;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;


@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class StonePowders
{
	
	@Instance
	public static StonePowders instance;
	
	@SidedProxy(modId=Reference.MODID,clientSide=Reference.CLIENT_PROXY, serverSide=Reference.COMMON_PROXY)
	public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	Reference.logger = event.getModLog();
    	Reference.logger.info("Pre Initialize");
    	PacketHandler.registerMessages(Reference.MODID);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
    	Reference.logger.info("Initialize");
    	NetworkRegistry.INSTANCE.registerGuiHandler(StonePowders.instance, new GuiHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	Reference.logger.info("Post Initialize");
    }
}
