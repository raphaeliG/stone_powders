package mc.raphaeliG.stone_powders.init;

import java.util.ArrayList;
import java.util.List;

import mc.raphaeliG.stone_powders.items.*;
import net.minecraft.item.Item;

public class ItemInit {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item STONE_POWDER = new ItemBase("stone_powder", CreativeTabInit.MAIN);
	public static final Item GRANITE_POWDER = new ItemBase("granite_powder", CreativeTabInit.MAIN);
	public static final Item DIOREITE_POWDER = new ItemBase("diorite_powder", CreativeTabInit.MAIN);
	public static final Item ANDESITE_POWDER = new ItemBase("andesite_powder", CreativeTabInit.MAIN);
	public static final Item OBSIDIAN_POWDER = new ItemBase("obsidian_powder", CreativeTabInit.MAIN);
	public static final Item NETHERRACK_POWDER = new ItemBase("netherrack_powder", CreativeTabInit.MAIN);
	public static final Item ENDSTONE_POWDER = new ItemBase("end_stone_powder", CreativeTabInit.MAIN);
	public static final Item EMPTY_POWDER = new ItemBase("empty_stone_powder", CreativeTabInit.MAIN);
}