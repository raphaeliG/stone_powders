package mc.raphaeliG.stone_powders.items;

import mc.raphaeliG.stone_powders.StonePowders;
import mc.raphaeliG.stone_powders.init.ItemInit;
import mc.raphaeliG.stone_powders.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel{
	
	public ItemBase(String name, CreativeTabs cretiveTab) {
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(cretiveTab);
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		StonePowders.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
