package com.clabe.pinkfluffmod.items;

import com.clabe.pinkfluffmod.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPinkFluff extends Item {
	
	public ItemPinkFluff() {
		
		setUnlocalizedName(Reference.PinkFluffModItems.PINK_FLUFF.getUnlocalizedName());
		setRegistryName(Reference.PinkFluffModItems.PINK_FLUFF.getRegistryName());
		setCreativeTab(CreativeTabs.MATERIALS);
		
	}
}
