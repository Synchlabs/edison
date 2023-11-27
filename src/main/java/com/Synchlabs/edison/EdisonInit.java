package com.Synchlabs.edison;

import com.Synchlabs.edison.items.ItemRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EdisonInit implements ModInitializer {
	public static final String MOD_ID = "edison";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize(ModContainer mod) {
		ItemRegistry.registerEdisonItems();
	}
}
