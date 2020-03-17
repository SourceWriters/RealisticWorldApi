package com.syntaxphoenix.spigot.realisticapi.data.block;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator.ChunkData;

import com.syntaxphoenix.spigot.realisticapi.data.APropertiedData;
import com.syntaxphoenix.spigot.realisticapi.data.property.AProperties;
import com.syntaxphoenix.spigot.realisticapi.generation.DataType;
import com.syntaxphoenix.spigot.realisticapi.generation.IWriteable;

public abstract class ABlock extends APropertiedData implements IWriteable {
	
	public ABlock(AProperties<?> properties) {
		super(properties);
	}

	public void setBlock(Block block) {
		setBlock(block.getLocation());
	}
	
	public void setBlock(World world, int x, int y, int z) {
		setBlock(new Location(world, x, y, z));
	}
	
	/*
	 * 
	 */
	
	@Override
	public DataType getDataType() {
		return DataType.BLOCK;
	}
	
	/*
	 * 
	 */
	
	public abstract BlockData asBlockData();
	
	public abstract void setBlock(Location location);
	
	public abstract void setBlock(ChunkData data, int x, int y, int z);

}
