package com.syntaxphoenix.realisticapi.block;

import org.bukkit.Material;
import org.bukkit.block.Block;

public abstract class DataProcessor {
	
	public abstract RealData process(Material material);
	
	public abstract RealData process(String blockData);
	
	public abstract RealData process(Block block);
	
}
