package com.syntaxphoenix.spigot.realisticapi.data.property;

import com.syntaxphoenix.spigot.realisticapi.data.DataProcessor;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;

public abstract class PropertiesProcessor extends DataProcessor {
	
	@Override
	public abstract AProperties<?> process(NbtCompound compound);

}
