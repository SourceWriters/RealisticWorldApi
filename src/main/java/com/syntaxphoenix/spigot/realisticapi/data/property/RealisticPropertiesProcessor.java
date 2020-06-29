package com.syntaxphoenix.spigot.realisticapi.data.property;

import com.syntaxphoenix.spigot.realisticapi.data.RealisticProcessors;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.NbtList;
import com.syntaxphoenix.syntaxapi.nbt.NbtType;

public class RealisticPropertiesProcessor extends PropertiesProcessor {

	@SuppressWarnings("unchecked")
	@Override
	public RealisticProperties process(NbtCompound root) {
		RealisticProperties properties = new RealisticProperties();
		if (!root.hasKey("size", NbtType.INT))
			return properties;
		int size = root.getInt("size");
		if (size == 0)
			return properties;
		NbtList<NbtCompound> list = (NbtList<NbtCompound>) root.getTagList("properties");
		for (NbtCompound compound : list) {
			RealisticProperty<?> property = RealisticProcessors.PROPERTY.process(compound);
			if (property != null)
				properties.add(property);
		}
		return properties;
	}

}
