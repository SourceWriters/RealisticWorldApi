package com.syntaxphoenix.spigot.realisticapi.data;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;

public abstract class DataProcessor {
	
	public abstract AData process(NbtCompound compound);
	
}
