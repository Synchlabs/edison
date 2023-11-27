package com.Synchlabs.edison.items;

import com.Synchlabs.edison.EdisonInit;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistry {

	public static final Item GLOWSHARD = registerItem("glowshard", new GlowShard());




	private static void addItemsToIngredientGroup(FabricItemGroupEntries entries) {
		entries.addItem(GLOWSHARD);
	}
	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(EdisonInit.MOD_ID, name), item);
	}

	public static void registerEdisonItems () {
		EdisonInit.LOGGER.info("Registering items for " + EdisonInit.MOD_ID);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ItemRegistry::addItemsToIngredientGroup);
	}
}
