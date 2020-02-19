package com.syntaxphoenix.realisticapi.data;

import java.util.Properties;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.utils.NbtSaveable;

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
