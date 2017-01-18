package com.clabe.pinkfluffmod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {
	
	public static void register() {
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.pinkFluff), 
				"XX",
				"XX",
				'X', ModItems.pinkFluff
		);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.pinkFluffStone), 
				"XX",
				"XX",
				'X', ModBlocks.pinkFluff
		);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.STICKY_PISTON), 
				"Y",
				"X",
				'X', Blocks.PISTON, 'Y', ModItems.pinkFluff
		);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.pinkFluff, 4), ModBlocks.pinkFluffStone);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pinkFluff, 2), Items.MILK_BUCKET.setContainerItem(Items.BUCKET), new ItemStack(Items.DYE, 1, 9), Items.SUGAR);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pinkFluffBread), Items.BREAD, ModItems.pinkFluff, ModItems.pinkFluff);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pinkFluffCookie), Items.COOKIE, ModItems.pinkFluff);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pinkFluffPumpkinPie), Items.PUMPKIN_PIE, ModItems.pinkFluff, ModItems.pinkFluff, ModItems.pinkFluff);	// 3 pink fluff
	}

	
}
