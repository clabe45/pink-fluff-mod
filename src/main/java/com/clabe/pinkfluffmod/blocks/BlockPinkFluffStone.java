package com.clabe.pinkfluffmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import com.clabe.pinkfluffmod.Reference;

public class BlockPinkFluffStone extends Block {
		
	public BlockPinkFluffStone () {
		super(Material.ROCK);
		setUnlocalizedName(Reference.PinkFluffModBlocks.PINK_FLUFF_STONE.getUnlocalizedName());
		setRegistryName(Reference.PinkFluffModBlocks.PINK_FLUFF_STONE.getRegistryName());
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setHardness(1.0F);
		setResistance(10);
		setHarvestLevel("pickaxe", 0);
	}
	
	
	
}
