package com.clabe.pinkfluffmod.items;

import com.clabe.pinkfluffmod.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemPinkFluffCookie extends ItemFood {
	
	static final int amount = 2 + Reference.FOOD_VALUE;
	static final float saturation = 0.5f + Reference.SATURATION_VALUE;
	static final boolean isWolfFood = false;
	
	public ItemPinkFluffCookie() {
		super(amount, saturation, isWolfFood);
		
		setUnlocalizedName(Reference.PinkFluffModItems.PINK_FLUFF_COOKIE.getUnlocalizedName());
		setRegistryName(Reference.PinkFluffModItems.PINK_FLUFF_COOKIE.getRegistryName());
		setCreativeTab(CreativeTabs.FOOD);
		setPotionEffect(new PotionEffect(Potion.getPotionById(1), (int)(15/0.05)), 1F);	// speed
	}
	
}
