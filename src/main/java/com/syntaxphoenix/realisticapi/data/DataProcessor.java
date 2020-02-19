package com.syntaxphoenix.realisticapi.data;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;

public abstract class DataProcessor {
	
	public abstract RealData process(NbtCompound compound);
	
}
