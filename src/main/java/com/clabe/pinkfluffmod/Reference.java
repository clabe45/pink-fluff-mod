package com.clabe.pinkfluffmod;

public class Reference {
	
	public static final String MOD_ID = "pinkfluffmod";
	public static final String NAME = "Pink Fluff Mod";
	public static final String VERSION = "1.1";
	public static final String MINECRAFT_VERSIONS = "[1.11]";
	
	public static final String CLIENT_PROXY_CLASS = "com.clabe.pinkfluffmod.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.clabe.pinkfluffmod.proxy.ServerProxy";
	
	// for foods containing pink fluff
	public static final int FOOD_VALUE = 2;
	public static final float SATURATION_VALUE = 0f;
	
	// generation underground
	public static final int MIN_UNDERGROUND_GEN_HEIGHT = 30;
	public static final int MAX_UNDERGROUND_GEN_HEIGHT = 60;
	
	public static enum PinkFluffModItems {
		PINK_FLUFF("pinkFluff", "ItemPinkFluff"),
		PURE_PINK_FLUFF("purePinkFluff", "ItemPurePinkFluff"),
		PINK_FLUFF_BREAD("pinkFluffBread", "ItemPinkFluffBread"),
		PINK_FLUFF_COOKIE("pinkFluffCookie", "ItemPinkFluffCookie"),
		PINK_FLUFF_PUMPKIN_PIE("pinkFluffPumpkinPie", "ItemPinkFluffPumpkinPie");
		
		private String unlocalizedName;
		private String registryName;
		
		PinkFluffModItems(String unlocalizedName, String registryName) {
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getRegistryName() {
			return registryName;
		}
		
		public String getUnlocalizedName() {
			return unlocalizedName;
		}
	}
	
	public static enum PinkFluffModBlocks {
		PINK_FLUFF("pinkFluff", "BlockPinkFluff"),
		PINK_FLUFF_STONE("pinkFluffStone", "BlockPinkFluffStone"),
		PURE_PINK_FLUFF("purePinkFluff", "BlockPurePinkFluff");
		
		private String unlocalizedName;
		private String registryName;
		
		PinkFluffModBlocks(String unlocalizedName, String registryName) {
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getRegistryName() {
			return registryName;
		}
		
		public String getUnlocalizedName() {
			return unlocalizedName;
		}
	}
	
}
