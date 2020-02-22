package com.syntaxphoenix.spigot.realisticapi.data.property;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.tools.NbtJsonParser;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public class RealisticProperty<P> extends RealProperty<P> {
	
	public RealisticProperty(Class<P> type, Property<P> property) {
		super(type, property);
	}
	
	public RealisticProperty(Property<P> property) {
		super(property);
	}
	
	/*
	 * 
	 */

	@Override
	public NbtCompound asNbt() {
		NbtCompound property = new NbtCompound();
		property.set("key", getKey());
		property.set("value", NbtJsonParser.toNbt(getValue()));

		NbtCompound compound = new NbtCompound();
		compound.set("type", type.getName());
		compound.set("property", property);
		return compound;
	}

}
