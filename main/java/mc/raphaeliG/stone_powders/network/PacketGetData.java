package mc.raphaeliG.stone_powders.network;

import io.netty.buffer.ByteBuf;
import mc.raphaeliG.stone_powders.tileentity.TileEntityMachine;
import mc.raphaeliG.stone_powders.util.Reference;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketGetData implements IMessage {

	
	private boolean messageValid;
	
	private BlockPos pos;

	private String className;
	private String fuelTimeField;
	private String currentItemFuelTimeField;
	private String progressField;
	private String totalProgressField;
	
	public PacketGetData() {
		this.messageValid = false;
	}
	
	public PacketGetData(BlockPos pos, String className, String fuelTimeField, String currentItemFuelTimeField, String progressField,
			String totalProgressField) {
		this.pos = pos;
		this.className = className;
		this.fuelTimeField = fuelTimeField;
		this.currentItemFuelTimeField = currentItemFuelTimeField;
		this.progressField = progressField;
		this.totalProgressField = totalProgressField;
		this.messageValid = true;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
			this.className = ByteBufUtils.readUTF8String(buf);
			this.fuelTimeField = ByteBufUtils.readUTF8String(buf);
			this.currentItemFuelTimeField = ByteBufUtils.readUTF8String(buf);
			this.totalProgressField = ByteBufUtils.readUTF8String(buf);
			this.progressField = ByteBufUtils.readUTF8String(buf);
		} catch (IndexOutOfBoundsException ioe) {
			Reference.logger.catching(ioe);
			return;
		}
		this.messageValid = true;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid)
			return;
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		ByteBufUtils.writeUTF8String(buf, this.className);
		ByteBufUtils.writeUTF8String(buf, this.fuelTimeField);
		ByteBufUtils.writeUTF8String(buf, this.currentItemFuelTimeField);
		ByteBufUtils.writeUTF8String(buf, this.totalProgressField);
		ByteBufUtils.writeUTF8String(buf, this.progressField);
	}
	
	public static class Handler implements IMessageHandler<PacketGetData, IMessage> {
		
		@Override
		public IMessage onMessage(PacketGetData message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.SERVER)
				return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}
		
		void processMessage(PacketGetData message, MessageContext ctx) {
			//Reference.logger.info("processing a packet get data!");
			TileEntity tileEntity = (TileEntityMachine) ctx.getServerHandler().player.getServerWorld().getTileEntity(message.pos);
			if (tileEntity == null)
				return;
			if (tileEntity instanceof TileEntityMachine)
			{
				TileEntityMachine te = (TileEntityMachine)tileEntity;
				PacketHandler.INSTANCE.sendTo(new PacketReturnData(te.fuelTime, te.currentItemFuelTime, te.progress, te.totalProgress,
					message.className, message.fuelTimeField, message.currentItemFuelTimeField, message.totalProgressField, message.progressField),
						ctx.getServerHandler().player);
			}
		}
	}

}
