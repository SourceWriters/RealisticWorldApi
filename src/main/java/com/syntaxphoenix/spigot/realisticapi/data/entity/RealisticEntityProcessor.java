package com.syntaxphoenix.spigot.realisticapi.data.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.syntaxphoenix.spigot.realisticapi.data.RealisticProcessors;
import com.syntaxphoenix.spigot.realisticapi.data.property.RealisticProperties;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;

public class RealisticEntityProcessor extends EntityProcessor {

	@Override
	public RealisticEntity process(NbtCompound compound) {
		if (!compound.hasKey("properties") || !compound.hasKey("type"))
			return null;
		RealisticProperties properties = RealisticProcessors.PROPERTIES.process(compound.getCompound("properties"));
		EntityType type;
		try {
			type = EntityType.valueOf(compound.getString("type"));
		} catch (IllegalArgumentException iae) {
			return null;
		}
		return new RealisticEntity(properties, type);
	}

	@Override
	public RealisticEntity process(EntityType entityType) {
		return new RealisticEntity(entityType);
	}

	@Override
	public RealisticEntity process(Entity entity) {
		return process(entity.getType());
	}

}
