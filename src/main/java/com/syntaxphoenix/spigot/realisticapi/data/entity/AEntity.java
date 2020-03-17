package com.syntaxphoenix.spigot.realisticapi.data.entity;

import org.bukkit.Location;
import org.bukkit.World;

import com.syntaxphoenix.spigot.realisticapi.data.APropertiedData;
import com.syntaxphoenix.spigot.realisticapi.data.property.AProperties;
import com.syntaxphoenix.spigot.realisticapi.generation.DataType;
import com.syntaxphoenix.spigot.realisticapi.generation.IWriteable;

public abstract class AEntity extends APropertiedData implements IWriteable {
	
	public AEntity(AProperties<?> properties) {
		super(properties);
	}

	public void spawn(World world, int x, int y, int z) {
		spawn(new Location(world, x, y, z));
	}
	
	/*
	 * 
	 */
	
	@Override
	public DataType getDataType() {
		return DataType.ENTITY;
	}
	
	/*
	 * 
	 */
	
	public abstract void spawn(Location location);

}
