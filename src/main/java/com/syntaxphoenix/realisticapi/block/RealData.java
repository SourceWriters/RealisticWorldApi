package com.syntaxphoenix.realisticapi.block;

import java.util.Properties;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator.ChunkData;

public abstract class RealData {
	
	protected final Properties properties = new Properties();
	
	public Properties getProperties() {
		return properties;
	}
	
	@Override
	public String toString() {
		return asString();
	}
	
	/*
	 * 
	 */
	
	public void setBlock(Block block) {
		setBlock(block.getLocation());
	}
	
	public void setBlock(World world, int x, int y, int z) {
		setBlock(new Location(world, x, y, z));
	}
	
	/*
	 * 
	 */
	
	public abstract BlockData asBlockData();
	
	public abstract String asString();
	
	public abstract void setBlock(Location location);
	
	public abstract void setBlock(ChunkData data, int x, int y);

}
