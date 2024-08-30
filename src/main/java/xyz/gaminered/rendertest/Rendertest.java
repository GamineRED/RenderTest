package xyz.gaminered.rendertest;

import net.fabricmc.api.ModInitializer;
import xyz.gaminered.rendertest.item.RendertestItems;

public class Rendertest implements ModInitializer {
	public static final String MOD_ID = "rendertest";
	public static final String MOD_NAME = "RenderTest";

	@Override
	public void onInitialize() {
		RendertestItems.initialize();
	}
}
