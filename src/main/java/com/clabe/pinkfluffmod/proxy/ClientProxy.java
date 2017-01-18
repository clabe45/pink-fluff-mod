package com.clabe.pinkfluffmod.proxy;

import com.clabe.pinkfluffmod.init.ModBlocks;
import com.clabe.pinkfluffmod.init.ModItems;

public class ClientProxy implements CommonProxy{

	@Override
	public void init() {
		ModItems.registerRenders();
		ModBlocks.registerRenders();
	}
	
}
