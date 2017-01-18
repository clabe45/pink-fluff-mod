package com.clabe.pinkfluffmod.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import com.clabe.pinkfluffmod.Reference;
import com.clabe.pinkfluffmod.init.ModBlocks;
import com.clabe.pinkfluffmod.init.ModItems;

public class BlockPurePinkFluff extends Block {
	
	private ItemDrop[] itemDrops;
	private BlockDrop[] blockDrops;
	
	public BlockPurePinkFluff() {
		super(Material.CAKE);
		setUnlocalizedName(Reference.PinkFluffModBlocks.PURE_PINK_FLUFF.getUnlocalizedName());
		setRegistryName(Reference.PinkFluffModBlocks.PURE_PINK_FLUFF.getRegistryName());
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setHardness(1.0F);
		setResistance(4);
		setHarvestLevel("shovel", 1);
		
		itemDrops = new ItemDrop[6];
		itemDrops[0] = new ItemDrop(ModItems.purePinkFluff, 3, 6, 15);	// pure pink fluff
		itemDrops[1] = new ItemDrop(ModItems.pinkFluff, 2, 12, 15);
		itemDrops[2] = new ItemDrop(new ItemStack(Items.DYE, 1, 9), 1, 9, 15);	// pink dye
		itemDrops[3] = new ItemDrop(Items.SUGAR, 1, 2, 5);	// sugar
		itemDrops[4] = new ItemDrop(ModItems.pinkFluffPumpkinPie, 1, 1, 3);
		itemDrops[5] = new ItemDrop(Items.EMERALD, 1, 1, 1);
		
		blockDrops = new BlockDrop[3];
		blockDrops[0] = new BlockDrop(ModBlocks.pinkFluff, 1, 1, 4);
		blockDrops[1] = new BlockDrop(ModBlocks.pinkFluffStone, 1, 1, 3);
		blockDrops[2] = new BlockDrop(ModBlocks.purePinkFluff, 1, 1, 2);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> drops = new ArrayList<ItemStack>();
		
		Random random = new Random();
		int max = 0;
		int chanceAt = 0;	// the number that the chance is at
		// add up total for randomization (max value)
		for (ItemDrop drop:itemDrops) {if(drop.chance > max) max += drop.chance; }
		for (BlockDrop drop:blockDrops) {if(drop.chance > max) max += drop.chance; }
		// get random number
		int rand = random.nextInt(max);
		// check each interval to see if rand hit it
		for (ItemDrop drop:itemDrops) {
			if (drop.chance > rand) {
				int quantity = drop.minQuantity + random.nextInt(drop.maxQuantity-drop.minQuantity);
				drops.add(new ItemStack(drop.drop.getItem(), quantity, drop.drop.getItemDamage()));
			}
			chanceAt += drop.chance;
		}
		for (BlockDrop drop:blockDrops) {
			if (rand > chanceAt && rand < chanceAt + drop.chance) {
				int quantity = drop.minQuantity + random.nextInt(drop.maxQuantity-drop.minQuantity);
				Item itemDrop = Item.getItemFromBlock(drop.drop);
				drops.add(new ItemStack(itemDrop, quantity));
			}
			chanceAt += drop.chance;
		}
		return drops;
	}
	
	private class BlockDrop {
		Block drop;
		int minQuantity, maxQuantity;
		int chance;
		
		public BlockDrop (Block drop, int minQuantity, int maxQuantity, int chance) {
			this.drop = drop;
			this.minQuantity = minQuantity;
			this.maxQuantity = maxQuantity;
			this.chance = chance;
		}
		
	}
	
	private class ItemDrop {
		ItemStack drop;
		int minQuantity, maxQuantity;
		int chance;
		
		public ItemDrop (Item drop, int minQuantity, int maxQuantity, int chance) {
			this(new ItemStack(drop), minQuantity, maxQuantity, chance);
		}
		
		public ItemDrop (ItemStack drop, int minQuantity, int maxQuantity, int chance) {
			this.drop = drop;
			this.minQuantity = minQuantity;
			this.maxQuantity = maxQuantity;
			this.chance = chance;
		}
		
	}
}
