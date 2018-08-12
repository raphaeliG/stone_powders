package mc.raphaeliG.stone_powders.client.gui;

import mc.raphaeliG.stone_powders.container.disimplementer.ContainerDisimplementer;
import mc.raphaeliG.stone_powders.network.PacketGetData;
import mc.raphaeliG.stone_powders.network.PacketHandler;
import mc.raphaeliG.stone_powders.tileentity.TileEntityDisimplementer;
import mc.raphaeliG.stone_powders.tileentity.TileEntityMachine;
import mc.raphaeliG.stone_powders.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiPowderDisimplementer extends GuiContainer {
	
	private IInventory playerInventory;
	private TileEntityDisimplementer tileEntity;
	
	public static int fuelTime = 0, currentItemfuelTime = 0, disimplementTime = 0, totalDisimplementTime = 0;
	
	public GuiPowderDisimplementer(IInventory playerInventory, TileEntityDisimplementer tileEntity) {
		super(new ContainerDisimplementer(playerInventory, tileEntity));
		this.playerInventory = playerInventory;
		this.tileEntity = tileEntity;
		tileEntity.progress = 0;
		tileEntity.totalProgress = 0;
		tileEntity.fuelTime = 0;
		tileEntity.currentItemFuelTime = 0;
		tileEntity.setTEData(0, 0, 0, 0);
		xSize = 176;
		ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		drawDefaultBackground();
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/container/powder_disimplementer.png"));
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        PacketHandler.INSTANCE.sendToServer(new PacketGetData(tileEntity.getPos(),
        		"mc.raphaeliG.stone_powders.client.gui.GuiPowderDisimplementer", "fuelTime", "currentItemfuelTime", "disimplementTime",
        		"totalDisimplementTime"));
        tileEntity.setTEData(fuelTime, currentItemfuelTime, disimplementTime, totalDisimplementTime);
        GlStateManager.pushMatrix();
        GlStateManager.translate(guiLeft, guiTop, 2);
        if (tileEntity.currentItemFuelTime != 0)
        {
        	int percentage = (int) (((float) tileEntity.fuelTime / tileEntity.currentItemFuelTime) * 100);
        	for (int i = 0; i < 16; ++i)
        	{
        		if (percentage > ((float)100 / 16) * i)
        		{
        			drawTexturedModalRect(37, 50 - i, 200, 16 - i, 18, 1);
        		}
        	}
        }
        if (tileEntity.totalProgress != 0)
        {
        	int percentage = (int) (((float) tileEntity.progress / tileEntity.totalProgress) * 100);
        	for (int i = 0; i < 22; ++i)
        	{
        		if (percentage > ((float)100 / 22) * i)
        		{
        			drawTexturedModalRect(80 + i, 35, 177 + i, 22, 1, 16);
        		}
        	}
        }
        GlStateManager.popMatrix();
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		tileEntity.readFromNBT(tileEntity.getUpdatePacket().getNbtCompound());
		String name = I18n.format("container.powder_disimplementer.name");
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 3, 0x404040);
        fontRenderer.drawString(playerInventory.getDisplayName().getFormattedText(), 8, 72, 4210752);
        
    }

}
