package xyz.gaminered.rendertest.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import xyz.gaminered.rendertest.Rendertest;

public class RendertestItems {
	public static final Item BOX_SELECT_ITEM = Items.register(Identifier.of(Rendertest.MOD_ID, "box_select"), new Item(new Item.Settings()));

	public static void initialize() {}
}
