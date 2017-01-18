package com.clabe.pinkfluffmod.generation;

import java.util.Random;

import com.clabe.pinkfluffmod.Reference;
import com.clabe.pinkfluffmod.init.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ModWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.getDimension()) {
		case -1: generateNether(world, random, chunkX * 16, chunkZ * 16); break;
		case 0: generateSurface(world, random, chunkX * 16, chunkZ * 16); break;
		case 1: generateEnd(world, random, chunkX * 16, chunkZ * 16); break;
		}
		
	}
	
	private void generateNether(World world, Random random, int x, int z) {
		
	}
	
	private void generateSurface(World world, Random random, int x, int z) {
		
		final int structureId = random.nextInt(3);
		final int structureChances = random.nextInt(4);
		final boolean spawn = random.nextBoolean();
		
		if (spawn) {
			for (int i=0; i<structureChances; i++) {
				switch(structureId) {
				case 0: generatePinkFluffStructA(world, random, x, z); break;
				case 1: generatePinkFluffStructB(world, random, x, z); break;
				case 2: generatePinkFluffStructC(world, random, x, z); break;
				}
			}
		}
		
		int undergroundChances = random.nextInt(3);
		
		if (spawn) {
			for (int i=0; i<undergroundChances; i++) {
				generateUnderground (world, random, x, z);	// somewhat linked with structures
			}
		}
		
		int airChances = random.nextInt(1);
		
		if (random.nextBoolean()) {
			for (int i=0; i<airChances; i++) {
				generateInAir(world, random, x, z);
			}
		}
		
	}
	
	private void generateEnd(World world, Random random, int x, int z) {
		
	}
	
	private void generateInAir (World world, Random random, int x, int z) {
		int size = random.nextInt(16);
		
		BlockPos basePos = new BlockPos(x+random.nextInt(16), 0, z+random.nextInt(16));
		int groundY = world.getHeight(basePos).getY();
		int newY = groundY + random.nextInt(256 - groundY);	// in range groundY to 256 (max height)
		basePos = new BlockPos(basePos.getX(), newY, basePos.getZ());	// updated base pos
		
		BlockPos nextPos;
		
		for (int i=0; i<size; i++) {
			
			int posX = basePos.getX() - size/2 + random.nextInt(size);	// in range -size to +size
			int posY = basePos.getY() - size/2 + random.nextInt(size);
			int posZ = basePos.getZ() - size/2 + random.nextInt(size);
			
			nextPos = new BlockPos(posX, posY, posZ);
			if (world.getBlockState(nextPos) == Blocks.AIR.getDefaultState()) {
				world.setBlockState(nextPos, ModBlocks.purePinkFluff.getDefaultState());
			}
		}
	}
	
	private void generateUnderground (World world, Random random, int x, int z) {
		int minHeight = Reference.MIN_UNDERGROUND_GEN_HEIGHT;
		int maxHeight = Reference.MAX_UNDERGROUND_GEN_HEIGHT;
		BlockPos basePos = new BlockPos(x+random.nextInt(16), minHeight+random.nextInt(maxHeight-minHeight), z+random.nextInt(16));
		int size = random.nextInt(7);
		for (int i=0; i<size; i++) {
			// make each variant position in 2x2x2 "block radius"
			int posX = basePos.getX() - 2 + random.nextInt(4);
			int posY = basePos.getY() - 2 + random.nextInt(4);
			int posZ = basePos.getZ() - 2 + random.nextInt(4);
			
			BlockPos pos = new BlockPos(posX, posY, posZ);
			IBlockState state = random.nextInt(4) == 0 ? ModBlocks.purePinkFluff.getDefaultState():ModBlocks.pinkFluffStone.getDefaultState();
			boolean canGenerate = world.getBlockState(pos) == Blocks.STONE.getDefaultState();
			if (canGenerate) {world.setBlockState(pos, state); System.out.println("Generated pink fluff stone at ("+posX+","+posY+","+posZ+") [success]");
			}
		}
	}
	
	private void generatePinkFluffStructA (World world, Random random, int x, int z) {
		
		final IBlockState pinkFluffBlockState = ModBlocks.pinkFluff.getDefaultState();
		final IBlockState pinkFluffStoneState = ModBlocks.pinkFluffStone.getDefaultState();
		boolean canGenerate = true;
		final int layer1Size = 4 + random.nextInt(4);	// range 4 -- 8
		ModBlockPos[] layer1Pos = new ModBlockPos[layer1Size];
		final int layer2Size = 1 + random.nextInt(1);	// range 1--2, for conditionals
		ModBlockPos[] layer2Pos = new ModBlockPos[layer2Size];
		ModBlockPos basePos = null;
		
		// generate points
		for (int i=0; i<layer1Size; i++) {
			
			int posX, posZ;
			ModBlockPos curPos;
			
			// first block pos = base pos
			if (basePos == null) {
				posX = x + random.nextInt(16);
				posZ = z + random.nextInt(16);
				
				curPos = jumpToGroundLevel(world, posX, posZ, false, false, false);
				basePos = curPos;
			} else {
				// generate each block
				switch (i) {
				case 1: {
					posX = basePos.getX() +1;
					posZ = basePos.getZ();
					break;
				}
				case 2: {
					posX = basePos.getX() -1;
					posZ = basePos.getZ();
					break;
				}
				case 3: {
					posX = basePos.getX();
					posZ = basePos.getZ() +1;
					break;
				}
				case 4: {
					posX = basePos.getX();
					posZ = basePos.getZ() -1;
				}
				default: {
					posX = basePos.getX() + random.nextInt(3) - 1;
					posZ = basePos.getZ() + random.nextInt(3) - 1;
				}
				}
				
				curPos = new ModBlockPos(posX, basePos.getY(), posZ);
			}
			
			layer1Pos[i] = curPos;
			if (random.nextInt(4) > 2) {
				if (i < layer2Size) layer2Pos[i] = new ModBlockPos(posX, basePos.getY()+1, posZ);
				if (random.nextInt(3) > 1) layer1Pos[i].setIsStone(true);
			}
		}
		
		// check points
		for (int i=0; i<layer1Size; i++) {
			// check if space empty
			if (world.getBlockState(layer1Pos[i]) != Blocks.AIR.getDefaultState()) canGenerate = false;
			if (i < layer2Size && layer2Pos[i] != null && world.getBlockState(layer2Pos[i]) != Blocks.AIR.getDefaultState()) canGenerate = false;
			// check if grass underneath
			if (world.getBlockState(new BlockPos(layer1Pos[i].getX(), basePos.getY()-1, layer1Pos[i].getZ())) != Blocks.GRASS.getDefaultState()) {
				canGenerate = false;
			}
		}
		
		// place blocks
		if (canGenerate) {
			for (int i=0; i<layer1Size; i++) {
				IBlockState state = layer1Pos[i].getIsStone() ? pinkFluffStoneState : pinkFluffBlockState;
				world.setBlockState(layer1Pos[i], state);
				if (i < layer2Size && layer2Pos[i] != null) world.setBlockState(layer2Pos[i], pinkFluffBlockState);
			}
		}
	}
	
	private void generatePinkFluffStructB (World world, Random random, int x, int z) {
		final IBlockState pinkFluffBlockState = ModBlocks.pinkFluff.getDefaultState();
		final IBlockState pinkFluffStoneState = ModBlocks.pinkFluffStone.getDefaultState();
		boolean canGenerate = true;
		final int layer1Size = 8 + random.nextInt(8);	// range 8--16
		ModBlockPos[] layer1Pos = new ModBlockPos[layer1Size];
		final int layer2Size = 4 + random.nextInt(4);	// range 4--8
		ModBlockPos[] layer2Pos = new ModBlockPos[layer2Size];
		final int layer3Size = 2 + random.nextInt(2);	// range 2-4
		ModBlockPos[] layer3Pos = new ModBlockPos[layer3Size];
		ModBlockPos basePos = null;
		
		// generate points (and layer2 blocks only generate on top of layer1 blocks)
		for (int i=0; i<layer1Size; i++) {
			
			int posX, posZ;
			ModBlockPos curPos;
			
			// first block pos = base pos
			if (basePos == null) {
				posX = x + random.nextInt(16);
				posZ = z + random.nextInt(16);
				
				curPos = jumpToGroundLevel(world, posX, posZ, false, false, false);
				basePos = curPos;
			} else {
				// generate each "vital block" --> needed block
				switch (i) {
				case 1: {
					posX = basePos.getX() +1;
					posZ = basePos.getZ();
					break;
				}
				case 2: {
					posX = basePos.getX() -1;
					posZ = basePos.getZ();
					break;
				}
				case 3: {
					posX = basePos.getX();
					posZ = basePos.getZ() +1;
					break;
				}
				case 4: {
					posX = basePos.getX();
					posZ = basePos.getZ() -1;
					break;
				}
				case 5: {
					posX = basePos.getX() +1;
					posZ = basePos.getZ() +1;
					break;
				}
				case 6: {
					posX = basePos.getX() -1;
					posZ = basePos.getZ() +1;
					break;
				}
				case 7: {
					posX = basePos.getX() -1;
					posZ = basePos.getZ() -1;
					break;
				}
				case 8: {
					posX = basePos.getX() +1;
					posZ = basePos.getZ() -1;
					break;
				}
				default: {
					posX = basePos.getX() + random.nextInt(6) - 3;
					posZ = basePos.getZ() + random.nextInt(6) - 3;
				}
				}
				
				curPos = new ModBlockPos(posX, basePos.getY(), posZ);
			}
			
			layer1Pos[i] = curPos;
			if (random.nextInt(4) > 2) {
				if (i < layer2Size) layer2Pos[i] = new ModBlockPos(posX, basePos.getY()+1, posZ);
				int r = random.nextInt(20);
				if (r > 5) layer1Pos[i].setIsStone(true);	// chance of turning block underneath pink fluff stone
				else if (r < 4) layer1Pos[i].setIsPure(true);
				// if layer 2 generated, then also chance of layer 3 generating
				if (random.nextInt(4) > 2) {
					if (i < layer3Size) {
						layer3Pos[i] = new ModBlockPos(posX, basePos.getY()+2, posZ);
						layer1Pos[i].setIsStone(true);	// definitely make bottom block underneath stone
						if (random.nextInt(3) > 0) layer2Pos[i].setIsStone(true);	// chance of, you kn
					}
				}
			}
		}
		
		// check points
		for (int i=0; i<layer1Size; i++) {
			// check if space empty
			if (world.getBlockState(layer1Pos[i]) != Blocks.AIR.getDefaultState()) canGenerate = false;
			if (i < layer2Size && layer2Pos[i] != null && world.getBlockState(layer2Pos[i]) != Blocks.AIR.getDefaultState()) canGenerate = false;
			if (i < layer3Size && layer3Pos[i] != null && world.getBlockState(layer3Pos[i]) != Blocks.AIR.getDefaultState()) canGenerate = false;
			// check if grass underneath
			if (world.getBlockState(new BlockPos(layer1Pos[i].getX(), basePos.getY()-1, layer1Pos[i].getZ())) != Blocks.GRASS.getDefaultState()) {
				canGenerate = false;
			}
		}
		
		// place blocks
		if (canGenerate) {
			for (int i=0; i<layer1Size; i++) {
				IBlockState state = layer1Pos[i].getIsStone() ? pinkFluffStoneState : pinkFluffBlockState;
				// place layer 1
				world.setBlockState(layer1Pos[i], state);
				// place layer 2
				if (i < layer2Size && layer2Pos[i] != null) {
					state = layer2Pos[i].getIsStone() ? pinkFluffStoneState : pinkFluffBlockState;
					world.setBlockState(layer2Pos[i], state);
				}
				// place layer 3
				if (i < layer3Size && layer3Pos[i] != null) {
					world.setBlockState(layer3Pos[i], pinkFluffBlockState);
				}
			}
		}
	}
	
	private void generatePinkFluffStructC (World world, Random random, int x, int z) {
		final IBlockState pinkFluffBlockState = ModBlocks.pinkFluff.getDefaultState();
		final IBlockState pinkFluffStoneState = ModBlocks.pinkFluffStone.getDefaultState();
		final IBlockState purePinkFluffState = ModBlocks.purePinkFluff.getDefaultState();
		boolean canGenerate = true;
		final int layer1Size = 12 + random.nextInt(6);	// range 12--18
		ModBlockPos[] layer1Pos = new ModBlockPos[layer1Size];
		final int layer2Size = 8 + random.nextInt(4);	// range 8--12
		ModBlockPos[] layer2Pos = new ModBlockPos[layer2Size];
		final int layer3Size = 4 + random.nextInt(2);	// range 4-6
		ModBlockPos[] layer3Pos = new ModBlockPos[layer3Size];
		ModBlockPos basePos = null;
		
		// generate points (and layer2 blocks only generate on top of layer1 blocks)
		for (int i=0; i<layer1Size; i++) {
			
			int posX, posZ;
			ModBlockPos curPos;
			
			// first block pos = base pos
			if (basePos == null) {
				posX = x + random.nextInt(16);
				posZ = z + random.nextInt(16);
				
				curPos = jumpToGroundLevel(world, posX, posZ, false, false, false);
				basePos = curPos;
			} else {
				// generate each "vital block" --> needed block
				switch (i) {
				case 1: {
					posX = basePos.getX() +1;
					posZ = basePos.getZ();
					break;
				}
				case 2: {
					posX = basePos.getX() -1;
					posZ = basePos.getZ();
					break;
				}
				case 3: {
					posX = basePos.getX();
					posZ = basePos.getZ() +1;
					break;
				}
				case 4: {
					posX = basePos.getX();
					posZ = basePos.getZ() -1;
					break;
				}
				case 5: {
					posX = basePos.getX() +1;
					posZ = basePos.getZ() +1;
					break;
				}
				case 6: {
					posX = basePos.getX() -1;
					posZ = basePos.getZ() +1;
					break;
				}
				case 7: {
					posX = basePos.getX() -1;
					posZ = basePos.getZ() -1;
					break;
				}
				case 8: {
					posX = basePos.getX() +1;
					posZ = basePos.getZ() -1;
					break;
				}
				case 9: {
					posX = basePos.getX() +2;
					posZ = basePos.getZ();
					break;
				}
				case 10: {
					posX = basePos.getX() -2;
					posZ = basePos.getZ();
					break;
				}
				case 11: {
					posX = basePos.getX();
					posZ = basePos.getZ() +2;
					break;
				}
				case 12: {
					posX = basePos.getX();
					posZ = basePos.getZ() -2;
					break;
				}
				default: {
					posX = basePos.getX() + random.nextInt(10) - 5;
					posZ = basePos.getZ() + random.nextInt(10) - 5;
				}
				}
				
				curPos = new ModBlockPos(posX, basePos.getY(), posZ);
			}
			
			layer1Pos[i] = curPos;
			if (random.nextInt(4) > 2) {
				if (i < layer2Size) layer2Pos[i] = new ModBlockPos(posX, basePos.getY()+1, posZ);
				int r = random.nextInt(10);
				if (r > 2) layer1Pos[i].setIsStone(true);	// chance of turning block underneath pink fluff stone
				else layer1Pos[i].setIsPure(true);
				// if layer 2 generated, then also chance of layer 3 generating
				if (random.nextInt(4) > 2) {
					if (i < layer3Size) {
						layer3Pos[i] = new ModBlockPos(posX, basePos.getY()+2, posZ);
						layer1Pos[i].setIsStone(true);	// definitely make bottom block underneath stone
						if (random.nextInt(3) > 0) layer2Pos[i].setIsStone(true);	// chance of, you kn
					}
				}
			}
		}
		
		// check points
		for (int i=0; i<layer1Size; i++) {
			// check if space empty
			if (world.getBlockState(layer1Pos[i]) != Blocks.AIR.getDefaultState()
					&& world.getBlockState(layer1Pos[i]) != Blocks.TALLGRASS) canGenerate = false;
			if (i < layer2Size && layer2Pos[i] != null && world.getBlockState(layer2Pos[i]) != Blocks.AIR.getDefaultState()) canGenerate = false;
			if (i < layer3Size && layer3Pos[i] != null && world.getBlockState(layer3Pos[i]) != Blocks.AIR.getDefaultState()) canGenerate = false;
			// check if grass underneath
			if (world.getBlockState(new BlockPos(layer1Pos[i].getX(), basePos.getY()-1, layer1Pos[i].getZ())) != Blocks.GRASS.getDefaultState()) {
				canGenerate = false;
			}
		}
		
		// make it much harder to generate, since it can generate on grass
		canGenerate = canGenerate && random.nextBoolean();
		
		// place blocks
		if (canGenerate) {
			for (int i=0; i<layer1Size; i++) {
				IBlockState state = layer1Pos[i].getIsStone() ? pinkFluffStoneState : pinkFluffBlockState;
				state = layer1Pos[i].getIsPure() ? purePinkFluffState : pinkFluffBlockState;
				// place layer 1
				world.setBlockState(layer1Pos[i], state);
				// place layer 2
				if (i < layer2Size && layer2Pos[i] != null) {
					state = layer2Pos[i].getIsStone() ? pinkFluffStoneState : pinkFluffBlockState;
					world.setBlockState(layer2Pos[i], state);
				}
				// place layer 3
				if (i < layer3Size && layer3Pos[i] != null) {
					world.setBlockState(layer3Pos[i], pinkFluffBlockState);
				}
			}
		}
	}
	
	// may change to add advanced functionality
	private ModBlockPos jumpToGroundLevel (World world, int x, int z, boolean ignoreFluids, boolean ignoreWood, boolean ignoreFoliage) {
	
		BlockPos currentTop = world.getHeight(new BlockPos(x, 0, z));
		ModBlockPos currentTopMod = new ModBlockPos(currentTop.getX(), currentTop.getY(), currentTop.getZ());
		
		return currentTopMod;
	
	}
	
	private class ModBlockPos extends BlockPos {
		
		boolean isStone;
		boolean isPure;
		
		public ModBlockPos(int x, int y, int z) {
			this(x, y, z, false);
		}
		public ModBlockPos(int x, int y, int z, boolean isStone) {
			super(x, y, z);
			this.isStone = isStone;
		}
		
		public boolean getIsStone() {
			return this.isStone;
		}
		
		public void setIsStone(boolean newIsStone) {
			this.isStone = newIsStone;
			this.isPure = !newIsStone;
		}
		
		public boolean getIsPure() {
			return this.isPure;
		}
	
		public void setIsPure(boolean newIsPure) {
			this.isPure = newIsPure;
			this.isStone = !newIsPure;
		}
		
	}

}
