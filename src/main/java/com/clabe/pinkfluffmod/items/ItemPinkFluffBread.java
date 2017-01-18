package com.clabe.pinkfluffmod.items;

import com.clabe.pinkfluffmod.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;

public class ItemPinkFluffBread extends ItemFood {
	
		static final int amount = 5 + Reference.FOOD_VALUE*2;	// two pink fluff's in recipe
		static final float saturation = 6.0f + Reference.SATURATION_VALUE*2;
	static final boolean isWolfFood = false;
	
	public ItemPinkFluffBread () {
		// super(int amount, float saturation, boolean isWolfFood)
		super(amount, saturation, isWolfFood);
		
		setUnlocalizedName(Reference.PinkFluffModItems.PINK_FLUFF_BREAD.getUnlocalizedName());
		setRegistryName(Reference.PinkFluffModItems.PINK_FLUFF_BREAD.getRegistryName());
		setCreativeTab(CreativeTabs.FOOD);
		setPotionEffect(new PotionEffect(Potion.getPotionById(1), (int)(30/0.05)), 1F);	// speed
	}
	
	
	
}
