package com.syntaxphoenix.realisticapi.data.property;

import com.syntaxphoenix.realisticapi.data.DataProcessor;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.tools.NbtJsonParser;
import com.syntaxphoenix.syntaxapi.reflections.Reflector;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public class PropertyProcessor extends DataProcessor {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public RealProperty<?> process(NbtCompound compound) {
		if(!compound.hasKey("type") || !compound.hasKey("property")) {
			return null;
		}
		Class type = Reflector.getClass(compound.getString("type"));
		NbtCompound property = compound.getCompound("property");
		if(!property.hasKey("key") || !property.hasKey("value")) {
			return null;
		}
		String key = property.getString("key");
		Object object = NbtJsonParser.fromNbt(property.get("value"), type);
		return new RealProperty<>(type, Property.createObject(key, object));
	}

}
