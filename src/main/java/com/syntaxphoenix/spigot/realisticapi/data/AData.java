package com.syntaxphoenix.spigot.realisticapi.data;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.utils.NbtSaveable;

public abstract class AData implements NbtSaveable<NbtCompound> {
	
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
