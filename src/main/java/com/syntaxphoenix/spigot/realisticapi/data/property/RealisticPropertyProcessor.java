package com.syntaxphoenix.spigot.realisticapi.data.property;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.tools.NbtJsonParser;
import com.syntaxphoenix.syntaxapi.reflections.Reflector;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public class RealisticPropertyProcessor extends PropertyProcessor {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public RealisticProperty<?> process(NbtCompound compound) {
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
		return new RealisticProperty<>(type, Property.createObject(key, object));
	}

	@Override
	public <E> RealisticProperty<E> process(Class<E> type, Property<E> property) {
		return new RealisticProperty<>(type, property);
	}

	@Override
	public <E> RealisticProperty<E> process(Property<E> property) {
		return new RealisticProperty<>(property);
	}

}
