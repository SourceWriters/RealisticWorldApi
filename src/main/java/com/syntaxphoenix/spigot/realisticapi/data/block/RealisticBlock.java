package com.syntaxphoenix.spigot.realisticapi.data.block;

import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator.ChunkData;

import com.syntaxphoenix.spigot.realisticapi.data.property.RealisticProperties;
import com.syntaxphoenix.spigot.realisticapi.generation.IDataWriter;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;

public class RealisticBlock extends ABlock {
	
	public RealisticBlock() {
		super(new RealisticProperties());
	}
	
	/*
	 * Coming soon
	 */

	@Override
	public NbtCompound asNbt() {
		return null;
	}

	@Override
	public BlockData asBlockData() {
		return null;
	}

	@Override
	public void setBlock(Location location) {
		
	}

	@Override
	public void setBlock(ChunkData data, int x, int y, int z) {
		
	}

	@Override
	public boolean write(IDataWriter writer) {
		return false;
	}

}
