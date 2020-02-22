package com.syntaxphoenix.spigot.realisticapi.data;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.utils.NbtSaveable;
import com.syntaxphoenix.syntaxapi.utils.data.Properties;

public abstract class RealData implements NbtSaveable<NbtCompound> {
	
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
	
	public final String asString() {
		return asNbt().toMSONString();
	}

}
