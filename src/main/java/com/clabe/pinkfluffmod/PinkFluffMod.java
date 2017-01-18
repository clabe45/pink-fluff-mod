package com.clabe.pinkfluffmod;

import com.clabe.pinkfluffmod.generation.ModWorldGenerator;
import com.clabe.pinkfluffmod.init.ModBlocks;
import com.clabe.pinkfluffmod.init.ModCrafting;
import com.clabe.pinkfluffmod.init.ModItems;
import com.clabe.pinkfluffmod.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions=Reference.MINECRAFT_VERSIONS)
public class PinkFluffMod {
	
	@Instance
	public static PinkFluffMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.init();
		ModItems.register();
		
		ModBlocks.init();
		ModBlocks.register();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
		
		ModCrafting.register();
		
		ModWorldGenerator worldGen = new ModWorldGenerator();
		GameRegistry.registerWorldGenerator(worldGen, 0);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
