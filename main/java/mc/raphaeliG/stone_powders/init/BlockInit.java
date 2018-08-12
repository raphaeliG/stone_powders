package mc.raphaeliG.stone_powders.init;

import java.util.ArrayList;
import java.util.List;

import mc.raphaeliG.stone_powders.blocks.BlockPowderDisimplementer;
import mc.raphaeliG.stone_powders.blocks.BlockPowderImplementer;
import mc.raphaeliG.stone_powders.blocks.templates.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block POWDERLESS_STONE = new BlockBase("powderless_stone", new PowderlessStoneMaterial(), CreativeTabInit.MAIN,
			1.5F, "pickaxe", 0, 30, SoundType.STONE);
	
	public static final Block POWDER_IMPLEMENTER = new BlockPowderImplementer();
	public static final Block POWDER_DISIMPLEMENTER = new BlockPowderDisimplementer();
	
	private static class PowderlessStoneMaterial extends Material
	{
		public PowderlessStoneMaterial() {
			super(MapColor.BLOCK_COLORS[EnumDyeColor.WHITE.getMetadata()]);
			setRequiresTool();
		}
	}
}
