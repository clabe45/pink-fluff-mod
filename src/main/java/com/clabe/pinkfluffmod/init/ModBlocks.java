package com.clabe.pinkfluffmod.init;

import com.clabe.pinkfluffmod.blocks.*;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block pinkFluff;
	public static Block pinkFluffStone;
	public static Block purePinkFluff;
	
	public static void init() {
		pinkFluff = new BlockPinkFluff();
		pinkFluffStone = new BlockPinkFluffStone();
		purePinkFluff = new BlockPurePinkFluff();
	}
	
	public static void register() {
		registerBlock(pinkFluff);
		registerBlock(pinkFluffStone);
		registerBlock(purePinkFluff);
	}
	
	private static void registerBlock(Block block) {
		GameRegistry.register(block);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
	}
	
	public static void registerRenders() {
		registerRender(pinkFluff);
		registerRender(pinkFluffStone);
		registerRender(purePinkFluff);
	}
	
	private static void registerRender(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
}
