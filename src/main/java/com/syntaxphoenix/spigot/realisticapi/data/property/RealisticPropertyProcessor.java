package com.syntaxphoenix.spigot.realisticapi.data.property;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.NbtType;
import com.syntaxphoenix.syntaxapi.nbt.tools.NbtJsonParser;
import com.syntaxphoenix.syntaxapi.reflections.ClassCache;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public class RealisticPropertyProcessor extends PropertyProcessor {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public RealisticProperty<?> process(NbtCompound compound) {
		if (!compound.hasKey("type", NbtType.STRING) || !compound.hasKey("property", NbtType.COMPOUND)) {
			return null;
		}
		Class type = ClassCache.getClass(compound.getString("type"));
		if (type == null)
			return null;
		NbtCompound property = compound.getCompound("property");
		if (!property.hasKey("key", NbtType.STRING) || !property.hasKey("value")) {
			return null;
		}
		Object object = NbtJsonParser.fromNbt(property.get("value"), type);
		return new RealisticProperty<>(type, Property.createObject(property.getString("key"), object));
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
