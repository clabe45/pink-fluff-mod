package com.clabe.pinkfluffmod.init;

import com.clabe.pinkfluffmod.Reference;
import com.clabe.pinkfluffmod.items.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item pinkFluff;
	public static Item purePinkFluff;
	public static Item pinkFluffBread;
	public static Item pinkFluffCookie;
	public static Item pinkFluffPumpkinPie;
	
	public static void init() {
		pinkFluff = new ItemPinkFluff();
		purePinkFluff = new ItemPurePinkFluff();
		pinkFluffBread = new ItemPinkFluffBread();
		pinkFluffCookie = new ItemPinkFluffCookie();
		pinkFluffPumpkinPie = new ItemPinkFluffPumpkinPie();
	}
	
	public static void register() {
		GameRegistry.register(pinkFluff);
		GameRegistry.register(purePinkFluff);
		GameRegistry.register(pinkFluffBread);
		GameRegistry.register(pinkFluffCookie);
		GameRegistry.register(pinkFluffPumpkinPie);
	}
	
	public static void registerRenders() {
		registerRender(pinkFluff);
		registerRender(purePinkFluff);
		registerRender(pinkFluffBread);
		registerRender(pinkFluffCookie);
		registerRender(pinkFluffPumpkinPie);
	}
	
	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
}
