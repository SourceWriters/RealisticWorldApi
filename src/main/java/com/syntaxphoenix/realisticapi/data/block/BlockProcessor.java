package com.syntaxphoenix.realisticapi.data.block;

import org.bukkit.Material;
import org.bukkit.block.Block;

import com.syntaxphoenix.realisticapi.data.DataProcessor;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;

public abstract class BlockProcessor extends DataProcessor {
	
	@Override
	public abstract RealBlock process(NbtCompound compound);
	
	public abstract RealBlock process(Material material);
	
	public abstract RealBlock process(String blockData);
	
	public abstract RealBlock process(Block block);
	
}
