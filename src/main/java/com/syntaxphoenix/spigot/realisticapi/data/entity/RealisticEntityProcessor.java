package com.syntaxphoenix.spigot.realisticapi.data.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.syntaxphoenix.spigot.realisticapi.data.RealisticProcessors;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.NbtType;

public class RealisticEntityProcessor extends EntityProcessor {

	@Override
	public RealisticEntity process(NbtCompound compound) {
		if (!compound.hasKey("type", NbtType.STRING))
			return null;
		EntityType type = fromString(compound.getString("type"));
		if (type == null)
			return null;
		return compound.hasKey("properties", NbtType.COMPOUND)
				? new RealisticEntity(RealisticProcessors.PROPERTIES.process(compound.getCompound("properties")), type)
				: new RealisticEntity(type);
	}

	@Override
	public RealisticEntity process(EntityType entityType) {
		return new RealisticEntity(entityType);
	}

	@Override
	public RealisticEntity process(String string) {
		EntityType type = fromString(string);
		if (type == null)
			return null;
		return new RealisticEntity(type);
	}

	@Override
	public RealisticEntity process(Entity entity) {
		return process(entity.getType());
	}

	/**
	 * EntityType from String
	 * 
	 * @param name of the entity
	 * 
	 * @return type or null
	 */
	protected EntityType fromString(String string) {
		EntityType type = null;
		try {
			type = EntityType.valueOf(string.toUpperCase());
		} catch (IllegalArgumentException iae) {
		}
		return type;
	}

}
