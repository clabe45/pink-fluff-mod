package com.clabe.pinkfluffmod.items;

import com.clabe.pinkfluffmod.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPurePinkFluff extends Item {
	
	public ItemPurePinkFluff() {
		setUnlocalizedName(Reference.PinkFluffModItems.PURE_PINK_FLUFF.getUnlocalizedName());
		setRegistryName(Reference.PinkFluffModItems.PURE_PINK_FLUFF.getRegistryName());
		setCreativeTab(CreativeTabs.MATERIALS);
	}
	
}
