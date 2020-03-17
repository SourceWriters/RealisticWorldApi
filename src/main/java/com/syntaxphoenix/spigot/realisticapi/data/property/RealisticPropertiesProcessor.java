package com.syntaxphoenix.spigot.realisticapi.data.property;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.NbtList;

public class RealisticPropertiesProcessor extends PropertiesProcessor {

	private final RealisticPropertyProcessor processor;

	public RealisticPropertiesProcessor(RealisticPropertyProcessor processor) {
		this.processor = processor;
	}

	public RealisticPropertyProcessor getProcessor() {
		return processor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RealisticProperties process(NbtCompound root) {
		RealisticProperties properties = new RealisticProperties();
		if (!root.hasKey("size"))
			return properties;
		int size = root.getInt("size");
		if (size == 0)
			return properties;
		NbtList<NbtCompound> list = (NbtList<NbtCompound>) root.getTagList("properties");
		for (NbtCompound compound : list) {
			RealisticProperty<?> property = processor.process(compound);
			if (property != null)
				properties.add(property);
		}
		return properties;
	}

}
