package com.clabe.pinkfluffmod.blocks;

import java.util.Random;

import com.clabe.pinkfluffmod.Reference;
import com.clabe.pinkfluffmod.init.ModBlocks;
import com.clabe.pinkfluffmod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPinkFluff extends Block {
	
	private Item drop;
	private int minQuantity;
	private int maxQuantity;
	
	public BlockPinkFluff() {
		super(Material.CAKE);
		setUnlocalizedName(Reference.PinkFluffModBlocks.PINK_FLUFF.getUnlocalizedName());
		setRegistryName(Reference.PinkFluffModBlocks.PINK_FLUFF.getRegistryName());
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setHardness(1.0F);
		setResistance(4);
		setHarvestLevel("shovel", 0);
		setTickRandomly(true);
		
		drop = ModItems.pinkFluff;
		minQuantity = 3;
		maxQuantity = 5;
	}
	
	@Override
	public Item getItemDropped (IBlockState par1, Random random, int fortune) {
		return ModItems.pinkFluff;
	}
	
	@Override
	public int quantityDropped(Random random) {
		return minQuantity + random.nextInt(maxQuantity - minQuantity);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
		super.updateTick(world, pos, state, random);
		
		// generate around it
		if (random.nextInt(6) == 0) {
			int posId = random.nextInt(5);
			BlockPos newPos;
			newPos = new BlockPos(pos.getX()+1, pos.getY(), pos.getZ());
			if (canGrowAt(world, newPos, random)) world.setBlockState(pos, state);
			newPos = new BlockPos(pos.getX()-1, pos.getY(), pos.getZ());
			if (canGrowAt(world, newPos, random))  world.setBlockState(newPos, state);
			newPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1);
			if (canGrowAt(world, newPos, random)) world.setBlockState(newPos, state);
			newPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1);
			if (canGrowAt(world, newPos, random))  world.setBlockState(newPos, state);
			newPos = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
			if (canGrowAt(world, newPos, random))  world.setBlockState(newPos, state);

		}
		
	}
	
	private boolean canGrowAt(World world, BlockPos pos, Random random) {
		BlockPos posUnder = new BlockPos(pos.getX(), pos.getY()-1, pos.getZ());
		BlockPos posUnder2 = new BlockPos(pos.getX(), pos.getY()-2, pos.getZ());
		BlockPos posUnder3 = new BlockPos(pos.getX(), pos.getY()-3, pos.getZ());
		BlockPos curPos = new BlockPos(pos.getX()+1, pos.getY(), pos.getZ());
		
		// check if at least one grass block is near block underneath it
		boolean isNearGrass = false;
		if (world.getBlockState(curPos) == Blocks.GRASS.getDefaultState() ||
				world.getBlockState(curPos) == Blocks.DIRT.getDefaultState()) isNearGrass = true;
		curPos = new BlockPos(pos.getX()-1, pos.getY()-1, pos.getZ());
		if (world.getBlockState(curPos) == Blocks.GRASS.getDefaultState() ||
				world.getBlockState(curPos) == Blocks.DIRT.getDefaultState())  isNearGrass = true;
		curPos = new BlockPos(pos.getX(), pos.getY()-1, pos.getZ()+1);
		if (world.getBlockState(curPos) == Blocks.GRASS.getDefaultState() ||
				world.getBlockState(curPos) == Blocks.DIRT.getDefaultState()) isNearGrass = true;
		curPos = new BlockPos(pos.getX(), pos.getY()-1, pos.getZ()-1);
		if (world.getBlockState(curPos) == Blocks.GRASS.getDefaultState() ||
				world.getBlockState(curPos) == Blocks.DIRT.getDefaultState())  isNearGrass = true;
		
		curPos = new BlockPos(pos.getX()-1, pos.getY()-1, pos.getZ()-1);
		if (world.getBlockState(curPos) == Blocks.GRASS.getDefaultState() ||
				world.getBlockState(curPos) == Blocks.DIRT.getDefaultState()) isNearGrass = true;
		curPos = new BlockPos(pos.getX()-1, pos.getY()-1, pos.getZ()+1);
		if (world.getBlockState(curPos) == Blocks.GRASS.getDefaultState() ||
				world.getBlockState(curPos) == Blocks.DIRT.getDefaultState())  isNearGrass = true;
		curPos = new BlockPos(pos.getX()+1, pos.getY()-1, pos.getZ()-1);
		if (world.getBlockState(curPos) == Blocks.GRASS.getDefaultState() ||
				world.getBlockState(curPos) == Blocks.DIRT.getDefaultState()) isNearGrass = true;
		curPos = new BlockPos(pos.getX()+1, pos.getY()-1, pos.getZ()+1);
		if (world.getBlockState(curPos) == Blocks.GRASS.getDefaultState() ||
				world.getBlockState(curPos) == Blocks.DIRT.getDefaultState())  isNearGrass = true;
		
		if (isNearGrass) {
			if (world.getBlockState(pos) == Blocks.AIR.getDefaultState() 
					&& world.getBlockState(posUnder) == ModBlocks.pinkFluffStone.getDefaultState()
					&& random.nextInt(4) == 0) {
				return true;
			} else if (world.getBlockState(posUnder) == ModBlocks.pinkFluff.getDefaultState() 
					&& world.getBlockState(posUnder2) == ModBlocks.pinkFluffStone.getDefaultState()
					&& random.nextInt(6) == 0) {
				return true;
			}
		}
		return false;
	}
	
}
