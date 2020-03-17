package com.syntaxphoenix.spigot.realisticapi.data.entity;

import org.bukkit.Location;

import com.syntaxphoenix.spigot.realisticapi.data.property.RealisticProperties;
import com.syntaxphoenix.spigot.realisticapi.generation.IDataWriter;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;

public class RealisticEntity extends AEntity {

	public RealisticEntity() {
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
	public void spawn(Location location) {
		
	}

	@Override
	public boolean write(IDataWriter writer) {
		if(!writer.isSupported(getDataType()))
			return false;
		
		return true;
	}

}
