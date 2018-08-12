package mc.raphaeliG.stone_powders.init;

import mc.raphaeliG.stone_powders.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabInit {
	public static final CreativeTabs MAIN = new CreativeTabs("main"){public ItemStack getTabIconItem(){return new ItemStack(ItemInit.OBSIDIAN_POWDER);}};
}
