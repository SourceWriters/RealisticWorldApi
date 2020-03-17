package com.syntaxphoenix.spigot.realisticapi.data.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import com.syntaxphoenix.spigot.realisticapi.data.DataProcessor;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;

public abstract class BlockProcessor extends DataProcessor {
	
	@Override
	public abstract ABlock process(NbtCompound compound);
	
	public abstract ABlock process(BlockData blockData);
	
	public abstract ABlock process(Material material);
	
	public abstract ABlock process(String blockData);
	
	public abstract ABlock process(Block block);
	
}
