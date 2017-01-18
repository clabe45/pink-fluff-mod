package com.clabe.pinkfluffmod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.clabe.pinkfluffmod.Reference;

public class ItemPinkFluffPumpkinPie extends ItemFood {
	
	static int amount = 8 + Reference.FOOD_VALUE;
	static float saturation = 4.8f + Reference.SATURATION_VALUE;
	static boolean isWolfFood = false;
	
	static int effectDuration = 40;
	
	public ItemPinkFluffPumpkinPie() {
		super(amount, saturation, isWolfFood);
		setUnlocalizedName(Reference.PinkFluffModItems.PINK_FLUFF_PUMPKIN_PIE.getUnlocalizedName());
		setRegistryName(Reference.PinkFluffModItems.PINK_FLUFF_PUMPKIN_PIE.getRegistryName());
		setCreativeTab(CreativeTabs.FOOD);
		setPotionEffect(new PotionEffect(Potion.getPotionById(1), (int)(effectDuration/0.05)), 1F);	// speed for 40s
	}

}
