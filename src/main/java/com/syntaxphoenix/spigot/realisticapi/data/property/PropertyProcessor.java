package com.syntaxphoenix.spigot.realisticapi.data.property;

import com.syntaxphoenix.spigot.realisticapi.data.DataProcessor;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public abstract class PropertyProcessor extends DataProcessor {
	
	@Override
	public abstract RealProperty<?> process(NbtCompound compound);
	
	public abstract <E> RealProperty<E> process(Class<E> type, Property<E> property);
	
	public abstract <E> RealProperty<E> process(Property<E> property);

}
